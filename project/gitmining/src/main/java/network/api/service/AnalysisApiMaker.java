package network.api.service;

import common.enumeration.attribute.Language;

public interface AnalysisApiMaker {
	//TODO 如需要时间信息，把时间信息做成枚举
	public String makeRepositoryTrendingApi(Language language);
	public String makeUserTrendingApi(Language language);
}
