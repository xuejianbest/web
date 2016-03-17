package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.loushang.internet.request.RequestWrapper;

public class FileForm extends HttpServlet {
	private static final long serialVersionUID = -5269517322198182876L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//		Enumeration<String> contextParam = getServletContext().getInitParameterNames();
//		while(contextParam.hasMoreElements()){
//			String name = contextParam.nextElement();
//			System.out.println(String.format("name: %s, value: %s", name, getServletContext().getInitParameter(name)));
//		}
//		
//		String initParam = getServletConfig().getInitParameter("test_initParam");
//		
//		request.setAttribute("name", initParam);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/test.jsp");
//		dispatcher.forward(request, response);
		
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/index.jsp");
		System.out.println(".....get");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		System.out.println("...post");
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			System.out.println(String.format("name: %s, value: %s", name, request.getParameter(name)));
		}
		
		request.setCharacterEncoding("UTF-8");
		
		RequestWrapper req = null;
		if(request instanceof RequestWrapper){
			request = (RequestWrapper)request;
		}else{
//			request = new javax.servlet.http.HttpServletRequestWrapper(request);
			req = new RequestWrapper(request);
		}
		req.setEncoding("ISO8859-1", "UTF-8");
		
		file(req);
	}
	
	private void file(HttpServletRequest request){
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			System.out.println("....false");
		}
		try {
			// 获取文件对象
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
		//	upload.setHeaderEncoding("utf-8");
			List<FileItem> items;
			items = upload.parseRequest(request);
			System.out.println(items);
			
			Iterator<FileItem> itr = items.iterator();
			// 依次处理input
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {// 如果是普通表单项目，非文件字段。
					String fieldName = item.getFieldName();
//					if (fieldName.equals("third_group_code")) {
//						third_group_code = item.getString();
//
//					}
				} else {
					// 获得上传文件表单name
					// String fieldName = item.getFieldName();
					// 获得文件名
					// String fileName=item.getName();
					long size = item.getSize();
					// 需要测试编码问题
					// jsonStr = new
					// String(item.getString().getBytes("iso-8859-1"),"UTF-8");
					InputStream inputObject = item.getInputStream();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(inputObject));
					// StringBuilder strBuilder = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						System.out.println(line + "....");
						
					}
					// jsonStr = strBuilder.toString();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
