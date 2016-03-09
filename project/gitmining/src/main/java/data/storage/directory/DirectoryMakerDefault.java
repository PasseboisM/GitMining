package data.storage.directory;

import common.service.GitUserMin;
import common.service.RepositoryMin;

import data.storage.directory.service.DirectoryMaker;

public class DirectoryMakerDefault implements DirectoryMaker {

	private static DirectoryMakerDefault instance = new DirectoryMakerDefault();
	
	public static DirectoryMakerDefault getInstance() {
		return instance;
	}
	
	private DirectoryMakerDefault() {}

	@Override
	public String repositoryDirectory(RepositoryMin minInfo) {
		return "/LocalData/repository/"+minInfo.getFull_name()+".rp";
	}

	@Override
	public String userDirectory(GitUserMin minInfo) {
		return "/LocalData/user/"+minInfo.getLogin()+".gu";
	}
}
