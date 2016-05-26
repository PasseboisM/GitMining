package common.exception;

/**
 * 可由本地数据存储模块抛出，表示查询的数据不存在于本地，应使用替换数据源。<br />
 * 建议处理方式：从替换数据源（Internet等）获取数据之后，向本地数据存储保存数据。
 * @author xjh14
 *
 */
public class TargetNotFoundException extends Exception {

	private static final long serialVersionUID = -3616920705923515502L;

}
