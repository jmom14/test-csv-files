package com.exmple.model;

import java.util.Date;

public class CSVFile {
	
	private int cuenta;
	private int monto;
	private byte operacion;
	private Date fecha;
	
	public CSVFile() {
		
	}
	
	public CSVFile(int cuenta, int monto, byte operacion, Date fecha) {
		this.cuenta = cuenta;
		this.monto = monto;
		this.operacion = operacion;
		this.fecha = fecha;
	}
	
	public int getCuenta() {
		return cuenta;
	}
	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public byte getOperacion() {
		return operacion;
	}
	public void setOperacion(byte operacion) {
		this.operacion = operacion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "CSVFile [cuenta=" + cuenta + ", monto=" + monto + ", operacion=" + operacion + ", fecha=" + fecha + "]";
	}
	
	

}
