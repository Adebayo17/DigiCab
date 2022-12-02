package com.patient;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {
	
	public AuthorizationFilter() {}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if (reqURI.indexOf("/login-patient.xhtml") >= 0
					|| (ses != null && ses.getAttribute("email") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/faces/login-patient.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		try {

			HttpServletRequest reqt1 = (HttpServletRequest) request;
			HttpServletResponse resp1 = (HttpServletResponse) response;
			HttpSession ses1 = reqt1.getSession(false);

			String reqURI1 = reqt1.getRequestURI();
			if (reqURI1.indexOf("/admin-patient.xhtml") >= 0
					|| (ses1 != null && ses1.getAttribute("email") != null)
					|| reqURI1.indexOf("/public/") >= 0
					|| reqURI1.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else
				resp1.sendRedirect(reqt1.getContextPath() + "/inscription-patient.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	@Override
	public void destroy() {

	}

}
