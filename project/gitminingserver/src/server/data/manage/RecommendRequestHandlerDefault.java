package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import common.message.HintMessage;
import static common.util.JSONHelper.printJSONList;
import data.service.DataServiceFactory;
import data.service.RecommendDataGetter;
import data.service.UserListDataGetter;
import server.data.service.RecommendRequestHandler;

public class RecommendRequestHandlerDefault implements RecommendRequestHandler {

	private RecommendDataGetter recommend = 
			DataServiceFactory.getInstance().getRecommendDataGetter();
	private UserListDataGetter userList = 
			DataServiceFactory.getInstance().getUserListDataGetter();
	
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
		case "repo":
			printTypeRepo(httpRequest, out);
			break;
		case "user":
			printTypeUser(httpRequest, out);
			break;
		case "related":
			printTypeRelated(httpRequest, out);
			break;
		default:
			out.println(new HintMessage("Unknown value for parameter (type).").toJSON());
			break;
		}
		
		out.close();
		
	}
	
	private void printTypeRepo(HttpServletRequest httpRequest, PrintWriter out) {
		String param = null;
		
		try {
			param = httpRequest.getParameter("param");
			if (param==null) throw new Exception();
		} catch (Exception e) {
			out.print(new HintMessage("Parameter (param) is necessary!").toJSON());
			return;
		}
		
		Language lang = Language.getLanguage(param);
		
		List<String> result = null;
		try {
			//TODO 使用上key所对应的用户历史信息
			result = recommend.getRecommendRepos(lang);
		} catch (NetworkException e) {
			e.printStackTrace();
			out.print(new HintMessage("Unknown network error."
					+ " Cannot get from GitHub server!").toJSON());
			return;
		}
		
		printJSONList(out, result);
	}
	
	private void printTypeUser(HttpServletRequest httpRequest, PrintWriter out) {
		String param = null;
		
		try {
			param = httpRequest.getParameter("param");
			if (param==null) throw new Exception();
		} catch (Exception e) {
			out.print(new HintMessage("Parameter (param) is necessary!").toJSON());
			return;
		}
		
		Language lang = Language.getLanguage(param);
		List<String> result = null;
		try {
			result = recommend.getRecommendUsers(lang);
		} catch (NetworkException e) {
			e.printStackTrace();
			out.print(new HintMessage("Unknown network error."
					+ " Cannot get from GitHub server!").toJSON());
			return;
		}
		
		printJSONList(out, result);
	}
	
	private void printTypeRelated(HttpServletRequest httpRequest, PrintWriter out) {
		String param = null, login = null;
		
		try {
			param = httpRequest.getParameter("param");
			login = httpRequest.getParameter("login");
			if (param==null || login==null) {
				throw new Exception();
			}
		} catch (Exception e) {
			out.print(new HintMessage("Please check for missed parameter.").toJSON());
			return;
		}
		
		List<String> result = null;
		
		/**
		 * OwnedRepositories/
		   StarredRepositories/
		   SubscrippedRepositories/
		   Followers/
		   Followings
		 */
		try {
			switch (param) {
			case "OwnedRepositories":
				result = userList.getOwnedRepositories(login);
				break;
			case "StarredRepositories":
				result = userList.getStarredRepositories(login);
				break;
			case "SubscrippedRepositories":
				result = userList.getSubscrippedRepositories(login);
				break;
			case "Followers":
				result = userList.getFollowers(login);
				break;
			case "Followings":
				result = userList.getFollowings(login);
				break;
			default:
				out.println(new HintMessage("Unknown value for (param)."));
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
			out.println(new HintMessage("Failure to get information from "
					+ "GitHub server.").toJSON());
			return;
		}
		
		printJSONList(out, result);
	}
	
}
