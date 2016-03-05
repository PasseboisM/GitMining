package common.util;

import java.util.List;

import common.exception.DataTransferException;

/**
 * 
 * @author xjh14
 *
 * 用于进行Pipeline&Filter模型传输的管道，
 * 服务模式为被动型，需要数据源、数据接收方主动调用方法获取数据
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
	 * @param maxNum 获取的List的最大容量（当通道内数据不足时，其大小小于maxNum，可能为0）
	 * @return 存放对象的列表
	 * @throws 数据源生产数据过程异常，无法继续服务
	 */
	public List<T> getObj(int maxNum) throws DataTransferException;
	
	/**
	 * 关闭此通道，数据源正常写入全部数据后，声明不会向此通道内继续增加数据
	 */
	public void close();
	
	/**
	 * 数据源发生异常，声明传输出现错误，不再向通道内写入数据
	 */
	public void closeWithException();
	
	/**
	 * 通道内是否还有缓冲数据（由数据接收方查询用）
	 * @return 是否还有缓冲数据
	 */
	public boolean hasMore();
}
