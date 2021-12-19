package br.jee.ecommerce.useCases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.jee.ecommerce.domain.entities.ProductEntity;

public class ListProductsUseCase {

	private Connection conexao;

	public ListProductsUseCase(Connection conexao) {
		this.conexao = conexao;
	}

	public List<ProductEntity> listar() throws SQLException {
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		String SQL = "SELECT name, description FROM product";
		PreparedStatement stmt = conexao.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductEntity aa = new ProductEntity();
			aa.setName(rs.getString(1)); 
			aa.setDescription(rs.getString(2));

			list.add(aa);
		}
		rs.close();
		stmt.close();
		return list;
	}

}
