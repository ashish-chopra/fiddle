package com.fun.apps.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Marker {

	private int id;
	private String latitude;
	private String longitude;
	private String location;
	private String experience;
	private String title;	
	private String timeOfVisit;
	private String user;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTimeOfVisit() {
		return timeOfVisit;
	}

	public void setTimeOfVisit(String timeOfVisit) {
		this.timeOfVisit = timeOfVisit;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Marker [id=" + id + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", location=" + location
				+ ", title=" + title + ", experience=" + experience
				+ ", user=" + user + ", timeOfVisit=" + timeOfVisit + "]";
	}
}
