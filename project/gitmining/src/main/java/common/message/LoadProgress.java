package common.message;

public class LoadProgress  {

	private int totalRepoNum = -1;
	private int loadedRepoNum = -2;
	private int loadedUser = -3;
	
	public LoadProgress(int totalRepoNum,int loadedRepoNum,int loadedUserNum) {
		this.totalRepoNum = totalRepoNum;
		this.loadedRepoNum = loadedRepoNum;
		this.loadedUser = loadedUserNum;
	}

	public int getTotalRepoNum() {
		return totalRepoNum;
	}

	public int getLoadedRepoNum() {
		return loadedRepoNum;
	}
	
	public boolean isFinished() {
		return totalRepoNum==loadedRepoNum;
	}
	
	public String toString() {
		return "Total:"+totalRepoNum+", Loaded:"+loadedRepoNum;
	}
	
	public int getLoadedUser() {
		return loadedUser;
	}
}
