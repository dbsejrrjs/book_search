package com.gun.book_search;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gun.book_search.vo.SearchUrlVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private SqlSessionFactory sqlSessionFactory;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/aa", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		//MYSQL
		 InputStream inputStream = null;
		  
		  try {
		   String resource = "com/gun/book_search/mybatis/config/mybatis_config.xml";
		   inputStream = Resources.getResourceAsStream(resource);
		  } catch (IOException e) {
		   e.printStackTrace();
		  }

		  sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
		  SqlSession session = sqlSessionFactory.openSession();
		  SearchUrlVo temp = null;
		  try {
			  int i = 2;
		    temp = (SearchUrlVo)session.selectOne("tb_search_url.testSelect", i);
		    System.out.println(temp.getName() + temp.getUrl());
		  } finally {
		    session.close();
		  }
		
		return "home";
	}
	
}
