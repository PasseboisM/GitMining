package common.service;

public interface RepositoryOrganization {
	/*"organization":{
	 * 				"login":"rubinius",
	 * 				"id":317747,
	 * 				"avatar_url":"https://avatars.githubusercontent.com/u/317747?v\u003d3",
	 * 				"gravatar_id":"",
	 * 				"url":"https://api.github.com/users/rubinius",
	 * 				"html_url":"https://github.com/rubinius",
	 * 				"followers_url":"https://api.github.com/users/rubinius/followers",
	 * 				"following_url":"https://api.github.com/users/rubinius/following{/other_user}",
	 * 				"gists_url":"https://api.github.com/users/rubinius/gists{/gist_id}",
	 * 				"starred_url":"https://api.github.com/users/rubinius/starred{/owner}{/repo}",
	 * 				"subscriptions_url":"https://api.github.com/users/rubinius/subscriptions",
	 * 				"organizations_url":"https://api.github.com/users/rubinius/orgs",
	 * 				"repos_url":"https://api.github.com/users/rubinius/repos",
	 * 				"events_url":"https://api.github.com/users/rubinius/events{/privacy}",
	 * 				"received_events_url":"https://api.github.com/users/rubinius/received_events",
	 * 				"type":"Organization",
	 * 				"site_admin":false
	 * 				},
	 */
	
	public String getLogin();
	public int getId();
	public String getAvatar_url();
	public String getGravatar_id();
	public String getUrl();
	public String getHtml_url();
	public String getFollowers_url();
	public String getFollowing_url();
	public String getGist_url();
	public String getStarred_url();
	public String getSubscriprions_url();
	public String getOrganizations_url();
	public String getRepos_url();
	public String getEvents_url();
	public String getReceived_events_url();
	public String getType();
	public boolean isSite_admin();
}
