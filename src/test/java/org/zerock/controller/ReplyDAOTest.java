package org.zerock.controller;

import java.util.*;

import javax.inject.Inject;
//import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
import org.zerock.domain.*;
import org.zerock.persistence.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class ReplyDAOTest {
	@Inject
	private ReplyDAO dao;

	private static Logger logger = LoggerFactory.getLogger(ReplyDAOTest.class);


	 @Test
	 public void testCreate() throws Exception {
		 ReplyVO reply = new ReplyVO();
		 reply.setBno(10000);
		 reply.setReplytext("댓글 테스트!!!");
		 reply.setReplyer("user00");
		 dao.create(reply);
	 }

	 @Test
	 public void testUpdate() throws Exception {
		 ReplyVO reply = new ReplyVO();
		 reply.setBno(10000);
		 reply.setReplytext("수정된 댓글입니다.");
		 reply.setReplyer("user11");
		 dao.update(reply);
	 }
	 
	 @Test
	 public void testDelete() throws Exception {
		 dao.delete(5);
	 }
	 
	 /*
	 @Test
	 public void testListPage() throws Exception {
		 int page = 3;
		 List<BoardVO> list = dao.listPage(page);
			 for (BoardVO boardVO : list) {
				 logger.info(boardVO.getBno() + ":" + boardVO.getTitle());
			 }
	 }

	// SQL문 결과와 Junit 테스트결과가 같은지 확인 
	// SELECT * FROM tbl_board WHERE bno > 0 ORDER BY bno DESC LIMIT 20,20; 
	 @Test
	 public void testListCriteria() throws Exception {
		 Criteria cri = new Criteria();
		 cri.setPage(2);
		 cri.setPerPageNum(20);
		
		 List<BoardVO> list =dao.listCriteria(cri);
		
			 for(BoardVO boardVO : list) {
				 logger.info(boardVO.getBno()+":"+boardVO.getTitle());
			 }
	 }

	
	@Test
	public void testURI2() throws Exception {
		//UriComponents :  path나 query에 해당하는 문자열들을 추가해 원하는 URI를 생성해줌.
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/{module}/{page}").queryParam("bno", 12)
				.queryParam("perPageNum", 20).build().expand("board", "read").encode();
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
*/	
}
