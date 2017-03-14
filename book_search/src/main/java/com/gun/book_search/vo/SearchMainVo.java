package com.gun.book_search.vo;

public class SearchMainVo {
	
	private int no;
	private String name;
	private String url;
	private int cnt;
	private int depth;
	private int site_type;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getSite_type() {
		return site_type;
	}
	public void setSite_type(int site_type) {
		this.site_type = site_type;
	}
	@Override
	public String toString() {
		return "SearchUrlVo [no=" + no + ", name=" + name + ", url=" + url + ", cnt=" + cnt + ", depth=" + depth
				+ ", site_type=" + site_type + "]";
	}

}
