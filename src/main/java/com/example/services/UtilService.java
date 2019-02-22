package com.example.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class UtilService {
	
	public Date getDate(String date) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(date); 
	}
	
	public int getMonth(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return Integer.parseInt(dateFormat.format(date));
	}

}
