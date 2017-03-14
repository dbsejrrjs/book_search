package com.gun.book_search.controller;

import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.book_search.service.SearchMainServiceNew;


@Controller
public class SearchMainController {
	
	@Inject
	SearchMainServiceNew ser;

	@RequestMapping(value = "/searchMain", method = RequestMethod.GET)
	public String searchMain(Model model) throws SQLException{
		
//		SearchMainServiceNew ser = new SearchMainServiceNew();
		model.addAttribute("searchMainDataCount", ser.getSearchMainList().size());
		model.addAttribute("searchMainData", ser.getSearchMainList());
		
		return "searchMain";
	}

}
