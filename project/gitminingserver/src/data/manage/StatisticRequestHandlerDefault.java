package data.manage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.service.StatisticRequestHandler;

class StatisticRequestHandlerDefault extends StatisticRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		PrintWriter out = null;
		String type = null;
		
		try {
			type = httpRequest.getParameter("type");
			out = httpResponse.getWriter();
		} catch (Exception e) {
			return;
		}
		
		switch (type) {
		case "general":
			break;
		case "user":
			break;
		case "repo":
			break;
		default:
			out.print("{\"message\":\"Unknown type.\"}");
			break;
		}
		
		out.close();
	}
	
	public void getGeneralStat (HttpServletRequest httpRequest,PrintWriter out) {
		String type = null;
		String result = null;
		try {
			type = httpRequest.getParameter("param");
		} catch (Exception e) {
			return;
		}
		
		/**
		 * UserDistOverFollower
					RepoDistOverFork
					RepoDistOverLanguage
					RepoDistOverCreateTime
					UserDistOverCreateTime
					RepoDistOverStar
					UserDistOverType	
		 */
		switch (type) {
		//TODO 实现数据返回（由于实际上数据是不变的，所以可以静态存储）
		case "UserDistOverFollower":
			result = null;
			break;
		case "RepoDistOverFork":
			break;
		case "RepoDistOverLanguage":
			break;
		case "RepoDistOverCreateTime":
			break;
		case "UserDistOverCreateTime":
			break;
		case "RepoDistOverStar":
			break;
		case "UserDistOverType":
			break;
		default:
			break;
		}
		
		out.print(result);

	}
	
	public void getRepoStat (HttpServletRequest httpRequest,PrintWriter out) {
		
	}

	public void getUserStat (HttpServletRequest httpRequest,PrintWriter out) {
	
	}

}
