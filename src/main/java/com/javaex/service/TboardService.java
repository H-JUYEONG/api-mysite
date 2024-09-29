package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;
	
	/* 전체리스트가져오기 리스트3(검색O, 페이징O) */
	public Map<String, Object> exeGetList(int crtPage, String keyword) {
		System.out.println("TboardService.exeList()");

		// 한 페이지의 출력갯수
		int listCnt = 10;
		
		// 현재 페이지 음수일때 계산(3항 연산자)
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		/*
		if(crtPage > 0) {
			crtPage = crtPage;
		} else {
			crtPage = 1;
		}
		*/
		
		// startRowNo 구하기
		// 1->(1 10) 2->(11 10) 3->(21 10) 사람
		// 1->(0 10) 2->(10 10) 3->(20 10) mysql
		// (1-1)10 0
		// (2-1)10 10
		// (3-1)10 20

		// 1->(1 3) 2->(4 3) 3->(7 3) 사람
		// 1->(0 3) 2->(3 3) 3->(6 3) mysql

		// (1-1)3 0
		// (2-1)3 3
		// (3-1)3 6
		// StartRowNo = (crtPage-1)listCnt

		int StartRowNo = (crtPage - 1) * listCnt;

		// 2개의 데이터를 1개로 묶어준다
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("StartRowNo", StartRowNo); // 별명, 실제 값(변수명)
		limitMap.put("listCnt", listCnt); // 별명, 실제 값(변수명)
		limitMap.put("keyword", keyword); // 별명, 실제 값(변수명)

		List<TboardVo> tboardList = tboardDao.selectList(limitMap);

		// 페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		// DB에서 데이터 전체 글 갯수 가져오기
		int totalCnt = tboardDao.selectTotalCntKeyword(keyword); 

		// endPageBtnNoNo 마지막 버튼 번호
		// 1 2 3 4 5 > 페이지당 버튼
		// 1 --> (1~5) 
		// 2 --> (1~5)
		// 3 --> (1~5)
		// 4 --> (1~5)
		// 5 --> (1~5)
		// 6 --> (6~10)
		// 7 --> (6~10)
		// ...
		// 10 -> (6~10)
		// 11 -> (11~15)

		// (1 5) => 올림(1/5)*5 0.2 올림하면 1.0 --> 1*5 --> 5
		// (2 5) => 올림(2/5)*5 0.4 올림하면 1.0 --> 1*5 --> 5
		// (3 5) => 올림(3/5)*5 0.6 올림하면 1.0 --> 1*5 --> 5
		// (4 5) => 올림(4/5)*5 0.8 올림하면 1.0 --> 1*5 --> 5
		// (5 5) => 올림(5/5)*5 1.0 올림하면 1.0 --> 1*5 --> 5
		// (6 10) => 올림(6/5)*5 1.2 올림하면 2.0 --> 2*5 --> 10
		// ...
		// (11 15) => 올림(11/5)*5 2.2 올림하면 3.0 --> 2*5 --> 15
		// (올림(crtPage/pageBtnCount)) * pageBtnCount
		
		// 마지막 버튼 번호
		int endPageBtnNo = (int) Math.ceil((crtPage / (double) pageBtnCount)) * pageBtnCount;
		
		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo - pageBtnCount) + 1;
		
		System.out.println(crtPage + ", " + startPageBtnNo + ", " + endPageBtnNo);
		
		// 다음 화살표 유무
		boolean next = false;
		
		// 한 페이지당 글갯수(10) * 마지막버튼번호(19) < 전체 글 갯수(187)
		if(listCnt * endPageBtnNo < totalCnt) { 
			next = true;
		} else {
			// 다음화살표가 false일때 마지막 숫자버튼이 갯수를 정확히 계산
			// 187 => 19페이지	187/10 --> 18.7 올림처리해서 19로 사용
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		// 이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		/* 화면에 표현할 모든 데이터를 묶는다 = map */
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList", tboardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		return pMap;
	}

}
