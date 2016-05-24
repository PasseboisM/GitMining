package data.storage.directory;

import common.service.GitUserMin;
import common.service.RepositoryMin;
import data.storage.directory.service.DirectoryMaker;

public class DirectoryMakerDefault implements DirectoryMaker {

	private static String root = System.getProperty("user.dir");
	private static DirectoryMakerDefault instance = new DirectoryMakerDefault();
	
	public static DirectoryMakerDefault getInstance() {
		return instance;
	}
	
	private DirectoryMakerDefault() {}

	@Override
	public String repositoryDirectory(RepositoryMin minInfo) {
		return repositoryDirectory(minInfo.getFull_name());
	}

	@Override
	public String userDirectory(GitUserMin minInfo) {
		return userDirectory(minInfo.getLogin());
	}

	@Override
	public String repositoryName(RepositoryMin minInfo) {
		return repositoryName(minInfo.getFull_name());
	}

	@Override
	public String userName(GitUserMin minInfo) {
		return userName(minInfo.getLogin());
	}

	@Override
	public String repositoryDirectory(String fullName) {
		return root+"/LocalData/repository/"+fullName.split("/")[0]+"/";
	}

	@Override
	public String repositoryName(String fullName) {
		return fullName.split("/")[1]+".txt";
	}

	@Override
	public String userDirectory(String login) {
		return root+"/LocalData/user/";
	}

	@Override
	public String userName(String login) {
		return login+".txt";
	}

	@Override
	public String repositoryRoot() {
		return root+"/LocalData/repository/";
	}

	@Override
	public String userRoot() {
		return userDirectory("");
	}
}
