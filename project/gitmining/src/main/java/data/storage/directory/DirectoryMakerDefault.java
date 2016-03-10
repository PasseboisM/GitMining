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

	public static void main(String[] args) {
		System.out.println(root);
	}
	
	@Override
	public String repositoryDirectory(RepositoryMin minInfo) {
		String owner = minInfo.getFull_name().split("/")[0];
		return root+"\\LocalData\\repository\\"+owner+"\\";
	}

	@Override
	public String userDirectory(GitUserMin minInfo) {
		return root+"\\LocalData\\user\\";
	}

	@Override
	public String repositoryName(RepositoryMin minInfo) {
		return minInfo.getFull_name().split("/")[1]+".txt";
	}

	@Override
	public String userName(GitUserMin minInfo) {
		return minInfo.getLogin()+".txt";
	}
}
