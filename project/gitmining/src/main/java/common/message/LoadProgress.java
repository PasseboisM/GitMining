package common.message;

public class LoadProgress  {

	private int totalRepoNum = -1;
	private int loadedRepoNum = -2;
	
	public LoadProgress(int totalRepoNum,int loadedRepoNum) {
		this.totalRepoNum = totalRepoNum;
		this.loadedRepoNum = loadedRepoNum;
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
	
}
