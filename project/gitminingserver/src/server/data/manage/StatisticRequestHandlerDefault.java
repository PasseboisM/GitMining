package server.data.manage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.message.HintMessage;
import data.service.DataServiceFactory;
import data.service.StatDataMakerFactory;
import data.service.stat.GeneralStatGetter;
import server.data.service.StatisticRequestHandler;

class StatisticRequestHandlerDefault extends StatisticRequestHandler {

	private GeneralStatGetter generalStat = null;
	
	public StatisticRequestHandlerDefault() {
		StatDataMakerFactory statData = DataServiceFactory.getInstance().getStatDataMakerFactory();
		
		generalStat = statData.getGeneralStatGetter();
	}
	
	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		PrintWriter out = null;
		String type = null;
		
		try {
			type = httpRequest.getParameter("type");
			out = httpResponse.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		if (type==null) {
			out.println(new HintMessage("Parameter (type) is necessary.").toJSON());
			out.close();
			return;
		}
		
		switch (type) {
		case "general":
			printGeneralStat(httpRequest, out);
			break;
		case "user":
			printUserStat(httpRequest, out);
			break;
		case "repo":
			printRepoStat(httpRequest, out);
			break;
		default:
			out.print(new HintMessage("Unknown statistics type.").toJSON());
			break;
		}
		
		out.close();
	}
	
	public void printGeneralStat (HttpServletRequest httpRequest,PrintWriter out) {
		String type = null;
		String result = null;
		
		try {
			type = httpRequest.getParameter("param");
			if (type==null) {
				out.println(new HintMessage("No param for statistics name!").toJSON());
			}
		} catch (Exception e) {
			return;
		}
		
		switch (type) {
		case "UserDistOverFollower":
			result = generalStat.getUserDistOverFollower();
			break;
		case "RepoDistOverFork":
			result = generalStat.getRepoDistOverFork();
			break;
		case "RepoDistOverLanguage":
			result = generalStat.getRepoDistOverLanguage();
			break;
		case "RepoDistOverCreateTime":
			result = generalStat.getRepoDistOverCreateTime();
			break;
		case "UserDistOverCreateTime":
			result = generalStat.getUserDistOverCreateTime();
			break;
		case "RepoDistOverStar":
			result = generalStat.getRepoDistOverStar();
			break;
		case "UserDistOverType":
			result = generalStat.getUserDistOverType();
			break;
		default:
			result = new HintMessage("Cannot find general statistics type ("+type+").").toJSON();
			break;
		}
		
		out.print(result);

	}
	
	public void printRepoStat (HttpServletRequest httpRequest,PrintWriter out) {
		//TODO RepoRank
	}

	public void printUserStat (HttpServletRequest httpRequest,PrintWriter out) {
		//TODO UserRank
	}

}
