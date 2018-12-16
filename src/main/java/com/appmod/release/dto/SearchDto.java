package com.appmod.release.dto;

public class SearchDto {
	
	private String systemCode;
	private String releaseDate;
	//Not sure of the search parameters
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

}
