package br.jee.ecommerce.useCases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.jee.ecommerce.domain.entities.ProductEntity;

public class UseCasesExample {
	
	private Connection conexao;

	public UseCasesExample(Connection conexao) {
		this.conexao = conexao;
	}
	
	public ProductEntity login(ProductEntity ProductEntity) throws SQLException{
		ProductEntity aluRetorno = null;
		String SQL = "SELECT * FROM alu WHERE usuario = ? and senha = ?";
		PreparedStatement stmt = conexao.prepareStatement(SQL);
//		stmt.setString(1, ProductEntity.getUsuario());
//		stmt.setString(2, ProductEntity.getSenha());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) { 
			aluRetorno = new ProductEntity();
//			aluRetorno.setUsuario(rs.getString(1));
//			aluRetorno.setSenha(rs.getString(2));
		}
		rs.close();
		stmt.close();
		return aluRetorno; 
	}
	
	public boolean salvar(ProductEntity ProductEntity) throws SQLException{
		
		String SQL = "INSERT INTO alu (usuario,senha) VALUES (?,?) ";
		PreparedStatement pstm = conexao.prepareStatement(SQL);
//		pstm.setString(1, ProductEntity.getUsuario());
//		pstm.setString(2, ProductEntity.getSenha());
		boolean r = pstm.execute();
		pstm.close();
		return !r;
	}
	
	public boolean excluir(ProductEntity ProductEntity) throws SQLException{
		String SQL = "DELETE FROM alu WHERE id=?";
		PreparedStatement pstmt = conexao.prepareStatement(SQL) ;
		pstmt.setLong(1, ProductEntity.getId());
		int r=pstmt.executeUpdate();
		pstmt.close();
		if(r>0){
		return true;
		}
		return false;
		}
	
	public ProductEntity alterar(ProductEntity ProductEntity) throws SQLException{
		String SQL = "UPDATE alu SET usuario = ?, senha = ? WHERE id = ?";
		PreparedStatement pstmt = conexao.prepareStatement(SQL) ;
//		pstmt.setString(1, ProductEntity.getUsuario());
//		pstmt.setString(2, ProductEntity.getSenha());
		int r=pstmt.executeUpdate();
		pstmt.close();
		if(r>0){
		return alterar(ProductEntity);
		}
		return null;
		}
	
	public List<ProductEntity> buscar(ProductEntity ProductEntity) throws SQLException{
		List<ProductEntity> listarProductEntitys = new ArrayList<ProductEntity>();
		String SQL = "SELECT * FROM alu WHERE usuario LIKE ? ";
		PreparedStatement stmt = conexao.prepareStatement(SQL);
//		stmt.setString(1, "%"+ProductEntity.getUsuario()+"%");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			ProductEntity alu = new ProductEntity();
//			alu.setUsuario(rs.getString("usuario")); 
//			alu.setSenha(rs.getString("senha"));
			listarProductEntitys.add(ProductEntity);
	}
		rs.close(); 
		stmt.close();
		return listarProductEntitys;
	}
	
	public List<ProductEntity> listarr()throws SQLException{
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		String SQL = "SELECT * FROM alu";
		PreparedStatement stmt = conexao.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ProductEntity aa = new ProductEntity();
//			aa.setUsuario(rs.getString(1));
//			aa.setSenha(rs.getString(2));

			list.add(aa);
		}
		rs.close();
		stmt.close();
		return list;
		}

}
