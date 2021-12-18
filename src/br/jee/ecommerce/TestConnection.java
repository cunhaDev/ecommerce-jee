package br.jee.ecommerce;

import java.sql.SQLException;

import br.jee.ecommerce.config.connection.ConexaoJDBC;

public class TestConnection {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConexaoJDBC.getConnection();
	}
}
