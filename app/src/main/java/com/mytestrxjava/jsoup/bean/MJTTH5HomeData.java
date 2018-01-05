package com.mytestrxjava.jsoup.bean;

import java.util.ArrayList;
import java.util.List;

public class MJTTH5HomeData {
	private List<MJTTH5Menu> menuList = new ArrayList<>();
	private List<MJTTH5MovieInfo> MovieInfoList = new ArrayList<>();
	public List<MJTTH5Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MJTTH5Menu> menuList) {
		this.menuList = menuList;
	}
	public List<MJTTH5MovieInfo> getMovieInfoList() {
		return MovieInfoList;
	}
	public void setMovieInfoList(List<MJTTH5MovieInfo> movieInfoList) {
		MovieInfoList = movieInfoList;
	}
	
}
