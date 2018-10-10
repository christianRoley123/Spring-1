package com.springframework.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

import com.springframework.dao.PersonalDAO;
import com.springframework.modelo.Personal;
import com.springframework.modelo.PersonalRowMapper;

@Component(value = "component_personalDAOImpl") //PARA QUE SEA CANDIDATA A LA INYECCION DE DEPENDENCIAS
public class PersonalDAOImpl implements PersonalDAO{
	
	Logger logger = Logger.getLogger("com.springframework.dao.impl");
	
	private NamedParameterJdbcTemplate jdbcTemplate; // Plantilla que acepta comodines para evitar enginer inyection
	
	/**
	 * Este metodo al inicializar con @Autoewired  INYECTARA el BEAN que se creo para el DBCP.JAR el cual permite la conexion con la base de datos
	 * y que con jdbTemplate se pueda hacer uso de los recursos que haran la persistencia a la BD
	 * @param datasource
	 */
	@Autowired
	private void obtenerDataSource(DataSource datasource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(datasource);
	}

	@Override
	public boolean guardar(Personal p) {
		
//		MapSqlParameterSource parametrosMapeados = new MapSqlParameterSource();
//		parametrosMapeados.addValue("nombre", p.getNombre());
//		parametrosMapeados.addValue("cargo", p.getCargo());
//		parametrosMapeados.addValue("fechaCreacion", p.getFechaCreacion());
			
		//En caso en la clase pojo(MODEL) se maneje los atributos con la descripcion igual a los CAMPOS de la BD funcionara.....
			BeanPropertySqlParameterSource parametrosMapeados = new BeanPropertySqlParameterSource(p);
			
		
			//UPDATE -> Retorna el numero de filas aceptadas , por ello si nos retorna 1 = true
		return jdbcTemplate.update("INSERT INTO tbl_administrador(nombre,cargo,fechaCreacion) VALUES(:nombre,:cargo,:fechaCreacion)", parametrosMapeados) == 1;
		
	}

	@Override
	public List<Personal> listar_todos_personales() {
		
	     List<Personal>lista = new ArrayList<Personal>();
	    
	    // JDBCTEMPLATE -> Acepta 1 consulta SQL y un RowMapper
	   //ROWMAPPER -> interfaz que permite asociar el resultado de la consulta con los pojos( MODELO.class )
		lista = jdbcTemplate.query("SELECT * FROM tbl_administrador", new RowMapper<Personal>() {

			@Override
			public Personal mapRow(ResultSet rs, int rowNum) throws SQLException { // METODO DE LA INTERFACE RowMapper
				Personal obj = new Personal();
				obj.setCodigo(rs.getInt("codigo"));
				obj.setNombre(rs.getString("nombre"));
				obj.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				obj.setCargo(rs.getString("cargo"));
				
				
				return obj;
			} 
			
		} );
		
		return lista;
	}

	@Override
	public Personal buscarPorId(int id) {
		logger.info("INICIALIZO METODO BUSCAR POR CODIGO DE PERSONAL");
		Personal instancia = new Personal();
		//CASTEAMOS (Personal) -> Porque el metodo no sabe que tipo de resultado retornara, al retornar una instancia de tipo "Personal" se castea
//		instancia = (Personal) jdbcTemplate.query("SELECT * FROM tbl_administrador WHERE codigo = :param_id ",
//										new MapSqlParameterSource("param_id" , id), new PersonalRowMapper());
		//otra forma de obtener el mismo resultado pero sin tener que CASTEAR(Personal) porque el metodo sabe que nos retornara un OBJETO  
		instancia = jdbcTemplate.queryForObject("SELECT * FROM tbl_administrador WHERE codigo = :param_id",
												new MapSqlParameterSource("param_id", id),
												new PersonalRowMapper());
		return instancia;
	}

	@Override
	public List<Personal> buscarPorNombre(String nombre) {
		logger.info("INICIALIZO METODO BUSCAR PERSONAL POR NOMBRE");
		List<Personal> instanciaPersonal = new ArrayList<Personal>();
		instanciaPersonal = jdbcTemplate.query("SELECT * FROM tbl_administrador WHERE nombre LIKE :param_nombre",
												new MapSqlParameterSource("param_nombre", "%" + nombre + "%"),
												new PersonalRowMapper());
		return instanciaPersonal;
	}

	@Override
	public boolean actualizarPersonal(Personal param_personal) {
		logger.info("INICIALIZANDO METODO ACTUALIZAR PERSONAL");
		boolean resultado;
		resultado = jdbcTemplate.update("UPDATE tbl_administrador SET nombre = :nombre , cargo = :cargo , fechaCreacion = :fechaCreacion"
										+ "	where codigo = :codigo", new BeanPropertySqlParameterSource(param_personal)) == 1;
		return resultado;
	}

	@Override
	public boolean darDebajaPersonal(int param_codigo) {
		logger.info("INICIALIZO METODO PARA DAR DE BAJA AL PERSONAL");
		boolean resultado ;
		if(
		resultado = jdbcTemplate.update("DELETE  FROM tbl_administrador WHERE codigo = :param_codigo", new MapSqlParameterSource("param_codigo",param_codigo)) == 1
		) {
			logger.info("Actualizacion completa y correctamente realizado");
		}else {
			logger.info("actualizacion no realizada, error en alguna parte del codigo.");
			resultado = false;
		}
		return resultado;
	}

	@Override
	public void guardarTodos(List<Personal> personal) {
		
		SqlParameterSource[] listaArgs = SqlParameterSourceUtils.createBatch(personal.toArray());
		jdbcTemplate.batchUpdate("INSERT INTO tbl_personal(nombre, cargo, fechaCreacion)values(:nombre, :cargo, :fechaCreacion)", listaArgs);
		
	}

}
