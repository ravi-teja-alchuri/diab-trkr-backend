package com.diabtrkr.models;

public class Therapy extends BaseModel {

	private String userId;
	private String type;
	private String therapy;
	private Boolean onPills;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTherapy() {
		return therapy;
	}

	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}

	public Boolean getOnPills() {
		return onPills;
	}

	public void setOnPills(Boolean onPills) {
		this.onPills = onPills;
	}

}
