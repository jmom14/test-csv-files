package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

				if (!("Fecha").equals(lineSplit[3])) {

					csvFile = new CSVFile(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]),
							Byte.parseByte(lineSplit[2]), utilService.getDate(lineSplit[3]));

					listCsv.add(csvFile);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		System.out.println(obtenerCargos(listCsv,mes));
//		System.out.println(obtenerAbonos(listCsv, mes));
//		System.out.println(obteneTotal(listCsv, mes));
//		obtenerListas(listCsv);
		getSalida(listCsv, mes);

		return "index";
	}

	public int obtenerCargos(List<CSVFile> list, int mes) {

		return list.stream().filter(e -> e.getOperacion() == 0 && utilService.getMonth(e.getFecha()) == mes).mapToInt(o -> o.getMonto()).sum();
	}

	public int obtenerAbonos(List<CSVFile> list, int mes) {

		return list.stream().filter(e -> e.getOperacion() == 1 && utilService.getMonth(e.getFecha()) == mes)
				.mapToInt(o -> o.getMonto()).sum();
	}

	public int obteneTotal(List<CSVFile> list, int mes) {

		return obtenerAbonos(list, mes) - obtenerCargos(list, mes);
	}

	public Object[] getDistinct(List<CSVFile> list) {

		return list.stream().map(CSVFile::getCuenta).distinct().sorted().toArray();
	}

	public void getSalida(List<CSVFile> list, int mes) {

		Object[] nums = getDistinct(list);
		List<List<CSVFile>> out = new ArrayList<>();
		List<CSVFile> tmpList = null;
		List<CSVFile> auxList = null;
		int total;
		String abs;

		for (Object o : nums) {
			tmpList = list.stream().filter(item -> item.getCuenta() == (int) o).collect(Collectors.toList());
			out.add(tmpList);
		}

		for (int i = 0; i < out.size(); i++) {
			auxList = out.get(i);
			total = obteneTotal(auxList, mes);
			abs = (total < 0) ? "(" + Math.abs(total) + ")" : Integer.toString(total) ;
			System.out.println(auxList.get(0).getCuenta() + ", " + abs);
		}

	}

}
