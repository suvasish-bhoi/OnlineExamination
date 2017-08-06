package com.oec.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

import com.oec.dao.ExamDAO;
import com.oec.dao.SystemDAO;
import com.oec.util.Email;
import com.oec.util.InstituteUtil;

@WebServlet("/UploadQuestionAnswerController")
public class UploadQuestionAnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
		private boolean isMultipart;
		private String filePath;
		private int maxFileSize = 100 * 1024;
		private int maxMemSize = 110 * 1024;
		private File file ;
		private int exam_id;
		private String fileName;
		
		
		public void init( ){
			Properties pp = new Properties();
			try {
				Class<UploadQuestionAnswerController> c = UploadQuestionAnswerController.class;
				ClassLoader cl = c.getClassLoader();
				pp.load(cl.getResourceAsStream("Admin.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			filePath=pp.getProperty("questionAnswer.dir");
		   }
		public void doPost(HttpServletRequest request, 
		               HttpServletResponse response)
		              throws ServletException, java.io.IOException {
			int code = InstituteUtil.getRandomNumber();
			if(request.getSession().isNew()){
				request.getRequestDispatcher("LogoutController").forward(request, response);
				return;
			}
		      isMultipart = ServletFileUpload.isMultipartContent(request);
		      
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      factory.setSizeThreshold(maxMemSize);
		      factory.setRepository(new File("‪c://ExamQuestion//"));
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      upload.setSizeMax( maxFileSize );

		      try{ 
		      List fileItems = upload.parseRequest(request);
		      Iterator i = fileItems.iterator();
		      
		      while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if ( !fi.isFormField () )	
		         {
		            String fieldName = fi.getFieldName();
		            fileName = fi.getName();
		            String contentType = fi.getContentType();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            if( fileName.lastIndexOf("\\") >= 0 ){
		               file = new File( filePath + 
		               fileName.substring( fileName.lastIndexOf("\\"))+exam_id+code) ;
		            }else{
		               file = new File( filePath + 
		               fileName.substring(fileName.lastIndexOf("\\")+1)+exam_id+code) ;
		            }
		            fi.write( file ) ;
		        
		         }else{
		        	 if(fi.getFieldName().equals("exam_id")){
		        		 exam_id = Integer.parseInt(fi.getString());
		        	 }
		         }
		      }
		      ExamDAO.saveQuestionAnswer(filePath+fileName+exam_id+code,exam_id);
		      file.delete();
		   }catch(Exception ex) {
		       System.out.println(ex);
		   }
		  file.delete();
		  request.getRequestDispatcher("instituteHome.jsp?result=Question Answer Uploaded").forward(request, response);
	}

}
