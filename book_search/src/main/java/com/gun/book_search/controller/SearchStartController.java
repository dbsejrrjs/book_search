package com.gun.book_search.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gun.book_search.service.SearchStartService;


@Controller
public class SearchStartController {
	
	@Inject
	SearchStartService ser;

	@RequestMapping(value = "/searchStart", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> searchMain(Model model, @RequestParam("bookName") String bookName) throws SQLException{

		Map<String, Object> maSearchList = ser.getSearchStartList(bookName);
//		model.addAttribute("searchMainData", ser.getSearchMainList());
		
		return maSearchList;
	}

}
