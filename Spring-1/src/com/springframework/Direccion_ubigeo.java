package com.springframework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
//@COMPONENT ->INDICARA A SPRING QUE LA CLASE ES CANDIDATA A SER AUTO-INYECTADA DENTRO DE CUALQUIER DEPENDENCIA
@Component 
public class Direccion_ubigeo {
	private String calle;
	private String codigo_postal;
	
	
	public Direccion_ubigeo() {
		super();
	}
	public Direccion_ubigeo(String calle, String codigo_postal) {
		super();
		this.calle = calle;
		this.codigo_postal = codigo_postal;
	}
	@Override
	public String toString() {
		return ("La Direccion es: [Calle= " + calle + ", codigo postal=" + codigo_postal +"]");
		
	}
	@Autowired //INICIALIZA  para que funcione
	public void setCalle(@Value("La rica vicky") String calle) {
		this.calle = calle;
	}
	
	@Autowired //Inicializa para que funcione
	public void setCodigo_postal(@Value("victoria-19956") String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	
	
	
}
