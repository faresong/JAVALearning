/**
 * 文件下载	
 */

package com.clive.demo1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BServlet
 */
public class BServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获得当前的 servletContext
		ServletContext servletContext = this.getServletContext();
		// 不必要
		request.setCharacterEncoding("UTF-8");
		// 不必要
		response.setCharacterEncoding("UTF-8");
		String fileName = request.getParameter("fileName");
		// 不必要
		response.setContentType(servletContext.getMimeType(".jpg"));
		System.out.println(servletContext.getMimeType(".jpg"));
//		response.setContentType("image/jpeg");
		response.setHeader("content-disposition", "attachment;fileName=" + java.net.URLEncoder.encode("图片.jpg", "UTF-8"));
		OutputStream outputStream = response.getOutputStream();
		// 获得当前的 Tomcat 项目所在路径 (Tomcat webapps 路径下)
		String realPath = servletContext.getRealPath("/");
		File file = new File(realPath, "/img/"+fileName);
		BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
		int len = 0;
		byte[] buff = new byte[1024];
		while((len = fis.read(buff)) != -1){
			outputStream.write(buff, 0, len);
		}
		fis.close();
		outputStream.close();
		System.out.println(realPath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
