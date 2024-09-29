package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.util.JsonResult;
import com.javaex.util.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/* 회원가입 */
	@PostMapping("/api/users")
	public int join(@RequestBody UserVo userVo) {
		System.out.println("UserController.join()");

		int count = userService.exeJoinUser(userVo);

		return count;
	}
	
	/* ID 중복 체크 */
	@GetMapping("/api/users/idcheck")
	public JsonResult idCheck(@RequestParam("id") String id) {
		System.out.println("UserController.idCheck()");
		
		boolean result = userService.exeIdCheck(id);
		
		if(result == true) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("사용할 수 없는 아이디입니다.");
		}
		
	}

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

	/* 회원정보 수정 폼(회원 정보 가져오기) */
	@GetMapping("/api/users/me")
	public JsonResult modifyForm(HttpServletRequest request) {
		System.out.println("UserController.modifyForm()");

		// 요청 헤더에서 토큰을 꺼내서 유효성을 체크한 후 정상이면 no값을 꺼내준다.
		int no = JwtUtil.getNoFromHeader(request);
		System.out.println(no);

		if (no != -1) {
			UserVo userVo = userService.exeGetUserInfo(no);
			return JsonResult.success(userVo);

		} else {
			return JsonResult.fail("토큰 없음, 비로그인, 변조");
		}
	}

	/* 회원정보 수정 */
	@PutMapping("/api/users/me")
	public JsonResult modifyUser(@RequestBody UserVo userVo, HttpServletRequest request) {
		System.out.println("UserController.modifyUser()");

		int no = JwtUtil.getNoFromHeader(request);

		if (no != -1) {
			// user의 no는 token으로 가져오기때문에 userVo에 넣어서 합치기
			userVo.setNo(no);
			int count = userService.exeUserModify(userVo);
			
			if (count == 1) {
				// 1. 수정된 회원정보에서 no, name 값만 넘겨주기위해서 null로 세팅함
				// 2. react의 localStorage authUser를 수정된 no, name 값으로 변경함
				userVo.setPassword(null);
				userVo.setGender(null);
				return JsonResult.success(userVo);
			} else {
				return JsonResult.fail("수정 실패");
			}
			
		} else {
			return JsonResult.fail("토큰 없음, 비로그인, 변조");
		}
	}
}
