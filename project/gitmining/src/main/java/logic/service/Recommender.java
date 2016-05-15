package logic.service;

import java.util.List;

import common.exception.NetworkException;
import common.service.GitUser;
import common.service.Repository;

public interface Recommender {
	public List<Repository> getRecommendRepos() throws NetworkException;
	public List<GitUser> getRecommendUsers() throws NetworkException;
}
