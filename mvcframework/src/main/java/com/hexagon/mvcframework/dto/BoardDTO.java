package com.hexagon.mvcframework.dto;

import lombok.Getter;
import lombok.Setter;

//이 클래스는 클라이언트의 요청 파라미터만을 받기 위함 이므로
//반드시 db의 컬러명과 일치시킬 필요가 없다 즉 보안상 중요한 컬럼과 관련된 파라미터는 일부러
//다르게 명칭을 주는 것이 좋다
@Getter
@Setter
public class BoardDTO {
	private String subject;
	private String writer;
	private String content;
	
}
