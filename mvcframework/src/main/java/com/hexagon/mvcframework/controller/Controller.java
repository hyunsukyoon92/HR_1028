package com.hexagon.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*컨트롤러 클래스들의 최상위 인터페이스
 * 모든 하위 컨트롤러들이 반드시 구현해야할 메서드를 정의!!
 * */
public interface Controller {
	
	//요청을 처리할때 사용되는 메서드
	public void execute(HttpServletRequest request, HttpServletResponse response);
	
	//뷰의 이름반환 , 실제 jsp 파일명을 반환하게되면 파일명 변경되었을때 이 클래스를 수정해야 하므로 유지보수성이 떨어짐..따라서 일부 단서만 반환 
	public String getViewName();
	
	//포워딩 여부를 반환 
	public boolean isForward();
	
}
