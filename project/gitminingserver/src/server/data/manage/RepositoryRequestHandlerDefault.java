package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.message.HintMessage;
import server.data.service.RepositoryRequestHandler;

class RepositoryRequestHandlerDefault extends RepositoryRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		PrintWriter out = null;
		try {
			out = httpResponse.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (httpRequest.getParameter("isTest").equals("true")) {
				out.println(new HintMessage("This is a testing message.").toJSON());
				out.close();
				return;
			}
		} catch (Exception e) {
		}
		
		//TODO Handle repo requests
		
		out.close();
	}

}
