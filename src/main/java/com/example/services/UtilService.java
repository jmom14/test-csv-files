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
	
	public String getMes(int mes) {
		String out ="";
		switch(mes){
		case 1:
			out = "01";
			break;
		case 2:
			out = "02";
			break;
		case 3:
			out = "03";
			break;
		case 4:
			out = "04";
			break;
		case 5:
			out = "05";
			break;
		case 6:
			out = "06";
			break;
		case 7:
			out = "07";
			break;
		case 8:
			out = "08";
			break;
		case 9:
			out = "09";
			break;
		case 10:
			out = "10";
			break;
		case 11:
			out = "11";
			break;
		case 12:
			out = "12";
			break;
		default:
				out = "";
			break;
		}
		return out;
	}

}
