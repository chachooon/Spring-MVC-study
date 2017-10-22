package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zerock.domain.SampleVO;

@RestController 
@RequestMapping("/sample")
public class SampleController {
	
	// @RestController : 해당 컨트롤러의 모든 뷰처리가 JSP 외에 데이터 자체를 반환한다.	
	
	// 단순 문자열의 경우 text/html으로 반환한다. 
	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello World ";
	}
	
	// 객체는 별도의 처리가 없어도 JSON으로 반환한다. 
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {

		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(123);

		return vo;
	}
	
	// 컬렉션 타입 - List는 배열로 반환한다.
	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {

		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}
		return list;
	}
	
	// 컬렉션 타입 - Map은 JSON형식 {key : value}으로 반환한다.
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap() {

		Map<Integer, SampleVO> map = new HashMap<>();
		//숫자 i가 key, SampleVO타입의 객체가 value.
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			map.put(i, vo);
		}
		return map;
	}
	
	// 별도의 view를 반환하지 않기 때문에 결과데이터가 예외적인 상황을 따로 처리해준다.
	// ResponseEntity 클래스를 이용해 'HTTP상태코드'와 '결과데이터'를 직접 제어할 수있다.
	
	// HTTP 상태코드 (400:bad request) 반환하는 경우.
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth() {
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	// HTTP 상태코드와 결과데이터를 같이 반환해야 하는경우.
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot() {

		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("길동");
			vo.setLastName("홍");
			vo.setMno(i);
			list.add(vo);
		}

		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}

}