package br.jee.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.jee.ecommerce.gateway.connection.ConexaoJDBC;


@WebFilter("/*")
public class SessionFilter implements Filter {
	private Connection conexao;
	public SessionFilter() {
		System.out.println("Create filter");
	}
	public void destroy() {
		System.out.println("Destroy filter");
	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException,
	ServletException {
		try {
			conexao = ConexaoJDBC.getConnection();
			request.setAttribute("conexao", conexao);
			chain.doFilter(request, response);
			conexao.setAutoCommit(false);
			conexao.commit();
		}
		catch( SQLException e ) {
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e ) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		} }
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Init filter");
	} }