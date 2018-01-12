package com.overware.domain;

import java.sql.Date;

public class MemberVO {

	private String userid;//사용자 아이디
	private String userpw;//사용자 패스워드
	private String username;//사용자 이름
	private String email;//사용자 이메일
	private Date regdate;//가입날짜
	private Date updatedate;//업데이트 날짜
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", email=" + email
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + ", getUserid()=" + getUserid()
				+ ", getUserpw()=" + getUserpw() + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail()
				+ ", getRegdate()=" + getRegdate() + ", getUpdatedate()=" + getUpdatedate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
