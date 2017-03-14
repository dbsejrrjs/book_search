package com.gun.book_search.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gun.book_search.service.SearchStartService;
import com.gun.book_search.vo.SearchMainVo;


@Controller
public class SearchStartController {
	
	@Inject
	SearchStartService ser;

	@RequestMapping(value = "/searchStart", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> searchMain(Model model, @RequestParam("bookName") String bookName) throws SQLException{
System.out.println(bookName);
		List<SearchMainVo> searchMainList = ser.getSearchStartList(bookName);
		Map<String, String> rst = new HashMap<String, String>();
		rst.put("bookName",bookName);
//		model.addAttribute("searchMainData", ser.getSearchMainList());
		System.out.println("last");
		return rst;
	}

}
