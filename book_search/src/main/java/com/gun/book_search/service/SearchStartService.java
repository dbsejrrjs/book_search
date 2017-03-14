package com.gun.book_search.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gun.book_search.dao.SearchMainDao;
import com.gun.book_search.vo.SearchMainVo;

@Service
public class SearchStartService {
	
	@Inject
	SearchMainDao searchMainDao;
	
	public List<SearchMainVo> getSearchStartList(String bookName){

		List<SearchMainVo> rstData = searchMainDao.getSelectAllList();
		
		BufferedReader brText = null;
		try{
			URL urlInit = new URL(rstData.get(0).getUrl()+bookName);
			URLConnection urlCon = urlInit.openConnection();
			
//			String strConType = urlCon.getContentType().toUpperCase();
//			System.out.println(strConType);
//			if(strConType.indexOf("EUC-KR") != -1){
//				brText = new BufferedReader(new InputStreamReader(urlCon.getInputStream() , "EUC-KR"));
//			}else if(strConType.indexOf("UTF-8") != -1){
//				brText = new BufferedReader(new InputStreamReader(urlCon.getInputStream() , "UTF-8"));
//			}
			brText = new BufferedReader(new InputStreamReader(urlCon.getInputStream() , "EUC-KR"));
		
			String strLine;
			StringBuffer sbText = new StringBuffer();
			while ((strLine = brText.readLine()) != null){
				sbText.append(strLine).append("\n");
			}
		
			System.out.println(sbText);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				brText.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return rstData;
	}

}
