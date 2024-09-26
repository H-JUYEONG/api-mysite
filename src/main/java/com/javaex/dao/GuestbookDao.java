package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestbookDao {

	// 필드
	@Autowired
	private SqlSession sqlSession;

	// 생성자
	// 기본생성자 사용(그래서 생략)

	// 메소드 gs
	// 필드값을 외부에서 사용하면 안됨(그래서 생략)

	// 메소드 일반
	// DB연결 메소드

	/* 방명록 목록 */
	public List<GuestVo> getGuestList() {
		System.out.println("GuestbookDao.getGuestList()");
		List<GuestVo> guestList = sqlSession.selectList("guestbook.selectList");

		return guestList;
	}

	/* 등록 */
	public int insertGuest(GuestVo guestVo) {
		System.out.println("GuestbookDao.insertGuest()");

		int count = sqlSession.insert("guestbook.insert", guestVo);
		return count;
	}

	/* 삭제폼 - 사람 1명 정보 불러오기
	public GuestVo getGuestOne(int no) {
		System.out.println("GuestbookDao.getPersonOne()");

		GuestVo guestVo = sqlSession.selectOne("guestbook.selectOne", no);

		return guestVo;
	} 
	*/

	/* 삭제 */
	public int deleteGuest(GuestVo guestVo) {

		int count = sqlSession.delete("guestbook.delete", guestVo);

		return count;
	}

}