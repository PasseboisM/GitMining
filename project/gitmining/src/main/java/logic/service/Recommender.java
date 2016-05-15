package logic.service;

import java.util.List;

import common.service.GitUser;
import common.service.Repository;

public interface Recommender {
	public List<Repository> getRecommendRepos();
	public List<GitUser> getRecommendUsers();
}
