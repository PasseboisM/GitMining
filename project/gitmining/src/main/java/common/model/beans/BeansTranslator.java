package common.model.beans;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;

public class BeansTranslator {
	
	/**
	 * 获取接口对应的Beans模型
	 * TODO 将职责分散给各个接口
	 * @param imp 接口类型
	 * @return Beans类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getBeans(Class imp) {
		if(imp==Repository.class) {
			return RepositoryBeans.class;
		} else if(imp==RepositoryMin.class) {
			return RepositoryMinBeans.class;
		} else if(imp==GitUserMin.class) {
			return GitUserMinBeans.class;
		} else if (imp==GitUser.class) {
			return GitUserBeans.class;
		} else {
			return null;
		}
	}
}
