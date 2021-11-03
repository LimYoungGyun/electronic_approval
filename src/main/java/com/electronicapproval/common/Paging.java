package com.electronicapproval.common;

public class Paging {
	private int page; // 페이지 번호 : 현재 몇 페이지 인지 << [1] [2] [3] [4] [5] >> 이런식의 페이지ㅣ 번호를 나타내느것.
	private int totalPage; // 페이지 갯수 : << [1] [2] [3] [4] [5] >> 총 5개의 페이지
	private int pageSize; // 한 페이지 게시글 수 : 한 페이지에 출력 될 게시글 수
	private int totalArticle; // 전체 게시글 수
	private int startRow; // 한 페이지에 게시글 시작 행
	private int endRow; // 한 페이지에 게시글 끝 행
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		// 페이지 번호가 없을 때 1로 맞춰줌.
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalArticle) {
		this.totalPage = this.totalArticle / this.pageSize + (this.totalArticle % this.pageSize == 0 ? 0 : 1);
		
		if (this.page <= 0 || this.page > totalPage) {
			this.page = 1;
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalArticle() {
		return totalArticle;
	}
	public void setTotalArticle(int totalArticle) {
		this.totalArticle = totalArticle;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int page) {
		this.startRow = (this.page - 1) * this.pageSize + 1;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int page) {
		this.endRow = page * pageSize;
		if (endRow > this.totalArticle) {
			endRow = this.totalArticle;
		}
	}
}
