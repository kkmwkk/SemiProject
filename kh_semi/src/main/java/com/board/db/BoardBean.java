package com.board.db;

import java.sql.Date;

import com.admin.db.AdminBean;
import com.admin.db.AdminBean.AdminBeanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardBean {
	
	private int no;
	private String title;
	private String category;
	private String writer;
	private String content;
	private int cnt;
	private Date regdate;
}

