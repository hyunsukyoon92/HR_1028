package com.hexagon.mvcframework.web.view;

//하위 컨트롤러가 DispatcherServlet에게 jsp풀경로를 주지않고 몸땡이만 반환해주므로
//이 단서를 이용하여 완전한 풀경로를 조합해주는 말그래도 뷰페이지에 대한 해결사를 정의한다
//접두어(prefix), 접미어(suffix)를 조합하여 완전한 jsp의 경로를 반환해줌
public class InternalResourceViewResolver {
	String prefix="/views/";
	String suffix=".jsp";

	//DispatcherServlet 으로부터 단서를 전달받아 풀경로 반환
	public String resolve(String viewName) {
		if(viewName.contains("/redirect:/")) {  // ex) redirect:/board/list
			return viewName.substring("redirect:/".length()-1); //redirect: 제외시킴
		}
		
		return prefix+viewName+suffix;
	}
		
}
