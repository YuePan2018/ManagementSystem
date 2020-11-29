package com.yue.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yue.entity.admin.User;
import com.yue.service.admin.UserService;

/*
 * SpringMVC Controller
 * 
 */

@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	// index page (from login page)
	@RequestMapping(value= "/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/index");
		model.addObject("name","data from MyBatis");
		return model;
	}

	// welcome page (of index page)
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model){
		model.setViewName("system/welcome");
		return model;
	}
	
	// log in page
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("system/login");
		return model;
	}
	
	/* 
	 * Authentication
	 * return value is in json
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(User user, HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		/*if(user == null){
			ret.put("type", "error");
			ret.put("msg", "����д�û���Ϣ��");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			ret.put("type", "error");
			ret.put("msg", "����д�û�����");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "����д���룡");
			return ret;
		}*/
		
		/* user is the username and password from login page, 
		 * but "userInDB" is the corresponding user information in database 
		 */
		User userInDB = userService.findByUsername(user.getUsername());
		if(userInDB == null){
			ret.put("type", "error");
			ret.put("msg", "���û��������ڣ�");
			return ret;
		}
		if(!userInDB.getPassword().equals(user.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "�������");
			return ret;
		}
		/*if(!user.getPassword().equals("   ")){
		ret.put("type", "error");
		ret.put("msg", "�������");
		return ret;
		}*/
		/*
		//˵���û������뼰��֤�붼��ȷ
		//��ʱ��Ҫ��ѯ�û��Ľ�ɫȨ��
		Role role = roleService.find(findByUsername.getRoleId());
		List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//���ݽ�ɫ��ȡȨ���б�
		String menuIds = "";
		for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
		if(!StringUtils.isEmpty(menuIds)){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
		List<Menu> userMenus = menuService.findListByIds(menuIds);
		//�ѽ�ɫ��Ϣ���˵���Ϣ�ŵ�session��
		request.getSession().setAttribute("admin", findByUsername);
		request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
		*/
		
		// store login information in session
		request.getSession().setAttribute("admin", userInDB);	
		// return data to jsp 
		ret.put("type", "success");
		ret.put("msg", "��¼�ɹ���");
		return ret;
	}
	
	
}
