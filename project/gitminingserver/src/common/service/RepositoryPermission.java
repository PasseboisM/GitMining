package common.service;

public interface RepositoryPermission {
	/*"permissions":{
	 * 				"admin":false,
	 * 				"push":false,
	 * 				"pull":true
	 * 				},
	 */
	public boolean isAdmin();
	public boolean isPush();
	public boolean isPull();
}
