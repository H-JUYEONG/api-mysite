package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/* 회원가입 */
	public int exeJoinUser(UserVo userVo) {
		System.out.println("UserService.exeJoinUser()");

		int count = userDao.insertUser(userVo);

		return count;
	}

	/* 로그인 */
	public UserVo exeLogin(UserVo userVo) {
		System.out.println("UserService.exeLogin()");

		UserVo authUser = userDao.selectUser(userVo);

		return authUser;

	}

	/* 회원정보 수정 폼 */
	public UserVo exeGetUserInfo(int no) {
		System.out.println("UserService.exeModifyForm()");

		UserVo userVo = userDao.selectInfo(no);

		return userVo;
	}

	/* 회원정보 수정 */
	public UserVo exeUserModify(UserVo userVo) {
		System.out.println("UserService.exeUserModify()");

		int count = userDao.modifyUser(userVo);

		// 회원번호(no)를 이용해서 수정된 회원의 정보를 객체로 저장
		UserVo modifyUserInfo = userDao.selectInfo(userVo.getNo());

		return modifyUserInfo;
	}

	/* 아이디 중복 체크 */
	public boolean exeIdCheck(String id) {
		System.out.println("UserService.exeIdCheck()");

		int count = userDao.selectUserById(id);

		if (count >= 1) { // 중복O
			return false;
		} else { // 중복X
			return true;
		}
	}

}
