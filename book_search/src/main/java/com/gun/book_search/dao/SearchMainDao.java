package com.gun.book_search.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.book_search.vo.SearchMainVo;

@Repository
public class SearchMainDao {

	@Inject
	private SqlSession sqlsession;
	
	private static final String nmspace = "bookSearch.searchMain";

	public List<SearchMainVo> getSelectAllList(){
		return sqlsession.selectList(nmspace+".selectAllList");
	}
}
