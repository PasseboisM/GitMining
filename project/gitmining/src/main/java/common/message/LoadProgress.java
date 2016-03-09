package common.message;

public class LoadProgress  {

	private int totalRepoNum = 0;
	private int loadedRepoNum = 0;
	
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
	
}
