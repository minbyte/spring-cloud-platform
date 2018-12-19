package com.mindasoft.cloud.commons.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author huangmin
 * @Date 2015年12月23日上午11:39:21
 */
public class AgeUtils {

	
	public static int getAge(Date birthDay){
		Calendar cal = Calendar.getInstance(); 
		
        if (cal.before(birthDay)) { 
            throw new IllegalArgumentException( 
                "The birthDay is before Now.It's unbelievable!"); 
        } 
        int yearNow = cal.get(Calendar.YEAR); 
        int monthNow = cal.get(Calendar.MONTH); 
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
        
        cal.setTime(birthDay); 
        int yearBirth = cal.get(Calendar.YEAR); 
        int monthBirth = cal.get(Calendar.MONTH); 
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
  
        int age = yearNow - yearBirth; 
  
        if (monthNow <= monthBirth) { 
            if (monthNow == monthBirth) { 
                if (dayOfMonthNow < dayOfMonthBirth) age--; 
            }else{ 
                age--; 
            } 
        } 
        return age; 
	}
	
	public static Date getBirthDay(Integer age){
		if(null == age){
			return null;
		}
		
		if (age<0 || age > 150) { 
            throw new IllegalArgumentException( 
                "The age must between 0 and 150.It's unbelievable!"); 
        }
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.YEAR, 0-age);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
		
		return cal.getTime();
	}
	
}
