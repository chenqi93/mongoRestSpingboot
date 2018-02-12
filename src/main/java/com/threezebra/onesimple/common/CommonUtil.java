package com.threezebra.onesimple.common;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class CommonUtil {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private static SecureRandom srand;
    private static ArrayList validch;

	public static String convertArrayData(String[] arrData) {
		String output = "";
		if (arrData != null) {
			for (String data : arrData) {
				output = output + data + ":";
			}
		}
		if (output.charAt(output.length() - 1) == ':') {
			output = output.substring(0, output.length() - 1);
		}
		return output;
	}

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
	public static void PasswordGenerator() throws NoSuchAlgorithmException, NoSuchProviderException {
        srand = new SecureRandom();
        validch = new ArrayList<>();

        //Filling the ArrayList with the characters we want to use based on ascii table:
        // https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
        for (int i = 65; i < 91; i++) {
            validch.add((char) i);// A-Z
            validch.add((char) (i + 32));// a-z
        }
        for (int i = 48; i < 58; i++) {
            validch.add((char) i);
        }
        for (int i = 35; i < 39; i++) {
            validch.add((char) i);
        }
        validch.add((char) 64);
        Collections.shuffle(validch);
    }
	
	public static char randChar() throws NoSuchAlgorithmException, NoSuchProviderException {
		PasswordGenerator();
        return (char) validch.get(srand.nextInt(validch.size()));
    }
	
	public static String newPassword() throws NoSuchAlgorithmException, NoSuchProviderException {
		 StringBuilder sb = new StringBuilder();
		 for (int i = 0; i < 10; i++) { // Passwords are 10 characters long
             sb.append(randChar());
         }
		return sb.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		System.out.println(newPassword());
	}
}
