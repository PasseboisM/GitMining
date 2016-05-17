package network.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.service.DataServiceMaker;
import data.service.RepositoryRequestHandler;

/**
 * Servlet implementation class RepositoryServlet
 */
@WebServlet("/repo")
public class RepositoryServlet extends HttpServlet {

	private static final long serialVersionUID = -1172282505153057641L;
	private static final RepositoryRequestHandler handler = 
			DataServiceMaker.getInstance().getRepositoryService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepositoryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handler.handleRequest(request, response);
	}

}
