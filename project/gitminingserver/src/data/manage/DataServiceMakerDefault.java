package data.manage;

import data.service.DataServiceMaker;
import data.service.RepositoryRequestHandler;
import data.service.StatisticRequestHandler;
import data.service.UserRequestHandler;

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
