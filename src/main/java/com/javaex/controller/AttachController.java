package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;

@RestController
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	
	@PostMapping("/api/attachs")
	public JsonResult form(@RequestParam("profileImg") MultipartFile profileImg) {
		System.out.println("AttachController.form()");
		
		String saveName = attachService.exeUpload(profileImg);
		
		return JsonResult.success(saveName);
	}

}
