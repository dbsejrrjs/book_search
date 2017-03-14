package com.gun.book_search.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.gun.book_search.dao.SearchMainDao;
import com.gun.book_search.vo.SearchMainVo;

@Service
public class SearchMainServiceNew {
	
	@Inject
	SearchMainDao searchMainDao;
	
	public List<SearchMainVo> getSearchMainList(){

		List<SearchMainVo> rstData = searchMainDao.getSelectAllList();
		
		return rstData;
	}

}
