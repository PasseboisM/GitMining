package server.data.manage;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.exception.TargetNotFoundException;
import common.message.HintMessage;
import data.service.DataServiceFactory;
import data.service.StatDataMakerFactory;
import data.service.stat.GeneralStatGetter;
import data.service.stat.RepoStatGetter;
import data.service.stat.UserStatGetter;
import server.data.service.StatisticRequestHandler;

class StatisticRequestHandlerDefault extends StatisticRequestHandler {

	private GeneralStatGetter generalStat = null;
	private UserStatGetter userStat = null;
	private RepoStatGetter repoStat = null;
	
	public StatisticRequestHandlerDefault() {
		StatDataMakerFactory statData = DataServiceFactory.getInstance().getStatDataMakerFactory();
		
		generalStat = statData.getGeneralStatGetter();
		userStat = statData.getUserStatGetter();
		repoStat = statData.getRepoStatGetter();
	}
	
	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		httpResponse.setCharacterEncoding("unicode");
		
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
		String fullName = null;
		try {
			fullName = httpRequest.getParameter("param");
			if(fullName==null) throw new Exception();
		} catch (Exception e) {
			out.println(new HintMessage("Parameter (param)"
					+ " as the full name is necessary.").toJSON()); 
			return;
		}
		
		try {
			out.print(repoStat.getRepoRanks(fullName));
		} catch (TargetNotFoundException e) {
			out.print(new HintMessage("Cannot find repository '"+fullName+"'!").toJSON());
		}
	}

	public void printUserStat (HttpServletRequest httpRequest,PrintWriter out) {
		String login = null;
		try {
			login = httpRequest.getParameter("param");
			if(login==null) throw new Exception();
		} catch (Exception e) {
			out.println(new HintMessage("Parameter (param)"
					+ " as the login is necessary.").toJSON()); 
			return;
		}
		
		try {
			out.print(userStat.getUserRanks(login));
		} catch (TargetNotFoundException e) {
			out.print(new HintMessage("Cannot find user '"+login+"'!").toJSON());
		}
	}

}
