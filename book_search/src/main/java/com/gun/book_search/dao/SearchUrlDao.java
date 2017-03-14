package com.gun.book_search.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.gun.book_search.vo.SearchUrlVo;

@Repository
public class SearchUrlDao {

	@Inject
	private SqlSession sqlsession;
	
	private static final String nmspace = "tb.searchUrl";

	public SearchUrlVo getSelect(SearchUrlVo searchUrlVo){
		return sqlsession.selectOne(nmspace+".testSelect", searchUrlVo);
	}

	public List<SearchUrlVo> getSelectList(){
		return sqlsession.selectList(nmspace+".testSelectList");
	}
}
