package com.admin.db;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBean {
	
	private int no;
	private String name;
	private String email;
	private String password;
	private String filename;
	private String filerealname;
	private String major;
	private String gender;
	private Date regdate;
	
}