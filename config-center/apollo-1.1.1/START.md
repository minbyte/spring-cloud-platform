1、打包完后apollo-configservice和apollo-adminservice上传到服务器。
2、在startup.sh 中指定APOLLO_PROFILE，启动链接各自的数据

3、在apollo-portal中的apollo-env.properties指定测试和生产环境的地址，
注意对应数据库serverconfig表的apollo.portal.envs 也要相应修改。
