package kr.or.dgit.jdbc_application_study.dto;

public class Title {
	private int titleNo;
	private String titleName;

	public Title() {}
	public Title(int titleNo, String titleName) {
		this.titleNo = titleNo;
		this.titleName = titleName;
	}
	public int getTitleNo() {
		return titleNo;
	}

	public Title(int titleNo) {
		this.titleNo = titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	@Override
	public String toString() {
		return String.format("%s, %s", titleNo, titleName);
	}
}
