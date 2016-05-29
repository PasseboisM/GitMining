package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import common.message.HintMessage;
import data.db.core.ConnectionPool;
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
				
				out.print(new HintMessage("This is a test message!").toJSON());
				
				out.close();
				return;
			}
			
		} catch (Exception e) {
		}
		
		//TODO Handle repo requests
		
		out.close();
	}

}
