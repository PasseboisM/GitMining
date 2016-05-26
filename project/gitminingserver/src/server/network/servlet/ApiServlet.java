package server.network.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.message.HintMessage;

/**
 * Servlet implementation class ApiServlet
 */
@WebServlet("/api")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String apiDoc = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiServlet() {
        super();
        try {
			BufferedReader reader = new BufferedReader(
					new FileReader(
							new File("D:/SE3/project/gitminingserver/API Design.txt")));
			StringBuilder sb = new StringBuilder();
			String s = null;
			while ((s=reader.readLine())!=null) {
				sb.append(s);
				sb.append('\n');
			}
			apiDoc = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try {
			if (request.getParameter("action").equals("test")) {
				out.print(new HintMessage("Connected.").toJSON());
			}
		} catch (Exception e) {
			printApiHint(out);
		}
		
		out.close();
	}
	
	private void printApiHint(PrintWriter out) {
		
		out.println(apiDoc);
	}

}
