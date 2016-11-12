package net.p316.news.newscat.data;

import java.sql.Date;

public class NcTitle {
	private int idx;
	private int idx_category;
	private String url;
	private String title;
	private String company;
	private Date date;
	
	public NcTitle() {
		
	}

	public void set_idx(int idx) {
		this.idx = idx;
	}
	
	public int get_idx() {
		return this.idx;
	}

	public void set_idx_category(int idx_category) {
		this.idx_category = idx_category;
	}
	
	public int get_idx_category() {
		return this.idx_category;
	}

	public void set_url(String url) {
		this.url = url;
	}
	
	public String get_url() {
		return this.url;
	}


	public void set_title(String title) {
		this.title = title;
	}
	
	public String get_title() {
		return this.title;
	}
	
	public void set_company(String company) {
		this.company = company;
	}
	
	public String get_company() {
		return this.company;
	}
	
	public void set_date(Date date) {
		this.date = date;
	}
	
	public Date get_date() {
		return this.date;
	}
}
