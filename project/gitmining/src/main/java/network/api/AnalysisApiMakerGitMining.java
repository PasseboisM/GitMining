package network.api;

import common.enumeration.attribute.Language;
import network.api.service.AnalysisApiMaker;

public class AnalysisApiMakerGitMining implements AnalysisApiMaker {

	@Override
	public String makeRepositoryTrendingApi(Language language) {
		return makeUrlByType(REPO,language);
	}

	@Override
	public String makeUserTrendingApi(Language language) {
		return makeUrlByType(DEVELOPER,language);
	}

	private String makeUrlByType(String type,Language language) {
		String api = BASE+type;
		String lang = getStandardizeRequire(language);
		api+=lang;
		return api;
	}

	private String getStandardizeRequire(Language language) {
		if(language==null)	return "";
		String lang = language.getName();
		lang=lang.replaceAll(" ", "%20");
		lang=lang.replaceAll("#", "sharp");
		return lang;
	}
}
