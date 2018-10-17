package com.springframework.dao;

import java.util.List;

import com.springframework.modelo.Personal;

public interface PersonalDAO {
	/**
	 * Metodo para realizar un registro nuevo de un Personal.
	 * @param p
	 * @return
	 */
	public boolean guardar(Personal p);
	
	
	/**
	 * Metodo que retorna una lista con los personales existentes en la base de datos.
	 * @return
	 */
	public List<Personal> listar_todos_personales();
	
	/**
	 * Metodo que retorna la informacion del personal segun su codigo.
	 * @return
	 */
	public Personal buscarPorId(int id);
	
	/**
	 * Metodo que retorna una lista con la informacion de los personales mediante el nombre.
	 * @return
	 */
	public List<Personal> buscarPorNombre(String nombre);
	
	/**
	 * Metodo que actualiza la informacion del personal seleccionado.
	 * 
	 * @param param_personal
	 * @return
	 */
	public boolean actualizarPersonal(Personal param_personal);
	
	/**
	 * Metodo que da de baja la cuenta del personal seleccionado.
	 * 
	 * @param param_codigo
	 * @return
	 */
	public boolean darDebajaPersonal(int param_codigo);
	
	
	/**
	 * Metodo que retornara una array con el numero de filas afectadas para validar que 
	 * se considere correcto si se logra ingresar todo el bloque de inserts a la base de datos.
	 * 
	 * @param personal
	 * @return
	 */
	public int[] guardarTodos(List<Personal> personal) ;
	
}
