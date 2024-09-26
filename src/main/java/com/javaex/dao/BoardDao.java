package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	/* 게시판 목록 */
	public List<BoardVo> getBoardList() {
		System.out.println("BoardDao.getBoardList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		return boardList;
	}
	
	/* 게시판 읽기 */
	public BoardVo selectContent(int no) {
		System.out.println("BoardDao.selectContent()");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectContent", no);
		
		return boardVo;
		
	}
	
	/* 조회수 증가 */
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit()");
		
		int count = sqlSession.update("board.updateHit", no);
		
		return count;
		
	}
	
	/* 게시판 등록 */
	public int insertBoard(BoardVo boardVo) {
		System.out.println("BoardDao.insertBoard()");
		
		int count = sqlSession.insert("board.insert", boardVo);
		
		return count;
	}
	
	/* 게시판 수정 */
	public int modifyBoard(BoardVo boardVo) {
		System.out.println("BoardDao.modifyBoard()");
		
		int count = sqlSession.update("board.update", boardVo);
		
		return count;
	}
	
	/* 게시판 삭제 */
	public int deleteBoard(int no) {
		System.out.println("BoardDao.deleteBoard()");
		
		int count = sqlSession.delete("board.delete", no);
		
		return count;
	}
}
