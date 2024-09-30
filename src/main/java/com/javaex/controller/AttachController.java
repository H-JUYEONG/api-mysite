package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;
import com.javaex.vo.AttachVo2;

@RestController
public class AttachController {

	@Autowired
	private AttachService attachService;

	/* 파일첨부(form) */
	@PostMapping("/api/attachs")
	public JsonResult form(@RequestParam("profileImg") MultipartFile profileImg) {
		System.out.println("AttachController.form()");

		String saveName = attachService.exeUpload(profileImg);

		return JsonResult.success(saveName);
	}

	/* 내용 입력 + 파일첨부(form2) */
	@PostMapping("/api/attachs2")
	public JsonResult form2(@ModelAttribute AttachVo2 attachVo2) {
		System.out.println("AttachController.form2()");

		String saveName = attachService.exeUpload2(attachVo2);

		return JsonResult.success(saveName);
	}

}
