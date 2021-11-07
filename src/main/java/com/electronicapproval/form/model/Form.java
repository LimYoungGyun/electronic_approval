package com.electronicapproval.form.model;

import java.util.Date;

import lombok.Data;

@Data
public class Form {
	private int id; // 게시물 번호
	private int employeeId; // 직원ID
	private int positionId; // 직급ID
	private int groupId; // 그룹Id
	private int count; // 연차개수
	private String startDate; // 연차 시작 일자
	private String endDate; // 연차 종료 일자
	private int sendTo; // 발신자 ID - 상대방 employeeId
	private String status; // 게시물 상태
	private String content; // 게시물 작성시 요청 내용
	private String reContent; // 게시물 수락, 반려시 내용
	private Date createdAt; // 등록 일자
	private Date updatedAt; // 수정 일자
}
