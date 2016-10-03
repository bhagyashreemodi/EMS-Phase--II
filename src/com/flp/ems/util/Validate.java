package com.flp.ems.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validate {

	public static void validateDate(String date) throws Exception{
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOb = format.parse(date);
		if(dateOb.compareTo(new Date()) > 0){
			throw new Exception("Enter valid date");
		}
	}
	public static void validatePhoneNumber(String phoneNumber) throws Exception{
		
		if(phoneNumber.length() != 10 || !(phoneNumber.matches("\\d+"))){
			throw new Exception("Please enter valid phone number");
			
		}
		
	}
	public static void validateName(String name) throws Exception{
		
		if(!(name.matches(".+"))){
			throw new Exception("Please enter valid name");
		}
		
	}
}
