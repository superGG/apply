package com.earl.apply.interceptor;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.earl.apply.helper.EhCacheHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * ip拦截器.
 * 
 * @author 黄祥谦.
 */
public class IpInterceptor extends AbstractInterceptor {

	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager
			.getLogger(IpInterceptor.class);
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private static Cache IP_LOGIN = EhCacheHelper.getSessionFactory().getCache("iplocal");
	
	@Override
	public final String intercept(final ActionInvocation invocation)
			throws Exception {
		logger.info("进入ip拦截器");
		// 获取参数
		ActionContext ctx = invocation.getInvocationContext();
		// 获取HttpServletRequest
		HttpServletRequest request = (HttpServletRequest) ctx
				.get(StrutsStatics.HTTP_REQUEST);

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println("ServletPath|ip|remote ip address|remote name|remote port|LocalAddress|LocalPort|char encodeing|ContentType|AuthType|PathTranslated|RemoteUser");
		System.out.println(request.getServletPath()+"|"+ip+"|" + request.getRemoteAddr()+"|"
				+ request.getRemoteHost()+"|" + request.getRemotePort()+"|"
				+ request.getLocalAddr()+"|" + request.getLocalPort()+"|"
				+ request.getCharacterEncoding()+"|" + request.getContentType()+"|"
				+ request.getAuthType()+"|" + request.getPathTranslated()+"|"
				+ request.getRemoteUser());
		
		Element element = IP_LOGIN.get(ip);
		if(element!= null){
			Integer objectValue = (Integer) element.getObjectValue();
			if(objectValue >3){ //硬编码，默认每个用户有三次机会
				logger.info("该ip在100分钟内重复提交数据3次");
				return null;
			}else{
				IP_LOGIN.remove(ip);
				IP_LOGIN.put(new Element(ip,objectValue+1));
				invocation.invoke();
				return null;
			}
		}
		IP_LOGIN.put(new Element(ip, 1));//ip访问次数默认是1次
		logger.info("退出ip拦截器");
		invocation.invoke();
		return null;
	}
}
