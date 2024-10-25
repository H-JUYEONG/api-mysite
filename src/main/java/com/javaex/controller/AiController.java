package com.javaex.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.util.JsonResult;

@RestController
public class AiController {

	@PostMapping("api/ai/chat")
	public JsonResult chat(@RequestParam(value = "question") String question) {
		System.out.println("AiController.chat()");
		System.out.println(question);

		String answer = "";

		// 파이썬 가상환경 실행
		String venvPath = "C:\\javaStudy\\workspace_python\\Ex05\\ex05_venv\\Scripts\\python.exe";

		// 실행할 파이썬 파일 경로
		String pyPath = "C:\\javaStudy\\workspace_python\\Ex05\\main.py";

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(venvPath, pyPath, question);

			// 파이썬에서 전달하는 메세지, 파이썬에서 발생하는 에러메세지 따로 관리된다.
			// 이것을 1개로 관리할 수 있다.
			processBuilder.redirectErrorStream(true);

			// 파이썬 스크립트 실행
			Process process = processBuilder.start();

			// 대답받기
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				} else {
					answer += line+"<br/>";
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(answer);
		return JsonResult.success(answer);
	}

}
