package server.network.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.message.HintMessage;
import server.data.manage.RecommendRequestHandlerDefault;
import server.data.service.RecommendRequestHandler;

/**
 * Servlet implementation class RecommendServlet
 */
@WebServlet("/recommend")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RecommendRequestHandler handler = new RecommendRequestHandlerDefault(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handler.handleRequest(request, response);
	}

}
