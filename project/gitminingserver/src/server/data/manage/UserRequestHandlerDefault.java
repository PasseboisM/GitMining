package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;

import common.enumeration.sort_standard.UserSortStandard;
import common.exception.TargetNotFoundException;
import common.message.HintMessage;
import common.param_obj.UserSearchParam;
import static common.util.JSONHelper.printJSONList;

import data.analysis.service.AnalysisServiceFactory;
import data.analysis.service.RequestRecorder;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import server.data.service.UserRequestHandler;

class UserRequestHandlerDefault extends UserRequestHandler {

	private MassiveDataGetter massive = 
			DataServiceFactory.getInstance().getMassiveDataGetter();
	private SpecificDataGetter specific =
			DataServiceFactory.getInstance().getSpecificDataGetter();
	
	private RequestRecorder recorder = AnalysisServiceFactory.getRecorder();
	
	private Gson gson = new Gson();
	
	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setCharacterEncoding("unicode");
		
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

		
		recorder.record(httpRequest);
	}

	private void printTypeData(HttpServletRequest httpRequest, PrintWriter out) {
		String method = null;
		try {
			method = httpRequest.getParameter("method");
			if (method==null) throw new Exception();
		} catch (Exception e) {
			out.println(new HintMessage("Parameter (method) is necessary!").toJSON());
			return;
		}
		
		switch (method) {
		case "spec":
			printTypeDataMethodSpec(httpRequest, out);
			break;
		case "paged":
			printTypeDataMethodPaged(httpRequest, out);
			break;
		case "search":
			printTypeDataMethodSearch(httpRequest, out);
			break;
		default:
			out.println(new HintMessage("Unspecified value for parameter (method).").toJSON());
			return;
		}

	}

	private void printTypeStat(HttpServletRequest httpRequest, PrintWriter out) {
		
		Document result = new Document("numOfUser",massive.getNumOfUsers());
		out.println(result.toJson());
	}

	private void printTypeDataMethodSpec(HttpServletRequest httpRequest, PrintWriter out) {
		String login = null;
		String result = null;
		try {
			login = httpRequest.getParameter("param");
			if (login==null) throw new Exception();
		} catch (Exception e) {
			out.println(new HintMessage("Parameter (param) is necessary"
					+ " to specify the login of user.").toJSON());
			return;
		}
		
		try {
			result = specific.getSpecificGitUser(login);
		} catch (TargetNotFoundException e) {
			out.println(new HintMessage("User '"+login+"' not found!").toJSON());
			return;
		}
		
		out.print(result);
	}
	
	private void printTypeDataMethodPaged(HttpServletRequest httpRequest, PrintWriter out) {
		String page = null,numPerPage = null,sort = null;
		List<String> result = null;
		
		try {
			page = httpRequest.getParameter("page");
			numPerPage = httpRequest.getParameter("numPerPage");
			sort = httpRequest.getParameter("sort");
			if (page==null||numPerPage==null||sort==null) throw new Exception();
			result = massive.getUsers(
					Integer.parseInt(page),
					Integer.parseInt(numPerPage),
					UserSortStandard.getStandard(sort));
		} catch (Exception e) {
			out.println(new HintMessage("Please check if params are enough.").toJSON());
			return;
		}
		
		printJSONList(out, result);
	}

	private void printTypeDataMethodSearch(HttpServletRequest httpRequest, PrintWriter out) {
		UserSearchParam param = null;
		
		try {
			String s = httpRequest.getParameter("param");
			if (s==null) throw new Exception();
			param = gson.fromJson(s, UserSearchParam.class);
			if (param.getKeywords()==null||param.getSortStandard()==null) {
				throw new Exception();
			}
		} catch (Exception e) {
			out.print(new HintMessage("Parameter (param) in bad format!").toJSON());
			return;
		}
		
		List<String> result = massive.searchUser(param);
		
		printJSONList(out,result);
		
	}
}
