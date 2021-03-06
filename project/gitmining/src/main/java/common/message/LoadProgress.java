package common.message;

/**
 * 由逻辑层提供的用于提示当前数据载入情况的数据Beans
 * <h5>版本V1.0：(2016/3/24)</h5>
 * <pre><strong>包含内容：</strong>
 *   本地仓库总数
 *   已载入仓库数目
 *   已载入用户数目
 * </pre>
 *   
 * @author xjh14
 *
 */
public class LoadProgress  {

	private int totalRepoNum = -1;
	private int loadedRepoNum = -2;
	private int totalUserNum = -3;
	private int loadedUser = -4;
	
	public LoadProgress(int totalRepoNum,int loadedRepoNum,int totalUserNum,int loadedUserNum) {
		this.totalRepoNum = totalRepoNum;
		this.loadedRepoNum = loadedRepoNum;
		this.totalUserNum = totalUserNum;
		this.loadedUser = loadedUserNum;
	}

	public int getTotalRepoNum() {
		return totalRepoNum;
	}
	
	public int getTotalUserNum(){
		return totalUserNum;
	}

	public int getLoadedRepoNum() {
		return loadedRepoNum;
	}
	
	public boolean isFinished() {
		return totalRepoNum==loadedRepoNum;
	}
	
	public int getLoadedUser() {
		return loadedUser;
	}

	@Override
	public String toString() {
		return "LoadProgress [totalRepoNum=" + totalRepoNum + ", loadedRepoNum=" + loadedRepoNum + ", totalUserNum="
				+ totalUserNum + ", loadedUser=" + loadedUser + "]";
	}

	
}
