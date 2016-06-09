package data.db.service.obj;

import common.enumeration.attribute.Category;

public class CatePair {
	public CatePair(Category cate, int count) {
		super();
		this.cate = cate;
		this.count = count;
	}
	public Category cate;
	public int count;
}