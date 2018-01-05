package com.mytestrxjava.jsoup;

import com.mytestrxjava.jsoup.bean.MJTTH5HomeData;
import com.mytestrxjava.jsoup.bean.MJTTH5Menu;
import com.mytestrxjava.jsoup.bean.MJTTH5MovieInfo;
import com.mytestrxjava.jsoup.bean.MJTTH5Page;
import com.mytestrxjava.jsoup.bean.MJTTH5TypePage;
import com.mytestrxjava.utils.LogUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.CommonException;
import bean.TVEpisodes;


public class MJTTH5BIZ {
	private static String homeUrl="http://m.meijutt.com/";
	private static String searchUrl="http://m.meijutt.com/search.asp";

	// 获取首页数据
	public MJTTH5HomeData MJTTH5HomeData() throws CommonException {
		String urlStr = "http://m.meijutt.com/";
		MJTTH5HomeData HomeData = new MJTTH5HomeData();
		List<MJTTH5Menu> MenuList = new ArrayList<>();
		List<MJTTH5MovieInfo> MoveInfoList = new ArrayList<>();
		MJTTH5Menu Menuitem = null;
		MJTTH5MovieInfo MoveInfoitem = null;
		Document doc = null;
		try {
			doc = Jsoup.connect(urlStr).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block

		}
		if (doc == null) {
			return null;
		}
		// 取所有的menu的列表
		Elements MenuColumn = doc.getElementsByClass("menu");
		for (int i = 0; i < MenuColumn.size(); i++) {
			// 取单个Menu中的a标签列表
			Elements tag_aElements = MenuColumn.get(i).getElementsByTag("a");
			for (int j = 0; j < tag_aElements.size(); j++) {
				// 取a标签并取出链接
				Menuitem = new MJTTH5Menu();
				Element a_ele = tag_aElements.get(j);
				String a_href = a_ele.absUrl("href");
				String a_text = a_ele.text();
				Menuitem.setMenuUrl(a_href);
				Menuitem.setMenuName(a_text);
				MenuList.add(Menuitem);
			}
		}

		// 取所有模块的略缩图列表
		Elements MoveInfoColumn = doc.getElementsByClass("imgBg1");
		for (int i = 0; i < MoveInfoColumn.size(); i++) {
			// 取略缩图信息
			MoveInfoitem = new MJTTH5MovieInfo();
			Element thumbNail_ele = MoveInfoColumn.get(i);
			String thumbNail_href = thumbNail_ele.absUrl("href");
			String thumbNail_nowstate = thumbNail_ele.text();
			Elements img_ele = thumbNail_ele.getElementsByTag("img");
			String img_url = img_ele.attr("src");
			String img_alt = img_ele.attr("alt");
			String img_title = img_ele.attr("title");
			MoveInfoitem.setMovieName(img_alt);
			MoveInfoitem.setMovieUrl(thumbNail_href);
			MoveInfoitem.setThumbNailName(img_title);
			MoveInfoitem.setThumbNailUrl(img_url);
			MoveInfoitem.setCurrenttate(thumbNail_nowstate);
			MoveInfoList.add(MoveInfoitem);
		}
		HomeData.setMenuList(MenuList);
		HomeData.setMovieInfoList(MoveInfoList);
		return HomeData;
	}

