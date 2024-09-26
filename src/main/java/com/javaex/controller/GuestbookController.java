package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.GuestVo;

@RestController
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	/* 방명록 목록 */
	@GetMapping("/api/guests")
	public JsonResult getList() {
		System.out.println("GuestbookController.getList()");

		List<GuestVo> guestList = guestbookService.exeGetGuestList();
	
		return JsonResult.success(guestList);
	}
	
	/* 등록 */
	@PostMapping("/api/guests")
	public int addGuest(@RequestBody GuestVo guestVo) {
		System.out.println("GuestbookController.addGuest()");

		int count = guestbookService.insertGuest(guestVo);

		return count;
	}
	
	/* 삭제 */
	@DeleteMapping("/api/guests/{no}")
	public JsonResult delGuest(@RequestBody GuestVo guestVo, @PathVariable(value = "no") int no) {
		System.out.println("GuestbookController.delGuest()");
		System.out.println(no);

		boolean count = guestbookService.deleteGuest(no, guestVo.getPassword());

		if (count != true) { // 삭제 실패
			return JsonResult.fail("비밀번호가 일치하지 않습니다.");
		} else { // 삭제 성공
			return JsonResult.success(count);
		}
	}

}
