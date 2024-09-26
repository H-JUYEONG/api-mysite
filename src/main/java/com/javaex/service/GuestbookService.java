package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	/* 전체 가져오기 */
	public List<GuestVo> exeGetGuestList() {
		
		System.out.println("GuestbookService.exeGetGuestList()");

		List<GuestVo> guestList = guestbookDao.getGuestList();

		return guestList;
	}
	
	/* 등록 */
	public int insertGuest(GuestVo guestVo) {
		
		System.out.println("GuestbookService.insertGuest()");

		int count = guestbookDao.insertGuest(guestVo);
		return count;
	}
	
	/* 삭제폼 - 사람 1명 정보 불러오기 
	public GuestVo exeDeleteForm(int no) {
		
		System.out.println("GuestbookService.exeDeleteForm()");
		
		GuestVo guestVo = guestbookDao.getGuestOne(no);
		
		return guestVo;
	} 
	*/
	
	/* 삭제 */
	public boolean deleteGuest(int no, String password) {
		
		boolean delete = false;

		System.out.println("GuestbookService.deleteGuest()");
		
		GuestVo guestVo = new GuestVo(no, password);

		int count = guestbookDao.deleteGuest(guestVo);
		
		if(count > 0) { // 삭제된 경우
			delete = true;
		}
		
		return delete;
	}


}
