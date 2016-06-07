package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.message.HintMessage;
import server.data.service.RecommendRequestHandler;

public class RecommendRequestHandlerDefault implements RecommendRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setCharacterEncoding("gb2312");

		PrintWriter out = null;
		try {
			out = httpResponse.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String type = httpRequest.getParameter("type");
		if (type==null) {
			out.print(new HintMessage("Parameter (type) is necessary!").toJSON());
			out.close();
		}
		
		switch (type) {
		case "repo":
			printTypeRepo(httpRequest, out);
			break;
		case "user":
			printTypeUser(httpRequest, out);
			break;
		case "related":
			printTypeRelated(httpRequest, out);
			break;
		default:
			out.println(new HintMessage("Unknown value for parameter (type).").toJSON());
			break;
		}
		
		out.close();
		
	}
	
	private void printTypeRepo(HttpServletRequest httpRequest, PrintWriter out) {
		
	}
	
	private void printTypeUser(HttpServletRequest httpRequest, PrintWriter out) {
		
	}
	
	private void printTypeRelated(HttpServletRequest httpRequest, PrintWriter out) {
		
	}
	
}
