package server.data.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogInRequestHandler {

	public abstract void handleRequest(
			HttpServletRequest httpRequest, HttpServletResponse httpResponse);
	
}
