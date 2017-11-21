package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		System.out.println("LoginFilter����-jingtao");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		//��������init-param��·��,�����ͨ��
		String sp = request.getServletPath();
		for(String p : paths) {
			if(p.equals(sp)) {
				chain.doFilter(req,res);
				return;
			}
		}
		
		//���session,û��½�˺žͷ��ص�½ҳ
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		System.out.println("a");
		if(code == null) {
			System.out.println("b");
			response.sendRedirect("tologin.login");
		} else {
			System.out.println("c");
			chain.doFilter(req,res);
		}
	}
	
	private String[] paths;
	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("LoginFilter��ʼ��-jingtao");
		String excludePath = cfg.getInitParameter("excludePath");
		paths = excludePath.split(",");
	}

}
