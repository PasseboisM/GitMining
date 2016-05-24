package common.service;

/**
 * @author River
 * 
 * 用于表示Repository信息中的Owner，与User有很大重合部分
 */
public interface RepositoryOwner {

	public String getLogin();

	public int getId();

	public String getAvatar_url();

	public String getGravatar_id();

	public String getUrl();

	public String getHtml_url();

	public String getFollowers_url();

	public String getFollowing_url();

	public String getGists_url();

	public String getStarred_url();

	public String getSubscriptions_url();

	public String getOrganizations_url();

	public String getRepos_url();

	public String getEvents_url();
	
	public String getReceived_events_url();

	public String getType();

	public boolean isSite_admin();

}