package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class AttachVo2 {

	// 필드
	private int no;
	private String orgName;
	private String saveName;
	private String filePath;
	private long fileSize;
	private String content;
	private MultipartFile img;

	// 생성자

	public AttachVo2() {

	}

	public AttachVo2(int no, String orgName, String saveName, String filePath, long fileSize, String content,
			MultipartFile img) {
		this.no = no;
		this.orgName = orgName;
		this.saveName = saveName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.content = content;
		this.img = img;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getImg() {
		return img;
	}

	public void setImg(MultipartFile img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "AttachVo2 [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", filePath=" + filePath
				+ ", fileSize=" + fileSize + ", content=" + content + ", img=" + img + "]";
	}

}