	public MJTTH5MovieInfo getMovieInfo(String htmlUrl) {
		MJTTH5MovieInfo MovieInfo = new MJTTH5MovieInfo();
		List<TVEpisodes> MovieNum = new ArrayList<>();
		TVEpisodes videoNum;
		Document doc = null;
		try {
			doc = Jsoup.connect(htmlUrl).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block

		}
		if (doc == null) {
			return null;
		}
		Elements MovieInfo_ele = doc.getElementsByClass("vodbox zm");
		String ImgUrl=MovieInfo_ele.get(0).getElementsByTag("img").get(0).attr("src");
		String ImgText=MovieInfo_ele.get(0).getElementsByTag("img").get(0).attr("alt");
		String title = MovieInfo_ele.get(0).getElementsByClass("title").text();
		String Name = MovieInfo_ele.get(0).getElementsByClass("actor").get(0)
				.text();
		String oldName = Name.substring(0, Name.lastIndexOf("类型"));
		Elements wide_ele = MovieInfo_ele.get(0).getElementsByClass("wide");
		for (int i = 0; i < wide_ele.size(); i++) {
			switch (i) {
				case 0:
					MovieInfo.setMovieType(wide_ele.get(i).text());
					break;
				case 1:
					MovieInfo.setCurrenttate(wide_ele.get(i).text());
					break;
				case 2:
					MovieInfo.setYearOfIssue(wide_ele.get(i).text());
					break;
				case 3:
					MovieInfo.setUpDataTime(wide_ele.get(i).text());
					break;
				default:
					break;
			}
		}
		String MovieText = doc.getElementById("voddetail").text();
		if (doc.getElementsByClass("vodlist clear down_list").size()>0){
			Element VideoNums_ele = doc.getElementsByClass(
					"vodlist clear down_list").get(0);
			Elements VideoNum_ele = VideoNums_ele.getElementsByTag("a");
			for (int i = 0; i < VideoNum_ele.size(); i++) {
				videoNum = new TVEpisodes();
				Element video = VideoNum_ele.get(i);
				String videoUrl = video.attr("href");
				String videoNumText = video.text();
				videoNum.setTVEpisodesUrl(videoUrl);
				videoNum.setTVEpisodesNum(videoNumText);
				MovieNum.add(videoNum);
			}
		}

		MovieInfo.setMovieName(title);
		MovieInfo.setOldName(oldName);
		MovieInfo.setMovieInfo(MovieText);
		MovieInfo.setMovieNum(MovieNum);
		MovieInfo.setThumbNailName(ImgText);
		MovieInfo.setThumbNailUrl(ImgUrl);
		return MovieInfo;
	}

	public MJTTH5TypePage getTypeList(String html, Boolean isSearch) throws CommonException {
		MJTTH5TypePage pageType =new MJTTH5TypePage();

		List<MJTTH5MovieInfo> TpyeList = new ArrayList<>();
		MJTTH5MovieInfo item = new MJTTH5MovieInfo();
		List<MJTTH5Page> pageList = new ArrayList<>();
		MJTTH5Page pageItem = null;
		Document doc = null;
		if (isSearch) {
			try {
				doc = Jsoup.parse(HTTPGetPost.sendPost(searchUrl, "searchword="+ URLEncoder.encode(html,"utf-8")));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			try {
				doc = Jsoup.connect(html).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		doc.setBaseUri("http://m.meijutt.com/");
		Elements TpyeList_ele = doc.getElementsByClass("lb_zk");
		Elements ul_ele=TpyeList_ele.get(0).getElementsByTag("ul");
		for (int i = 0; i < ul_ele.size(); i++) {
			Elements a_ele=ul_ele.get(i).getElementsByTag("a");
			for (int j = 0; j < a_ele.size(); j++) {
				item=new MJTTH5MovieInfo();
				Element a_child_ele =a_ele.get(j);
				String videoUrl= a_child_ele.absUrl("href");
				String ImgUrl="";
				try{
					ImgUrl=a_child_ele.getElementsByTag("img").get(0).attr("src");
				}catch (Exception e){
					LogUtils.dLog("没有找到图片链接");
				}

				Elements p_ele=a_child_ele.getElementsByTag("p");
				if (p_ele.size()==0){
					continue;
				}
				for (int k = 0; k < p_ele.size(); k++) {
					switch (k) {
						case 0:
							item.setMovieName(p_ele.get(k).text());
							break;
						case 1:
							item.setOldName(p_ele.get(k).text());
							break;
						case 2:
							item.setCurrenttate(p_ele.get(k).text());
							break;
						case 3:
							item.setMovieHot(p_ele.get(k).text());
							break;
						default:
							break;
					}
				}
				item.setMovieUrl(videoUrl);
				item.setThumbNailUrl(ImgUrl);
				TpyeList.add(item);
			}
		}
		Elements page_ele =doc.getElementsByClass("page clear");
		Elements page_a_ele=page_ele.get(0).getElementsByTag("a");
		for (int i = 2; i < page_a_ele.size()-2; i++) {
			pageItem=new MJTTH5Page();
			Element a_child_ele =page_a_ele.get(i);
			String PageUrl= searchUrl+a_child_ele.attr("href");
			String PageNum=a_child_ele.text();
			pageItem.setPage(PageNum);
			pageItem.setPageUrl(PageUrl);
			pageList.add(pageItem);
		}

		pageType.setPageList(pageList);
		pageType.setMovieInfoList(TpyeList);
		return pageType;
	}
}
