package com.mytestrxjava.jsoup.bean;

import java.util.ArrayList;
import java.util.List;

import bean.TVEpisodes;

public class MJTTH5MovieInfo {
	private String movieName;
	private String movieUrl;
	private String thumbNailName;
	private String thumbNailUrl;
	private String currenttate;
	private String oldName;
	private String movieType;
	public String getMovieType() {
		return movieType;
	}
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	private String movieHot;
	private String yearOfIssue;
	private String movieInfo;
	private String upDataTime;
	public String getUpDataTime() {
		return upDataTime;
	}
	public void setUpDataTime(String upDataTime) {
		this.upDataTime = upDataTime;
	}
	private List<TVEpisodes> MovieNum=new ArrayList<>();
	
	public String getYearOfIssue() {
		return yearOfIssue;
	}
	public void setYearOfIssue(String yearOfIssue) {
		this.yearOfIssue = yearOfIssue;
	}
	public String getMovieInfo() {
		return movieInfo;
	}
	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
	}
	public List<TVEpisodes> getMovieNum() {
		return MovieNum;
	}
	public void setMovieNum(List<TVEpisodes> movieNum) {
		MovieNum = movieNum;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieUrl() {
		return movieUrl;
	}
	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}
	public String getThumbNailName() {
		return thumbNailName;
	}
	public void setThumbNailName(String thumbNailName) {
		this.thumbNailName = thumbNailName;
	}
	public String getThumbNailUrl() {
		return thumbNailUrl;
	}
	public void setThumbNailUrl(String thumbNailUrl) {
		this.thumbNailUrl = thumbNailUrl;
	}
	public String getCurrenttate() {
		return currenttate;
	}
	public void setCurrenttate(String currenttate) {
		this.currenttate = currenttate;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getMovieHot() {
		return movieHot;
	}
	public void setMovieHot(String movieHot) {
		this.movieHot = movieHot;
	}
}
