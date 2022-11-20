package com.gmember.db;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// setter, getter 인식
@Data

// 세션 객체화
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class GMemberBean {
	private int no;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String filename;
	private String filerealname;
	private String major;
	private Date regdate;
}
