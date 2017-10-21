package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private BoardService service;

	// 등록을 위한 페이지를 보는경우 GET, 실제로 데이터를 처리하는 부분 POST
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register get................");
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post................");
		logger.info(board.toString());
		service.regist(board);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}
	
	// GET/POST방식을 구분하여 기본적인 CRUD 기능 구현.
	// 글 등록 후, 새로고침으로 등록 중복 막기위해 목록페이지로 리다이렉트 한다.
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list.................");
		model.addAttribute("list", service.listAll());
	}
	// 글 조회(read)요청 시, 파라미터는 외부에서 bno값을 전달받는다. 
	// 명확한 표현을 위해 @RequestParam을 이용해서 구성(생략가능)
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	// 글 수정(modify)도 조회(read)와 같은 GET방식으로 요청받는다.
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno));
	}
	// 글 삭제(remove)요청 후, 새로고침으로 요청이 중복되는 것을 막기위해 리다이렉트 한다.
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("remove post................");
		service.remove(bno);
		rttr.addFlashAttribute("msg", "success");
		return "redirect:/board/listAll";
	}

	
}