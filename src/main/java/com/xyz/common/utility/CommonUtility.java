package com.xyz.common.utility;

import java.security.SecureRandom;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.xyz.common.constant.ApplicationConstant;

public class CommonUtility {
	private static SecureRandom random = new SecureRandom();
	public static String generateUniqueCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(ApplicationConstant.PREFIX);
        for (int i = 0; i < 4; i++) {
            sb.append(ApplicationConstant.ALPHA_NUMERIC_STRING.charAt(new Random().nextInt(ApplicationConstant.ALPHA_NUMERIC_STRING.length())));
        }
        return sb.toString();
    }
	
	public static String generateUniqueCodeForTheater() {
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            sb.append(ApplicationConstant.ALPHA_NUMERIC_STRING.charAt(random.nextInt(ApplicationConstant.ALPHA_NUMERIC_STRING.length())));
        }
        return sb.toString();
    }
	
	public static Date convertToDate(String dateString,String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }
	
	public static String convertTimeToString(Time time,String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedTime = formatter.format(time);
        return formattedTime;
	}
	
	public static String convertTodateToString(Date date,String pattern) {
		         
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
      
        String dateString = dateFormat.format(date);

        
        return dateString;
	}

}
