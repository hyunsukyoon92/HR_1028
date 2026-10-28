package com.hexagon.mvcframework.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hexagon.mvcframework.controller.Controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		log.debug("게시판 내용을 처리합니다");
	}

	@Override
	public String getViewName() {
		return null;
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
