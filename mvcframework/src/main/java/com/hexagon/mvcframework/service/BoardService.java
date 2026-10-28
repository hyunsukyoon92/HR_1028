package com.hexagon.mvcframework.service;

import java.util.List;

import com.hexagon.mvcframework.domain.Board;

//이 객체가 필요한 이유?
//만일 이 서비스 영역의 객체가 존재하지 않게 되면, 컨트롤러가 모델 영역에 지나치게 의존적이다'
//예를 들어 컨트롤러는 회원등록시 insert 하라고 명령하기 보다는 "등록" 이라는 비전산 용어를(비즈니스 용어) 만으로도
//업무를 시켜야함 그러기 위해서는 모델 영역에 서비스라는 하나의 계층을 두고
//앞으로는 이 서비스영역이 모든 컨트롤러의 명령을 DAO 대신 받아야 한다
public class BoardService {
	public void regist() {
	}
	public List getList() {
		return null;
	}
	public Board getContent() {
		return null;
	}
}
