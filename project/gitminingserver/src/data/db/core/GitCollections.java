package data.db.core;

public enum GitCollections {
	REPOSITORY("repository"),USER("user"),ANALYSIS("system_user_info");
	
	private String name;
	private GitCollections(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
