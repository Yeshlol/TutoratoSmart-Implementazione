package project.Utils;

import java.security.MessageDigest;

public class Utils {
	public static String sha256(String baseString) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(baseString.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();

	        for (int i = 0; i < hash.length; i++) {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}
	
	public static String getTimeAsString(int time) {		
		int hours = time / 60;
		String hs = "";
		
		if (hours == 0) {
			hs = "00";
		}
		else if (hours < 10)
			hs = "0" + hours;
		else
			hs = "" + hours;
			
		int minutes = time % 60;
		String min = "";
		
		if (minutes == 0) {
			min = "00";
		}
		else if (minutes < 10)
		min = "0" + minutes;
		else
			min = "" + minutes;
		
		return hs + ":" + min;
	}
	
	public static int getTimeAsInt (String time) {
		String hs = time.substring(0,2);
		String min = time.substring(3);
		
		int hours = Integer.parseInt(hs) * 60;
		int minutes = Integer.parseInt(min);
		
		return hours + minutes;
	}
}