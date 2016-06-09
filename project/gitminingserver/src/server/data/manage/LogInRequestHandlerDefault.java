package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.message.HintMessage;
import data.service.DataServiceFactory;
import data.service.LogInDataService;
import data.service.SpecificDataGetter;
import server.data.service.LogInRequestHandler;

public class LogInRequestHandlerDefault implements LogInRequestHandler {

	private LogInDataService logInService = 
			DataServiceFactory.getInstance().getLogInDataService();
	

	
	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setCharacterEncoding("gb2312");
		
		PrintWriter out = null;
		
		try {
			out = httpResponse.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		String login = null, password = null;
		
		try {
			login = httpRequest.getParameter("login");
			password = httpRequest.getParameter("pass");
			if (login==null || password==null) {
				throw new Exception();
			}
		} catch (Exception e) {
			out.println(new HintMessage("Login and password are necessary!").toJSON());
			return;
		}
		
		out.print(logInService.tryLogIn(login, password));
		
		out.close();
	}

}
