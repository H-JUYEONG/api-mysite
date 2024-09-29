package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
