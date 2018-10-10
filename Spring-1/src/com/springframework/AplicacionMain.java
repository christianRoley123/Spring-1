package com.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AplicacionMain {
	
//	public static void main(String[] args) {
//		//interfaz que permite acceder a los recursos y utilidadess de spring
//		ApplicationContext applicationContext;
//		applicationContext = new ClassPathXmlApplicationContext("configuracion-spring.xml"); // pasamos el nombre del archivo de configuracion de spring.XML para poder hacer las inyecciones de dependencia
//		Administrador objAdministrador = (Administrador) applicationContext.getBean("admin_bean"); //IDENTIFICADOR DEL BEAN : admin
//		
//		objAdministrador.imprimirDireccion();
//		
//		System.out.println(objAdministrador);//POR DEFECTO LLAMARA AL METODO ToString()
//		
//		//Para quitar el MENSAJE DE WARNING de applicationContext -> Debemos cerrar el applicationContext.
//		((ClassPathXmlApplicationContext) applicationContext).close();
//	}
}
