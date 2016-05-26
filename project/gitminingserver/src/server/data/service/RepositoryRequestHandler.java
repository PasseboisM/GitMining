package server.data.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RepositoryRequestHandler {

	public abstract void handleRequest(
			HttpServletRequest httpRequest, HttpServletResponse httpResponse);
	
}
