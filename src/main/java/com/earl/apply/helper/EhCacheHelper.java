package com.earl.apply.helper;

import java.net.URL;

import net.sf.ehcache.CacheManager;

/**
 * @author 黄祥谦.
 * @date:2016-1-4 上午11:32:33
 * @version :
 */
public class EhCacheHelper {

	/**
	 * Session工厂.
	 */
	private static final CacheManager IP_CACHE = buildCacheManage();

	/**
	 * 私有构造方法.
	 * 
	 */
	private EhCacheHelper() {
	}

	private static CacheManager buildCacheManage() {
		URL url = EhCacheHelper.class.getResource("/ehcache-ip.xml");
		return CacheManager.create(url);
	}

	/**
	 * @return 获取的sessionFactory
	 */
	public static CacheManager getSessionFactory() {
		return IP_CACHE;
	}
}
