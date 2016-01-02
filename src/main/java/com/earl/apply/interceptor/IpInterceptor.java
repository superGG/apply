package com.earl.apply.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

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
     * 
     */
	private static final long serialVersionUID = 1L;

	@Override
	public final String intercept(final ActionInvocation invocation)
			throws Exception {
		System.out.println("进入ip拦截器");
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
		System.out.println("退出ip拦截器");
		invocation.invoke();
		return null;
	}
}
