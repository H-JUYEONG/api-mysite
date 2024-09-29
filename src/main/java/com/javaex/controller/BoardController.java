package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 게시판 목록 */
	@GetMapping("/api/boards")
	public JsonResult getList() {
		System.out.println("BoardController.getList()");

		List<BoardVo> boardList = boardService.exeGetBoardList();
	
		return JsonResult.success(boardList);
	}
	
	/* 삭제 */
	@DeleteMapping("/api/boards/{no}")
	public JsonResult delBoard(@PathVariable("no") int no) {
		System.out.println("BoardController.delBoard()");

		int count = boardService.exeDeleteBoard(no);

		if (count != -1) { // 삭제 성공
			return JsonResult.success(count);
		} else { // 삭제 실패
			return JsonResult.fail("삭제 실패");
		}
	}
	
	/* 게시판 읽기 */
	@GetMapping("/api/boards/{no}")
	public JsonResult readBoard(@RequestParam("no") int boardNo) {
		System.out.println("BoardController.readBoard()");
		
		BoardVo boardVo = boardService.exeGetContent(boardNo);
		
		if (boardVo != null) { // 삭제 성공
			return JsonResult.success(boardVo);
		} else { // 삭제 실패
			return JsonResult.fail("조회 실패");
		}
	}
}
