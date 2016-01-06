package com.earl.apply.helper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate工具类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class HibernateHelper {

	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager
			.getLogger(HibernateHelper.class);
	
    /**
     * Session工厂.
     */
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * 私有构造方法.
     * 
     */
    private HibernateHelper() {
    }

    /**
     * 创建Session工厂.
     * 
     * @return Session工厂.
     */
    private static SessionFactory buildSessionFactory() {
        SessionFactory result = null;
        try {
            // 根据hibernate.cfg.xml创建Session工厂
        	logger.info("开始创建session工厂");
            Configuration cfg = new Configuration().configure();
            
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
            result = cfg.buildSessionFactory(ssrb.applySettings(cfg.getProperties()).build());
            logger.info("session工厂创建完成");
        } catch (Throwable e) {
            // 记录异常
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return 获取的sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

}
