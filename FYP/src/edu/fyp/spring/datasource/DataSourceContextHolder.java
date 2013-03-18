package edu.fyp.spring.datasource;

public class DataSourceContextHolder {

	private static final ThreadLocal contextHolder = new ThreadLocal(); // 线程本地环境

	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);

	}

	public static String getDataSourceType() {
		return (String) contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}