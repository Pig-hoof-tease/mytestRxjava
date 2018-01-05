package com.mytestrxjava.jsoup.bean;

import java.util.ArrayList;
import java.util.List;

public class MJTTH5TypePage {
	private List<MJTTH5Page> pageList = new ArrayList<>();
	private List<MJTTH5MovieInfo> MovieInfoList = new ArrayList<>();
	public List<MJTTH5Page> getPageList() {
		return pageList;
	}
	public void setPageList(List<MJTTH5Page> pageList) {
		this.pageList = pageList;
	}
	public List<MJTTH5MovieInfo> getMovieInfoList() {
		return MovieInfoList;
	}
	public void setMovieInfoList(List<MJTTH5MovieInfo> movieInfoList) {
		MovieInfoList = movieInfoList;
	}
}
