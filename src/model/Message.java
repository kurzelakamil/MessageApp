package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Message {
	Integer id = 0;
	String subject;
	String textMessage;
	Timestamp createdDate;
	Integer authorId;
	Integer receiverId;
	
	public Message(String subject, String textMessage, Timestamp createdDate, Integer authorId, Integer receiverId) {
		super();
		this.subject = subject;
		this.textMessage = textMessage;
		this.createdDate = createdDate;
		this.authorId = authorId;
		this.receiverId = receiverId;
	}
	
	public Message(Integer id, String subject, String textMessage, Timestamp createdDate, Integer authorId, Integer receiverId) {
		super();
		this.id = id;
		this.subject = subject;
		this.textMessage= textMessage;
		this.createdDate = createdDate;
		this.authorId = authorId;
		this.receiverId = receiverId;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}
	
	public String getDateAsString() {
		String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(createdDate);
		return date;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getId() {
		return id;
	}
	
	
}