package common.util;

import java.util.List;

/**
 * 
 * @author River
 *
 * 用于进行Pipeline&Filter模型传输的管道
 * 
 * @param <T> 本通道用于传输的对象类型
 * 实现时注意同步性问题
 */
public interface  ObjChannel<T> {

	/**
	 * 向通道内传入对象数组，按顺序写入缓冲
	 * @param list 存放对象的数组
	 */
	public void writeObj(T[] list);
	
	/**
	 * 获取通道内的一组对象
	 * @param maxNum 获取的List的最大容量（当通道内数据不足时，其大小小于maxNum）
	 * @return 存放对象的列表
	 */
	public List<T> getObj(int maxNum);
	
	/**
	 * 关闭此通道，数据源声明不会向此通道内继续增加数据
	 */
	public void close();
	
	/**
	 * 通道内是否还有缓冲数据（由数据接收方查询用）
	 * @return 是否还有缓冲数据
	 */
	public boolean hasMore();
}
