package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	/* 게시판 목록 */
	public List<BoardVo> exeGetBoardList() {
		System.out.println("BoardService.exeGetBoardList()");
		
		List<BoardVo> boardList = boardDao.getBoardList();
		
		return boardList;
	}

	/* 게시판 읽기 (BoardRead) */
	public BoardVo exeGetContent(int no) {
		System.out.println("BoardService.exeGetContent()");
		
		// 조회수 증가
		boardDao.updateHit(no);
		
		BoardVo boardVo = boardDao.selectContent(no);
		
		return boardVo;
	}
	
	/* 게시판 읽기 (BoardModifyForm) */
	public BoardVo exeGetContent2(int no) {
		System.out.println("BoardService.exeGetContent()");
		
		BoardVo boardVo = boardDao.selectContent(no);
		
		return boardVo;
	}
	
	/* 게시판 등록 */
	public int exeWrite(BoardVo boardVo) {
		System.out.println("BoardService.exeWrite()");
		
		int count = boardDao.insertBoard(boardVo);
		
		return count;
	}
	
	/* 게시판 수정 */
	public int exeModifyBoard(BoardVo boardVo) {
		System.out.println("BoardService.exeModifyBoard()");
		
		int count = boardDao.modifyBoard(boardVo);
		
		return count;
	}
	
	/* 게시판 삭제 */
	public int exeDeleteBoard(int no) {
		System.out.println("BoardService.exeDeleteBoard()");
		
		int count = boardDao.deleteBoard(no);
		
		return count;
				
	}
	
}
