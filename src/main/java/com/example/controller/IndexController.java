package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.services.UtilService;
import com.exmple.model.CSVFile;

@Controller
public class IndexController {

	@Autowired
	UtilService utilService;

	@GetMapping("/index")
	public String hola() {
		return "index";
	}

	@PostMapping("/read")
	public String read(@RequestParam(value = "file") MultipartFile csv, @RequestParam(value = "mes") int mes) {

	
		String line = "";
		BufferedReader br = null;
		CSVFile csvFile = null;
		List<CSVFile> listCsv = new ArrayList<>();
		try {
			br = new BufferedReader(new InputStreamReader(csv.getInputStream()));

			while ((line = br.readLine()) != null) {
				String[] lineSplit = line.split(",");
				
				if(!("Fecha").equals(lineSplit[3])) {
					

					csvFile = new CSVFile(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]),
							Byte.parseByte(lineSplit[2]), lineSplit[3]);
					
					listCsv.add(csvFile);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "index";
	}
	
	public int obtenerCargos(List<CSVFile> list, int mes) {
		
		return list.stream().filter(e -> e.getOperacion() == 0 && e.getFecha().subSequence(3, 5).equals(utilService.getMes(mes)))
				.mapToInt(o -> o.getMonto())
			      .sum();
	}
	
	public int obtenerAbonos(List<CSVFile> list, int mes) {
		
		return list.stream().filter(e -> e.getOperacion() == 1 && e.getFecha().subSequence(3, 5).equals(utilService.getMes(mes)))
				.mapToInt(o -> o.getMonto())
			      .sum();
	}
	
	public int obteneTotal(List<CSVFile> list, int mes) {
		
		return obtenerAbonos(list, mes) - obtenerCargos(list, mes);
	}
	

	
}
