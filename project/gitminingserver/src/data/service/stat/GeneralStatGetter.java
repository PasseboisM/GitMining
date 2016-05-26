package data.service.stat;

/**
 * 本接口用于获取用于构造各种GeneralStatistics对象的JSON字符串
 * @author River
 *
 */
public interface GeneralStatGetter {
	
	public String getUserDistOverFollower();
	
	public String getRepoDistOverFork();
	
	public String getRepoDistOverLanguage();
	
	public String getRepoDistOverCreateTime();
	
	public String getUserDistOverCreateTime();
	
	public String getRepoDistOverStar();
	
	public String getUserDistOverType();
	
}
