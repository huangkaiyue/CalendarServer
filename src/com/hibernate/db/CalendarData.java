package com.hibernate.db;


public class CalendarData {
	private int uId;
	private String author;
	private String textData;
	private String playAudio;
	private String Tdate;

	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTextData() {
		return textData;
	}
	public void setTextData(String textData) {
		this.textData = textData;
	}
	public String getPlayAudio() {
		return playAudio;
	}
	public void setPlayAudio(String playAudio) {
		this.playAudio = playAudio;
	}
	public String getTdate() {
		return Tdate;
	}
	public void setTdate(String tdate) {
		Tdate = tdate;
	}
	@Override
	public String toString() {
		return "CalendarData [uId=" + uId + ", author=" + author+ ", textData=" + textData+", Tdate=" + Tdate + "]";
	}
}
