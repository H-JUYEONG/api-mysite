package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/* 로그인 */
	@PostMapping("/api/users/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");

		UserVo authUser = userService.exeLogin(userVo);

		if (authUser != null) { // 로그인 성공
			// 토큰을 만들고 '응답문서의 헤더'에 토큰을 붙여서 보낸다
			JwtUtil.createTokenAndSetHeader(response, "" + authUser.getNo()); // getNo를 "" 이용해서 문자열로 바꿈
			return JsonResult.success(authUser);

		} else {
			return JsonResult.fail("로그인 실패");
		}

	}

	/* 회원가입 */
	@PostMapping("/api/users")
	public int join(@RequestBody UserVo userVo) {
		System.out.println("UserController.join()");

		int count = userService.exeJoinUser(userVo);

		return count;
	}

}
