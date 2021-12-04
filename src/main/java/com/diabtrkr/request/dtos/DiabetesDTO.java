package com.diabtrkr.request.dtos;

import java.util.Date;

public class DiabetesDTO {

	private Date time;
	private Integer bloodSugarLevel;
	private Integer carbs;
	private Integer insulin;
	private String mealType;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getBloodSugarLevel() {
		return bloodSugarLevel;
	}

	public void setBloodSugarLevel(Integer bloodSugarLevel) {
		this.bloodSugarLevel = bloodSugarLevel;
	}

	public Integer getCarbs() {
		return carbs;
	}

	public void setCarbs(Integer carbs) {
		this.carbs = carbs;
	}

	public Integer getInsulin() {
		return insulin;
	}

	public void setInsulin(Integer insulin) {
		this.insulin = insulin;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

}
