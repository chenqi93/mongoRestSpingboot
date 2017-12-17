package com.threezebra.onesimple.common;

public class CommonUtil {
	
	public static String convertArrayData(String[] arrData) {
		String output = "";
		if(arrData != null) {
			for(String data : arrData) {
				output = output + data + ":";
			}
		}
		if(output.charAt(output.length()-1) == ':') {
			output = output.substring(0, output.length()-1);
		}
		return output;
	}

}
