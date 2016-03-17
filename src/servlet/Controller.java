package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = -5269517322198182877L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Enumeration<String> contextParam = getServletContext().getInitParameterNames();
		while(contextParam.hasMoreElements()){
			String name = contextParam.nextElement();
			System.out.println(String.format("name: %s, value: %s", name, getServletContext().getInitParameter(name)));
		}
		
		String initParam = getServletConfig().getInitParameter("test_initParam");
		
		request.setAttribute("name", initParam);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/test.jsp");
		dispatcher.forward(request, response);
		
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/index.jsp");
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println("...post");
		
//		Enumeration<String> names = request.getParameterNames();
//		while(names.hasMoreElements()){
//			String name = names.nextElement();
//			String[] values = request.getParameterValues(name);
//			for(String value: values){
//				System.out.println(String.format("name: %s, value: %s", name, value));
//			}
//		}
		int i = Integer.parseInt(request.getParameter("i"));
		System.out.println(i);
		response.getWriter().write("...doPost");
	}
}
