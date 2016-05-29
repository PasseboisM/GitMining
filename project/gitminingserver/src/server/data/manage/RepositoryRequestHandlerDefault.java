package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import common.exception.TargetNotFoundException;
import common.message.HintMessage;
import data.db.core.ConnectionPool;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import server.data.service.RepositoryRequestHandler;

class RepositoryRequestHandlerDefault extends RepositoryRequestHandler {

	private MassiveDataGetter massive = 
			DataServiceFactory.getInstance().getMassiveDataGetter();
	private SpecificDataGetter specific =
			DataServiceFactory.getInstance().getSpecificDataGetter();
	private Gson gson = new Gson();
	
	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		PrintWriter out = null;
		try {
			out = httpResponse.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String type = httpRequest.getParameter("type");
		if (type==null) {
			out.print(new HintMessage("Parameter (type) is necessary!"));
			out.close();
		}
		
		switch (type) {
		case "stat":
			printTypeStat(httpRequest, out);
			break;
		case "data":
			printTypeData(httpRequest, out);
			break;
		default:
			out.println(new HintMessage("Unknown value for parameter (type)."));
			break;
		}
		
		out.close();
	}

	private void printTypeStat(HttpServletRequest httpRequest,PrintWriter out) {
		Document result = new Document("numOfRepo",massive.getNumOfRepositories());
		out.println(result.toJson());
	}
	
	private void printTypeData(HttpServletRequest httpRequest,PrintWriter out) {
		String method = httpRequest.getParameter("method");
		if (method==null) {
			out.println(new HintMessage("Parameter (method) is necessary."));
		}
		
		switch (method) {
		case "spec":
			printTypeDataMethodSpec(httpRequest, out);
			break;
		case "search":
			printTypeDataMethodSearch(httpRequest, out);
			break;
		case "paged":
			printTypeDataMethodPaged(httpRequest, out);
			break;
		default:
			out.println(new HintMessage("Unknown value for parameter (method)."));
			break;
		}
	}
	
	private void printTypeDataMethodSpec(HttpServletRequest httpRequest,PrintWriter out) {
		String param = httpRequest.getParameter("param");
		if(param==null) {
			out.println(new HintMessage("Parameter (param) is necessary."));
			return;
		}
		
		try {
			String result = specific.getSpecificRepo(param);
			out.println(result);
		} catch (TargetNotFoundException e) {
			out.println(new HintMessage("Repository "+param+" not found."));
		}
	}
	
	private void printTypeDataMethodSearch(HttpServletRequest httpRequest,PrintWriter out) {
		//TODO
	}

	private void printTypeDataMethodPaged(HttpServletRequest httpRequest,PrintWriter out) {
		//TODO
	}
}
