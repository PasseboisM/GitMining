package common.util;

import java.util.List;

/**
 * 用于将多个数据源合为一个的集流器，
 * 能够帮助ObjChannel支持多数据源写入
 * @author xjh14
 */
public interface MultiSourceSwitch<T> {

	/**
	 * 向本集流器注册数据源，只有注册过的数据源才可以向本集流器写入数据
	 * @param source 待注册的数据源
	 */
	public void register(Object source);
	
	/**
	 * 向集流器目标管道写入对象数组
	 * @param toBeWritten 待写入的对象数组
	 */
	public void write(T[] toBeWritten);
	
	/**
	 * 向集流器目标管道写入对象列表
	 * @param toBeWritten 待写入的对象列表
	 */
	public void write(List<T> toBeWritten);
	
	/**
	 * 向集流器注销本数据源，声明此数据源不会再写入数据
	 * @param source 待注销的数据源对象
	 */
	public void deregister(Object source);
	
	/**
	 * 向集流器声明数据源错误（声明此数据源由于异常原因不会再写入数据）
	 * @param source 声明异常的数据源
	 */
	public void declareException(Object source);
	
}
