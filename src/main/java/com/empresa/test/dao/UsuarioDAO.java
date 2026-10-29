package com.empresa.test.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.empresa.test.modelo.Usuario;
import com.empresa.test.util.Conexion;

public class UsuarioDAO {
	
	private Conexion conexion;
	
	private static final String INSERT_USUARIO_SQL = "INSERT INTO usuario (nombre, email, pais) VALUES (?, ?, ?);";
	private static final String DELETE_USUARIO_SQL = "DELETE FROM WHERE id = ?;";
	private static final String UPDATE_USUARIO_SQL = "UPDATE usuario nombre = ?, email = ?, pais = ? WHERE id = ?;";
	private static final String SELECT_USUARIO_BY_ID = "SELECT * FROM usuario WHERE id = ?;";
	private static final String SELECT_ALL_USUARIO = "SELECT * FROM usuario;";

	private UsuarioDAO() {
		
		this.conexion = conexion.getConexion();
	}
	
	public void insert(Usuario usuario) {
		try {
			
			PreparedStatement preparedStatement = conexion.setPreparedStatement(INSERT_USUARIO_SQL);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPais());
			
			conexion.execute();
			
		} catch(SQLException e) {
			
		}
	}
	
	public void Delete(int id) {
		try {
			
			PreparedStatement preparedStatement = conexion.setPreparedStatement(DELETE_USUARIO_SQL);
			preparedStatement.setInt(1, id);
			
			conexion.execute();
			
		} catch(SQLException e) {
			
		}
	}
	
	public void Update(Usuario usuario) {
		try {
			
			PreparedStatement preparedStatement = conexion.setPreparedStatement(UPDATE_USUARIO_SQL);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getPais());
			preparedStatement.setInt(4,usuario.getId());
			
			conexion.execute();
			
		} catch(SQLException e) {
			
		}
	}
	
	public List<Usuario> selectAll() {
		
		List <Usuario> usuarios = new ArrayList<>();
		
		try {
			PreparedStatement preparedStatement = conexion.setPreparedStatement(SELECT_ALL_USUARIO);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String pais = rs.getString("pais");
				usuarios.add(new Usuario(id, nombre, email, pais));
				
			}
			
		} catch(SQLException e) {
			
		}
		
		return usuarios;
		
	}
	
}
