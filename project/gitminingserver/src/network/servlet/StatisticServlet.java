package network.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.service.DataServiceMaker;
import data.service.StatisticRequestHandler;

/**
 * Servlet implementation class StatisticServlet
 */
@WebServlet("/stat")
public class StatisticServlet extends HttpServlet {

	private static final long serialVersionUID = 4697050488559930071L;
	private static final StatisticRequestHandler handler = 
			DataServiceMaker.getInstance().getStatisticHandler();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handler.handleRequest(request, response);
	}

}
