package com.hexagon.mvcframework.dispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexagon.mvcframework.controller.Controller;
import com.hexagon.mvcframework.handler.RequestMappingHandlerMapping;
import com.hexagon.mvcframework.web.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

/*
  [ 애플이케이션의 모든 요청을 받는 진입점 ]
  1.요청을 받는다
  2.요청을 분석한다(이 요청을 누가 처리할지를 결정짓고 해당 컨트롤러의 메서드 호출)
  5.결과를 보여준다
*/
@Slf4j
public class DispatcherServlet extends HttpServlet{
	RequestMappingHandlerMapping handlerMapping;
	InternalResourceViewResolver viewResolver;
	
	//이 서블릿이 생성된 직후, 초기화를 위해 호출
	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext(); //현재 애플리케이션에 대한 정보를 가진 전역 객체인 ServletContext 를 얻는다
		String contextConfigLocation=config.getInitParameter("contextConfigLocation");
		String realPath = application.getRealPath(contextConfigLocation); //얻고 싶은 대상 파일 및 경로를 넣으면, os상의 풀경로를 반환
		
		log.debug("매핑 파일의 OS상 실제 경로는 {}", realPath);
		
		handlerMapping = new RequestMappingHandlerMapping(realPath);
		viewResolver = new InternalResourceViewResolver();
	}
	
	// 서블릿의 생명주기 메서드(init, service, destory) 중 요청을 처리하는 메서드인 service를 재정의한다!! 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("요청 받았어");
		
		//2단계 - 요청 분석(직접하지 않고 RequestMappingHandlerMapping 에게 분석을 맡김)
		String uri = request.getRequestURI();//웹드라우저가 요청한 요청 주소 ex)/board/list, /board/regist 등등
		Controller controller = handlerMapping.getController(uri);
		
		//우리 프레임웍의 경우 모든 요청을 다 받는다고 선언되어 있으므로(web.xml의 서블릿매핑), 심지어 favicon 요청까지 받아버림 favicon 요철을 처리하는 커트롤러를 
		//따로 정의 한적이 없기 떄문에, favicon요청의 경우 controller는 생성된 것이 없으므로 null이다. 
		//따라서 null 일 때는 Controller의 메서드인 execute() 호출하지 말자!!! (NullPointerException 방지)
		if(controller ==null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		controller.execute(request, response);
		
		//5단계 - 결과 보여주기
		String viewPage = viewResolver.resolve(controller.getViewName());
		log.debug("viewresolver로 부터 얻어온 jsp의 완전 경로는{}", viewPage);
		
		//포워딩? redirect?
		if(controller.isForward()) {
			//포워딩 처리해야 함(요청에 대해 응답하지 않고 서버의 또 다른 서블릿으로 요청을 전달)
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);//포워딩이 수행되는 시점
		}else {
			//redirect 처리해야함(요청에 대해 응답하여 클라이언트로 하여 지정한 url 로 다시 들어오게 함) - location.href 를 유도 해냄
			response.sendRedirect(viewPage);
		}
	}
	
}














