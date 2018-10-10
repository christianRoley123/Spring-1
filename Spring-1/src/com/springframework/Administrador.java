package com.springframework;

import org.springframework.beans.factory.annotation.Autowired;

public class Administrador {
	public Administrador() {
		super();
	}
	
	private int id_codigo;
	private String nombre;
	
	//@Autowired sirve para inicializar y inyectar la dependencia.
	@Autowired 
	private Direccion_ubigeo direccion;
	
	


	@Override
	public String toString() {
		
		return  "Administrador [id_codigo=" + id_codigo + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

	public void setDireccion(Direccion_ubigeo direccion) {
		this.direccion = direccion;
	}

	public Administrador(int id_codigo, String nombre) {
		super();
		this.id_codigo = id_codigo;
		this.nombre = nombre;
	}

	public int getId_codigo() {
		return id_codigo;
	}

	public void setId_codigo(int id_codigo) {
		this.id_codigo = id_codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Usando el formato de impresion: PRINTF()
	 */
	public void imprimirDireccion() {
		System.out.printf("%S%s %n","E","ncino 201");
		
	}
}
