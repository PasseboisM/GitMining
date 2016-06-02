package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.TargetNotFoundException;
import common.message.HintMessage;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import server.data.service.RepositoryRequestHandler;

class RepositoryRequestHandlerDefault extends RepositoryRequestHandler {

	private MassiveDataGetter massive = 
			DataServiceFactory.getInstance().getMassiveDataGetter();
	private SpecificDataGetter specific =
			DataServiceFactory.getInstance().getSpecificDataGetter();
	
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
			out.print(new HintMessage("Parameter (type) is necessary!").toJSON());
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
			out.println(new HintMessage("Unknown value for parameter (type).").toJSON());
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
			out.println(new HintMessage("Parameter (method) is necessary.").toJSON());
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
			out.println(new HintMessage("Unknown value for parameter (method).").toJSON());
			break;
		}
	}
	
	private void printTypeDataMethodSpec(HttpServletRequest httpRequest,PrintWriter out) {
		String param = httpRequest.getParameter("param");
		if(param==null) {
			out.println(new HintMessage("Parameter (param) is necessary.").toJSON());
			return;
		}
		
		try {
			String result = specific.getSpecificRepo(param);
			out.println(result);
		} catch (TargetNotFoundException e) {
			out.println(new HintMessage("Repository "+param+" not found.").toJSON());
		}
	}
	
	private void printTypeDataMethodSearch(HttpServletRequest httpRequest,PrintWriter out) {
		//TODO
	}

	private void printTypeDataMethodPaged(HttpServletRequest httpRequest,PrintWriter out) {
		int page = 0,numPerPage = 0;
		RepoSortStadard sort = RepoSortStadard.NO_SORT;
		
		try {
			page = Integer.parseInt(httpRequest.getParameter("page"));
			numPerPage = Integer.parseInt(httpRequest.getParameter("numPerPage"));
			sort = RepoSortStadard.fromName(httpRequest.getParameter("sort"));
		} catch (Exception e) {
			out.println(new HintMessage("Please check your request parameter.").toJSON());
			return;
		}
		List<String> result = massive.getRepositories(page, numPerPage, sort);
		out.print('[');
		for (int i = 0; i < result.size(); i++) {
			out.print(result.get(i));
			if(i!=result.size()-1)	out.print(",");
		}
		out.print(']');
	}
}
