package org.zerock.domain;


public class Criteria{ //외부에서 입력되는 데이터를 다루는 객체.
	
	private int page; //현재 조회하는 페이지의 번호.
	private int perPageNum; //한 페이지당 출력하는 데이터의 개수.
	
	public Criteria() {
		this.page=1;
		this.perPageNum=10;
	}
	
	public void setPage(int page) {
		if(page<=0) {
			this.page=1;
			return;
		}
		this.page=page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum<=0||perPageNum >100) {
			this.perPageNum =10;
			return;
		}
		this.perPageNum =perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	//method for MyBatis SQL Mapper -
	public int getPageStart() {
		return (this.page-1) * perPageNum;
	}

	//method for MyBatis SQL Mapper -
	public int getPerPageNum() {
		return this.perPageNum;
	}
	
	@Override
	public String toString() {
		return "Criteria [page="+page+","+"perPageNum="+perPageNum+"]";
	}
}