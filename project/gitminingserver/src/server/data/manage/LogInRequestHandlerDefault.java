package server.data.manage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.data.service.LogInRequestHandler;

public class LogInRequestHandlerDefault implements LogInRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setCharacterEncoding("gb2312");
		// TODO Auto-generated method stub
		
	}

}
