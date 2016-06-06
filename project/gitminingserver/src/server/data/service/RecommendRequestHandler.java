package server.data.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RecommendRequestHandler {

	
	public abstract void handleRequest(
			HttpServletRequest httpRequest, HttpServletResponse httpResponse);
}
