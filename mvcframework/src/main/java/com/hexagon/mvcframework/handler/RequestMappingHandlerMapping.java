package com.hexagon.mvcframework.handler;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.hexagon.mvcframework.controller.Controller;

import lombok.extern.slf4j.Slf4j;

/*----------------------------------------------------
 클라이언트의 각 요청에 대해 어떤 컨트롤러가 동작해야 하는지 매핑 파일을 조회하고, 
 해당 컨트롤러의 인스턴스를 DispatcherServlet에게 제공한다 
 ----------------------------------------------------*/
@Slf4j
public class RequestMappingHandlerMapping {
	Properties props; //대상 데이터가 만일 = 을 기준으로 작성되어 있다면, 좌측은 key,  우측은 value로 이해할 수 있는 객체
	 
	//각 요청마다 어떤 컨트롤러가 동작할지를 모아놓을 Map 
	//key -  요청uri , value- 컨트롤러의 인스턴스    
	// 예)   /board/list  -   ListController 인스턴스
	Map<String, Controller> controllerMap=new HashMap<>();
	 
	public RequestMappingHandlerMapping(String path) {
		// 매핑 파일을 접근하여 각 요청에 어떤 클래스가 동작해야 하는지에 대한 정보를 보관해놓자!!
		//WEB-INF/mapping.properties 경로는 완전한 풀 경로가 아니므로, FileInputStream이 접근할 수 없다..
		// 해결책?  하나의 애플리케이션 마다 할당되는 application 내장객체(jsp) 인 ServletContext 객체를 이용하여 현재 애플리케이션에 들어있는 파일들의 
		// OS상의 풀경로를 얻어오는 방법이 있다.
		try(FileInputStream fis=new FileInputStream(path)){
			log.debug("파일 접근 성공");
			
			props = new Properties();
			props.load(fis); //파일과 Properties가 연결되는 시점 
			
			//Set은 순서가 없는 집합이므로, 일반적인  for문으로는 직접 돌릴수 없다. 따라서 jdk5 부터 출시된 개선된 for로 돌리자!!
			for(String key  : props.stringPropertyNames()) {
				String className = props.getProperty(key);
				
				log.debug("Set에서 꺼낸  key는 {}와 그값은 {}", key, className);
				
				//여기까지 구한 정보는 컨트롤러의 경로일 뿐 실제 클래스는 아니므로, 지금부터 이 경로를 이용하여 컨트롤러를  jvm의  method(static) 영역으로 올리고 
				//load된 클래스들을 대상으로 인스턴스까지 만들어보자!!!
				Class clazz =Class.forName(className); //com.hexagon.mvcframework.controller.board.ListController
				
				//지금 clazz라는 변수를 대상으로 new 연산자를 적용할수 없으므로,... ex) new clazz() 불가능 
				//따라서 clazz라는 Class 클래스가 보유한 메서드중 인스턴스를 생성해주는 메서드를 이용해보자 
				Controller instance=(Controller)clazz.getDeclaredConstructor().newInstance(); //컨트롤러의 인스턴스 생성!!!
				
				//생성된 인스턴스에 꼬리표인 key값을 부여하여 언제든  key 값으로 꺼내서 사용해보자!!
				//우리의 경우 브라우저에서 요청한 주소가 /board/list  라면 이 uri 를 인스턴스를 꺼내는 용도의 key 값으로 사용하자!!
				controllerMap.put(key, instance);
			}
		}catch(Exception e) {
			log.debug("파일 경로를 확인해 주세요");
			e.printStackTrace(); //에러 로그
		}
	}
	
	//현재 이 핸들러 매핑 클래스는 DispatcherServlet 에게 요청이 들어올때마다 Map을 검색하여 해당 컨트롤러를 반환해줘야한다
	public Controller getController(String uri) {
		return controllerMap.get(uri);//브라우저의 요청이 만일 /board/list 라면, Map에서 /board/list 라는 키를 갖는 ListController인스턴스를 꺼냄
	}
}
