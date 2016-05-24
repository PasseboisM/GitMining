package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.data.service.RepositoryRequestHandler;

class RepositoryRequestHandlerDefault extends RepositoryRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		try {
			out = httpResponse.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (httpRequest.getParameter("isTest").equals("true")) {
				out.println("{\"message\":\"This is a testing message.\"}");
			} else {
				out.println("{\"message\":\"Not testing. Request not handled.\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("You can use the parameter 'isTest=true' to get a repository JSON for testing.");
		}
		
		out.close();
	}

}
