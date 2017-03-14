package com.gun.book_search;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.book_search.dao.SearchUrlDao;
import com.gun.book_search.vo.SearchUrlVo;


@Controller
public class SearchUrlTest {

	@Inject
	SearchUrlDao searchUrlDao;
	
	@RequestMapping(value = "/searchUrlTest", method = RequestMethod.GET)
	public String test(Model model) throws SQLException{

		SearchUrlVo searchUrlVo = new SearchUrlVo();
		searchUrlVo.setNo(1);

		SearchUrlVo resultData = searchUrlDao.getSelect(searchUrlVo);

		List<SearchUrlVo> resultData2 = searchUrlDao.getSelectList();
		
		model.addAttribute("resultData", resultData2);
		
		
		return "home";
	}

}
