package data.db.core;

public enum GitCollections {
	REPOSITORY("repository"),USER("user");
	
	private String name;
	private GitCollections(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
