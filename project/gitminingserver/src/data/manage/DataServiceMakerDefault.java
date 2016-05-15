package data.manage;

import javax.servlet.http.HttpServletRequest;

import data.service.DataServiceMaker;
import data.service.RepositoryRequestHandler;
import data.service.StatisticRequestHandler;
import data.service.UserRequestHandler;

public class DataServiceMakerDefault extends DataServiceMaker {

	RepositoryRequestHandler repoHandler = new RepositoryRequestHandlerDefault();
	
	@Override
	public RepositoryRequestHandler getRepositoryService() {
		return repoHandler;
	}

	@Override
	public UserRequestHandler getUserService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatisticRequestHandler getStatisticHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
