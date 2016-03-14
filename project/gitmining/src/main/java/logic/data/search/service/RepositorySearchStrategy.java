package logic.data.search.service;

import common.param_obj.RepositorySearchParam;
import common.service.RepositoryMin;

/**
 * <body>
 * 	<h4>利用RepositorySearchParam匹配仓库的替换策略</h4>
 * 	<hr />
 * </body>
 * 
 * @author xjh14
 *
 */
public interface RepositorySearchStrategy {

	/**
	 * <body>
	 * <p>
	 * 	依赖：RepositorySearchParam与RepositoryMin的一系列getter接口
	 * 	<br />
	 * 	返回：double值表示进行匹配的对象与搜索参数的相关性。
	 * 	为0.0时表示不符合搜索参数，对象不应成为搜索结果；数值越大表示对象越符合参数要求。
	 * </p>
	 * </body>
	 * @param obj
	 * @param params
	 * @return
	 */
	public double match(RepositoryMin obj, RepositorySearchParam params);
	
}
