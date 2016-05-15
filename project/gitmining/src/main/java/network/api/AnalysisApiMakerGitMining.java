package network.api;

import common.enumeration.attribute.Language;
import network.api.service.AnalysisApiMaker;

public class AnalysisApiMakerGitMining implements AnalysisApiMaker {

	@Override
	public String makeRepositoryTrendingApi(Language language) {
		String api = BASE+REPO;
		String lang = language.getName();
		lang=lang.replaceAll(" ", "%20");
		lang=lang.replaceAll("#", "sharp");
		api+=lang;
		return api;
	}

	@Override
	public String makeUserTrendingApi(Language language) {
		String api = BASE+DEVELOPER;
		String lang = language.getName();
		api+=lang;
		return api;
	}

}
