package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;
import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {

	@Autowired
	private SqlSession sqlSession;

	/* 전체리스트가져오기 리스트3(검색O, 페이징O) */
	public List<TboardVo> selectList(Map<String, Object> limitMap) {
		System.out.println("TboardDao.selectList()");

		List<TboardVo> tboardList = sqlSession.selectList("tboard.selectList3", limitMap);

		return tboardList;
	}

	/* 데이터 전체 갯수 구하기(검색O) */
	public int selectTotalCntKeyword(String keyword) {
		System.out.println("TboardDao.selectTotalCntKeyword()");

		int totalCnt = sqlSession.selectOne("tboard.selectTotalCntKeyword", keyword);
		System.out.println(totalCnt);
		return totalCnt;
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
