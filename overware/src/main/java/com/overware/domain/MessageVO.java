package com.overware.domain;

import java.util.Date;

public class MessageVO {
	
	private Integer mid; //
	private String targetid; // 메세지 받는 사람의 아이디 
	private String sender; //보내는 사람 id
	private String message;//메세지 내용
	private Date opendate;
	private Date senddate;
	
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public Date getSenddate() {
		return senddate;
	}
	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	
	@Override
	public String toString() {
		return "MessageVO [mid=" + mid + ", targetid=" + targetid + ", sender=" + sender + ", message=" + message
				+ ", opendate=" + opendate + ", senddate=" + senddate + ", getMid()=" + getMid() + ", getTargetid()="
				+ getTargetid() + ", getSender()=" + getSender() + ", getMessage()=" + getMessage() + ", getOpendate()="
				+ getOpendate() + ", getSenddate()=" + getSenddate() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
