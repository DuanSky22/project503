package com.iscas.project503.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HazelcastDataAccessServlet
 */
public class HazelcastDataAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HazelcastDataAccessServlet() {
        super();
    }

    //select
  	public void doGet(HttpServletRequest request,HttpServletResponse response){
  		String url=request.getRequestURI();
  		System.out.println(url);
  	}
  	
  	//add
  	public void doPost(HttpServletRequest resquest,HttpServletResponse response){
  		
  	}
  	//update
  	public void doPut(HttpServletRequest resquest,HttpServletResponse response){
  		
  	}
  	//delete
  	public void doDelete(HttpServletRequest resquest,HttpServletResponse response){
  		
  	}

}
