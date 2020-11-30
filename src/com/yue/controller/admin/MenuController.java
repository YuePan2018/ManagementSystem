package com.yue.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 *  controller to manage manu
 */

@Controller
@RequestMapping("/admin/menu")
public class MenuController {
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		//model.addObject("topList", menuService.findTopList());
		model.setViewName("menu/list");
		return model;
	}
}
