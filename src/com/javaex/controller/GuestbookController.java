package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;


@WebServlet("/gbc")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("gbc호출 완료");
		
		String action = request.getParameter("action");
		
		
		
		//리스트
		if("list".equals(action)) {
			System.out.println("리스트");
			
			//리스트 넣기
			GuestBookDao guestBookDao = new GuestBookDao();
			List<GuestBookVo> guestBookList = guestBookDao.guestbookList();
			
			//어트리뷰트
			request.setAttribute("gList", guestBookList);
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			
		}//삭제 폼
		else if("dform".equals(action)) {
			System.out.println("삭제 폼");
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
		}//삭제
		else if("delete".equals(action)) {
			System.out.println("삭제");
			
			int iNo = Integer.parseInt(request.getParameter("id"));
			String pw = request.getParameter("pw");
			
			GuestBookVo gvo = new GuestBookVo(iNo,pw);
			
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookDelete(gvo);
			
			response.sendRedirect("/guestbook2/gbc?action=list");
			
		}//입력
		else if("insert".equals(action)) {
			System.out.println("입력");
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVo guestBookVo = new GuestBookVo(name,password,content);
			
			GuestBookDao guestBookDao = new GuestBookDao();
			guestBookDao.guestBookInsert(guestBookVo);
			
			response.sendRedirect("/guestbook2/gbc?action=list");
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
