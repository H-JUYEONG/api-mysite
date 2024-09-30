package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.TboardService;
import com.javaex.util.JsonResult;

@RestController
public class TboardController {

	@Autowired
	private TboardService tboardService;

	/* 전체리스트가져오기 - 기본 리스트(검색O, 페이징O) */
	@GetMapping("/api/boards2/list")
	public JsonResult getList(@RequestParam(value = "crtpage", required = false, defaultValue = "1") int crtPage,
		    @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("BoardController.getList()");
		System.out.println(crtPage);
		System.out.println(keyword);

		Map<String, Object> pMap = tboardService.exeGetList(crtPage, keyword);
		System.out.println(pMap);

		return JsonResult.success(pMap);
	}

}
