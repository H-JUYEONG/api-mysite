package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

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
	
	/* 로그인 */
	

}
