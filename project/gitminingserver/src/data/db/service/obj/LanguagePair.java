package data.db.service.obj;

import common.enumeration.attribute.Language;

public class LanguagePair {
	public LanguagePair(Language lang, int count) {
		super();
		this.lang = lang;
		this.count = count;
	}
	public Language lang;
	public int count;
}