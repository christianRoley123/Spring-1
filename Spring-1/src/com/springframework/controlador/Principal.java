package com.springframework.controlador;

import java.io.ObjectInputStream.GetField;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.springframework.dao.PersonalDAO;
import com.springframework.dao.impl.PersonalDAOImpl;
import com.springframework.modelo.Personal;

public class Principal {
	
	public static void main(String[] args) {
		
		//interfaz que permite acceder a los recursos y utilidadess de spring
		ApplicationContext  contexto = new ClassPathXmlApplicationContext("configuracion-spring.xml");
		
		PersonalDAO personalDAO = (PersonalDAO) contexto.getBean("component_personalDAOImpl"); //inyectamos el daoImpl mediante su identificador
		
		Personal objPersonal = new Personal();
		
		Timestamp ts = new Timestamp(new Date().getTime());
		String fecha_sistema = ts.toString();
		
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-mm-dd");
			Date fecha = null;
			try {
				fecha = simpledateformat.parse(fecha_sistema);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(fecha);
		
		
		objPersonal.setNombre("Martin");
		objPersonal.setFechaCreacion(ts);
		objPersonal.setCargo("Jefe de Analista qa");
		
		
		
		try {
			
			if(personalDAO.guardar(objPersonal) == true) {
				System.out.printf("%s%n","Registro satisfactoriamente");
			}else {
				System.out.printf("%s%n", "Error en alguna parte del codigo, no se registro ningun usuario");
				
			}
			
			List<Personal> obj_listaPersonales = personalDAO.listar_todos_personales();
			
			for (Personal i : obj_listaPersonales) {
				System.out.println(i);
			}
			
			List<Personal> objPersonal2 = new ArrayList<Personal>();
			System.out.println(personalDAO.buscarPorId(3));
			System.out.println(personalDAO.buscarPorId(4));
			System.out.println(personalDAO.buscarPorId(3));
			objPersonal2 = personalDAO.buscarPorNombre("c");
			for (Personal personal : objPersonal2) {
				System.out.println(personal);
			}
			
			Personal obj = personalDAO.buscarPorId(5);
			System.out.println("Personal que sera actualizado: " + obj);
			obj.setCargo("Analista pmbok actualizado");
			obj.setNombre("Mariano actualizado");
			
			if(personalDAO.actualizarPersonal(obj) == true) {
				System.out.println("Personal actualizado correctamente , ahora la informacion actual es: " + personalDAO.buscarPorId(5));
			}else {
				
				System.out.println("Error en alguna sintaxis del codigo, no se pudo realizar la operacion");
			}
			
			System.out.println("Se dara de baja al personal: " + personalDAO.buscarPorId(10));
			
			if(personalDAO.darDebajaPersonal(10) == true) {
				
			}
			
			List<Personal> listaOBJ = new ArrayList<Personal>();
			listaOBJ.add(new Personal("Mariana","Jefe de operaciones",ts));
			listaOBJ.add(new Personal("Richigardo", " Encargado manufactura",ts));
			
			int [] valores = personalDAO.guardarTodos(listaOBJ);
			
		}catch( CannotGetJdbcConnectionException ex) { //en caso no se obtenga conexion con JDBC saltaran las excepciones 
		
			ex.printStackTrace();
		}catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		//Para quitar el MENSAJE DE WARNING de applicationContext -> Debemos cerrar el applicationContext.
		((ClassPathXmlApplicationContext) contexto).close();
	}

}
