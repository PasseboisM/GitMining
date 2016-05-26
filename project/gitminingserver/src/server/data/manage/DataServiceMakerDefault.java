package server.data.manage;

import server.data.service.DataServiceMaker;
import server.data.service.RepositoryRequestHandler;
import server.data.service.StatisticRequestHandler;
import server.data.service.UserRequestHandler;

public class DataServiceMakerDefault extends DataServiceMaker {

	private RepositoryRequestHandler repoHandler = new RepositoryRequestHandlerDefault();
	private UserRequestHandler userHandler = new UserRequestHandlerDefault();
	private StatisticRequestHandler statHandler = new StatisticRequestHandlerDefault();
	
	
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

}
