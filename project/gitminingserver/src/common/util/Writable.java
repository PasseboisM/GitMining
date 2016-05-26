package common.util;

import java.util.List;

public interface Writable<T> {

	/**
	 * 向通道内传入对象数组，按顺序写入缓冲
	 * @param list 存放对象的数组
	 */
	public void writeObj(T[] list);
	
	/**
	 * 向通道内传入对象列表，按顺序写入缓冲
	 * @param list 存放对象的列表
	 */
	public void writeObj(List<T> list);
	
}
