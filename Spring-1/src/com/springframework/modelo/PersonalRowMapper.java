package com.springframework.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonalRowMapper implements RowMapper<Personal> {

	@Override
	public Personal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Personal instanciaPersonal = new Personal();
		
		instanciaPersonal.setCodigo(rs.getInt("codigo"));
		instanciaPersonal.setNombre(rs.getString("nombre"));
		instanciaPersonal.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
		instanciaPersonal.setCargo(rs.getString("cargo"));
		
		return instanciaPersonal;
	}


}
