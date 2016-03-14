package logic.data.search;

import common.enumeration.attribute.Language;
import common.param_obj.RepositorySearchParam;
import common.service.RepositoryMin;
import logic.data.search.service.RepositorySearchStrategy;

public class RepoSearchStrategyDefault implements RepositorySearchStrategy {

	@Override
	public double match(RepositoryMin obj, RepositorySearchParam params) {
		
		double matchCount = 0;
		
		boolean languageMatched = false;
		
		for(Language lang:params.getLangs()) {
			if(lang==obj.getMainLanguage()) {
				matchCount += 5;
				languageMatched = true;
				break;
			} else if(lang==Language.ALL) {
				languageMatched = true;
				break;
			}
		}
		
		
		boolean categoryMatched = true;
//		for(Category cate:cates) {
//			for(Category minCate:minInfo.getCategories()) {
//				if(cate==minCate) {
//					matchCount++;
//					break;
//				}
//			}
//		}
		
		boolean keywordMatched = false;
		
		if(params.getKeywords().length==0) {
			keywordMatched = true;
		} else {
			for(String keyword:params.getKeywords()) {
				if(obj.getFull_name().contains(keyword)&&(!keyword.equals(""))) {
					matchCount += 10;
					keywordMatched = true;
				} else if(keyword.equals("")) {
					keywordMatched = true;
				}
			}
		}
		

		//If the object cannot match the choice params, it dosen't match the whole search param.
		boolean choicesMatched = languageMatched && categoryMatched;
		
		if(choicesMatched) {
			return Math.max(1,matchCount);
		} else {
			return 0.0;
		}
		
	}

}
