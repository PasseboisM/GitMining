package logic.service;

import java.util.List;

import common.service.Repository;

public interface RepoRelatedListGetter {
	//TODO
	public List<Repository> getRelatedRepoNames(String fullName);
	//api禁止使用，需要权限，之前只测试了用户的相关类型，没测试项目的
	//结果发现项目的api需要本人权限
	//5/17
//	public List<GitUser> getContributorNames(String fullName) throws IOException;
//	public List<GitUser> getCollaboratorNames(String fullName) throws IOException;
}
