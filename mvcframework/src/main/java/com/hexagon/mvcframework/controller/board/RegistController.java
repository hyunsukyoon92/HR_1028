package com.hexagon.mvcframework.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.mvcframework.controller.Controller;
import com.hexagon.mvcframework.dao.BoardDAO;
import com.hexagon.mvcframework.domain.Board;
import com.hexagon.mvcframework.dto.BoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistController implements Controller{

	private BoardDAO boardDAO = new BoardDAO();
	private String viewName;
	private boolean forword;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		log.debug("게시판 등록을 처리합니다");
		
		//클라이언트가 전송한 파라미터를 BoardDTO로 받는다(보안상)
		Board board = new Board();
		board.setTitle(request.getParameter("subject"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		int result = boardDAO.insert(board);
		if(result!=1) {//등록 실패라면 에러페이지 보여주기
			viewName="error/result"; //view/error/result.jsp
			forword=true;//에러메시지 저장위해
			request.setAttribute("errorMessage", "등록실패");
		}else {
			viewName="redirect:/board/list";//현재 요청을 끊고 목록페이지 새롭게 들어오도록한다
			forword=false;
		}
	}

	@Override
	public String getViewName() {
		return viewName;
	}

	@Override
	public boolean isForward() {
		return forword;
	}

}
