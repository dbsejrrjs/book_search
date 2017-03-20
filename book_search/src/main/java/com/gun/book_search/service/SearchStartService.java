package com.gun.book_search.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gun.book_search.dao.SearchMainDao;
import com.gun.book_search.vo.SearchMainVo;

@Service
public class SearchStartService {
	
	@Inject
	SearchMainDao searchMainDao;
	
	public Map<String, Object> getSearchStartList(String strBookName){

		List<SearchMainVo> rstData = searchMainDao.getSelectAllList();

		String strUrlText = urlTextRead(rstData.get(0).getUrl(),strBookName);

		//HTML 태그 및 &nbsp; 삭제 정규식
		String strTagDelRegex = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>|(&nbsp;)";
		
		//임시용 변수(다용도, 새로운 값이 할당되는 순간까지의 lifeCycle를 갖음)
		String strTemp = "";
		
		//검색된 도서Text 전체 가져오기
		strTemp = regularConvertDataIf("ss_book_box\">.*(</div><div style=\"clear:both;\"></div></div>)", strUrlText, "WHILE");

		//검색된 도서 배열로 변환
		String[] arrStrData = strTemp.toString().split("[^\\s]ss_book_box");
			
		//각 도서별 데이터 추출
		Map<String, Object> maRtnData = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> arTotalData = new ArrayList<Map<String, Object>>();
		Map<String, Object> maTotalInData;
		ArrayList<Map<String, String>> arDetailData;
		Map<String, String> maDetailInData;
		
		//검색 결과
		maRtnData.put("bookName", strBookName);
		maRtnData.put("totalCnt", arrStrData.length);
		
		for(int i = 0; i < arrStrData.length; i++){
			
			//book name
			strTemp = regularConvertDataIf("\\[중고\\].*</li>",arrStrData[i],"IF");
			maTotalInData = new HashMap<String, Object>();
			maTotalInData.put("bookName", strTemp.replaceAll(strTagDelRegex, ""));
			arTotalData.add(maTotalInData);
			
			//book image
			strTemp = regularConvertDataIf("(http://image.aladin.co.kr/product/)[\\w|\\/]*(.jpg)",arrStrData[i],"IF");
			arTotalData.get(i).put("bookImg", strTemp);

			//book detail
			Pattern p = Pattern.compile("(http://off.aladin.co.kr/usedstore/wproduct.aspx\\?)[\\w|\\=|\\&]*");
			Matcher m = p.matcher(arrStrData[i]);
			arDetailData = new ArrayList<Map<String, String>>();
			while(m.find()){
				maDetailInData = new HashMap<String, String>();
				strTemp = m.group();
				
				//대상 URL
				maDetailInData.put("targetUrl", strTemp);
				
				//대상 지점명
				strUrlText = urlTextRead(strTemp, "");
				strTemp = regularConvertDataIf("\\[([가-힣]|\\.|\\s)*\\]( 서가 단면도)",strUrlText,"IF");
				maDetailInData.put("targetName", strTemp.replaceAll(" 서가 단면도", ""));
				
				//대상 가격정보
				strTemp = regularConvertDataIf("(<!-- 도서 상세 정보 -->).*(<!--// 도서 상세 정보 -->)",strUrlText,"IF");
				strTemp = regularConvertDataIf("(최저가).*원",strTemp,"IF");
				maDetailInData.put("targetPrice", strTemp.replaceAll(strTagDelRegex, "").replaceAll("(\\s|I)*", "").replaceAll("원",	"원 "));
				
				arDetailData.add(maDetailInData);
			}
			
			arTotalData.get(i).put("bookDetail", arDetailData);
		}
			
		maRtnData.put("searchList", arTotalData);
		
		return maRtnData;
	}

	//URL에서 Text를 Read
	public String urlTextRead(String strUrl, String strBookName){

		BufferedReader brText = null;
		StringBuffer sbUrlText = new StringBuffer();
		HttpURLConnection urlCon = null;
		
		try {
			URL urlInit = new URL(strUrl);
			urlCon = (HttpURLConnection) urlInit.openConnection();
			String strConType = urlCon.getContentType().toUpperCase();
			
			//파라미터 encoding
			if(!"".equals(strBookName)){
				if(strConType.indexOf("UTF-8") != -1){
					strBookName = URLEncoder.encode(strBookName, "UTF-8");
				}else{
					strBookName = URLEncoder.encode(strBookName, "EUC-KR");
				}
				
				urlCon.disconnect();
				urlInit = new URL(strUrl+strBookName);
				urlCon = (HttpURLConnection) urlInit.openConnection();
			}
	
			if(strConType.indexOf("UTF-8") != -1){
				brText = new BufferedReader(new InputStreamReader(urlCon.getInputStream() , "UTF-8"));
			}else{
				brText = new BufferedReader(new InputStreamReader(urlCon.getInputStream() , "EUC-KR"));
			}
			
			String strLine;
			while ((strLine = brText.readLine()) != null){
				sbUrlText.append(strLine);
			}
			brText.close();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(brText != null){ brText.close(); }
				if(urlCon != null){ urlCon.disconnect(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sbUrlText.toString();
	}

	//정규식을 통한 Text Convert
	public String regularConvertDataIf(String strRegex, String strText, String strLoop){

		StringBuffer sbRtnText = new StringBuffer();
		Pattern p = Pattern.compile(strRegex);
		Matcher m = p.matcher(strText);
		if("IF".equals(strLoop)){
			if(m.find()){
				sbRtnText.append(m.group());
			}
		}else if("WHILE".equals(strLoop)){
			while(m.find()){
				sbRtnText.append(m.group());
			}
		}
		
		return sbRtnText.toString();
	}

}
