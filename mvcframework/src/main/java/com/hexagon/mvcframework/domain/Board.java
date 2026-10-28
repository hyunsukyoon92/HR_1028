package com.hexagon.mvcframework.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

//Mybatis 등에서 db 매핑 작업에 사용할 객체이므로
//멤버변수(필드)명은 db table명과 일치하는 경우가 많다
@Getter
@Setter
public class Board {
	private int boardId;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime createdAt;
	private int hit;
}
