
package com.mindasoft.cloud.commons.util;

import com.mindasoft.cloud.commons.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * IP地址
 *
 * @author dodoing
 * @email hmiter@sina.com
 * @datetime 2017年3月8日 下午12:57:02
 */
public class IPUtils {

	private static Logger logger = LoggerFactory.getLogger(IPUtils.class);

    private final static String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";

	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if( ip.indexOf(",")!=-1 ){
                    ip = ip.split(",")[0];
                }
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
        	logger.error("IPUtils ERROR ", e);
        }
        return ip;
    }

    /**
     * 验证是否为正确的IP地址
     * @param ip
     * @return
     */
    public static boolean isIP(String ip){
        if (ip == null){
            throw new NullPointerException("IP不能为空！");
        }
        return ip.matches(REGX_IP);
    }

    /**
     * 检测ip 是否在beginIp 和 endIp 的地址段内
     * @param ip
     * @param beginIp
     * @param endIp
     * @return
     */
    public static boolean isIPSection(String ip,String beginIp,String endIp) {
        if (beginIp == null || endIp == null){
            throw new NullPointerException("IP段不能为空");
        }
        if(!isIP(beginIp) || !isIP(endIp) || !isIP(ip)){
            throw new BaseException("IP地址错误");
        }
        String[] sips = beginIp.split("\\.");
        String[] sipe = endIp.split("\\.");
        String[] sipt = ip.split("\\.");
        long ips = 0L, ipe = 0L, ipt = 0L;
        for (int i = 0; i < 4; ++i) {
            ips = ips << 8 | Integer.parseInt(sips[i]);
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);
        }
        if (ips > ipe) {
            long t = ips;
            ips = ipe;
            ipe = t;
        }
        return ips <= ipt && ipt <= ipe;
    }

    /**
     * 是否为内网网段
     * @param ip
     * @return
     */
    public static boolean isLAN(String ip){
        if("127.0.0.1".equals(ip)){
            return true;
        }
        return isIPSection(ip,"192.168.0.0","192.168.255.255")
                || isIPSection(ip,"172.16.0.0","172.31.255.255")
                || isIPSection(ip,"10.0.0.0","10.255.255.255");
    }

    public static boolean isLAN(HttpServletRequest request){
        return isLAN(getIpAddr(request));
    }



}
