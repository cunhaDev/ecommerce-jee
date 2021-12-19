package br.jee.ecommerce.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.jee.ecommerce.domain.entities.ProductEntity;
import br.jee.ecommerce.useCases.ListProductsUseCase;

@WebServlet("/acao")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListProductsUseCase productUseCase = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		productUseCase = new ListProductsUseCase((Connection) request.getAttribute("conexao"));

		String metodo = request.getParameter("met");
		String pagina = "index.jsp";

		if (metodo.equals("acessar")) {
			if (listarCliente(request) != null) {
				pagina = "listar.jsp";
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/" + pagina);
		dispatcher.forward(request, response);

	}

	private List<ProductEntity> listarCliente(HttpServletRequest request) {
		try {
			List<ProductEntity> lista = productUseCase.listar();
			if (lista.isEmpty()) {
				lista = new ArrayList<ProductEntity>();
				request.getSession().setAttribute("listaDeProdutos", lista);
				return null;
			} else {
				request.getSession().setAttribute("listaDeProdutos", lista);
			}
			return lista;
		} catch (Exception e) {
			request.setAttribute("mensagem", "Não foi possível listar!");
		}
		return null;
	}
}