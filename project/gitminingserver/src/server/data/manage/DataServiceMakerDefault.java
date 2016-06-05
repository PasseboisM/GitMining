package server.data.manage;

import server.data.service.DataServiceMaker;
import server.data.service.LogInRequestHandler;
import server.data.service.RecommendRequestHandler;
import server.data.service.RepositoryRequestHandler;
import server.data.service.StatisticRequestHandler;
import server.data.service.UserRequestHandler;

public class DataServiceMakerDefault extends DataServiceMaker {

	private RepositoryRequestHandler repoHandler = new RepositoryRequestHandlerDefault();
	private UserRequestHandler userHandler = new UserRequestHandlerDefault();
	private StatisticRequestHandler statHandler = new StatisticRequestHandlerDefault();
	private RecommendRequestHandler recommendHandler = new RecommendRequestHandlerDefault();
	private LogInRequestHandler logInHandler = new LogInRequestHandlerDefault();
	
	@Override
	public RepositoryRequestHandler getRepositoryService() {
		return repoHandler;
	}

	@Override
	public UserRequestHandler getUserService() {
		return userHandler;
	}

	@Override
	public StatisticRequestHandler getStatisticHandler() {
		return statHandler;
	}

	@Override
	public RecommendRequestHandler getRecommendHandler() {
		return recommendHandler;
	}

	@Override
	public LogInRequestHandler getLogInRequestHandler() {
		return logInHandler;
	}

}
