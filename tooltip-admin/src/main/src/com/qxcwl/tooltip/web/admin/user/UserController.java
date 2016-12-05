package com.qxcwl.tooltip.web.admin.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qxcwl.tooltip.common.page.Page;
import com.qxcwl.tooltip.common.utils.StringUtils;
import com.qxcwl.tooltip.model.DataEntity;
import com.qxcwl.tooltip.model.role.Role;
import com.qxcwl.tooltip.model.user.User;
import com.qxcwl.tooltip.service.role.RoleService;
import com.qxcwl.tooltip.service.user.UserService;
import com.qxcwl.tooltip.web.admin.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@ModelAttribute("user")
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return userService.get(id);
		}else{
			return new User();
		}
	}

	@RequestMapping(value = {"list",""})
	public String list(User user ,HttpServletRequest request, HttpServletResponse response,Model model){
		//PageList<User> list= userService.findPage(new Paginator(request,true), new User());
		Page<User> page= userService.findPage(new Page(request,response), new User());
		model.addAttribute("list", page.getList());
		model.addAttribute("page", page);
		return "modules/sys/userList";
	}
	
	@RequestMapping(value = "form")
	public String form(User user,HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleService.findList(new Role()));
		return "modules/sys/userForm";
	}
	
	@RequestMapping(value = "save")
	public String save(User user, String roleIds,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		String roleListIds = request.getParameter("roleIds");
		System.out.println(roleListIds);
		System.out.println(roleIds);
		if(StringUtils.isNotEmpty(roleIds)){
			String[] roleIdArr = roleIds.split(",");
			List<Role> roleList = Lists.newArrayList();
			for (String roleId : roleIdArr) {
				Role role = roleService.get(roleId);
				roleList.add(role);
			}
			user.setRoleList(roleList);
		}
		user.setDelFlag(DataEntity.DEL_FLAG_NORMAL);
		user.setStatus(User.STATUS.NORMAL.toString());
		if(StringUtils.isEmpty(user.getId())){
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			userService.insert(user);
		}else{
			userService.update(user);
		}
		return "redirect:" + adminPath + "/sys/user/list?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "datatables")
	public String datatables(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,
			@RequestParam(required=false,defaultValue="10") int iDisplayStart ,
			@RequestParam(required=false,defaultValue="1") int iDisplayLength) {
		
		String sEcho = request.getParameter("sEcho");
		//String iDisplayStart = request.getParameter("iDisplayStart");
		//String iDisplayLength = request.getParameter("iDisplayLength");
		String iSortCol_0 = request.getParameter("iSortCol_0");
		String sSortDir_0 = request.getParameter("sSortDir_0");
		String sSearch = request.getParameter("sSearch");
		String iTotalRecords = request.getParameter("iTotalRecords");
		String iTotalDisplayRecords = request.getParameter("iTotalDisplayRecords");
		String aaData = request.getParameter("aaData");
		String sColumns = request.getParameter("sColumns");
		
		Page<User> list= userService.findPage(new Page(getPage(iDisplayStart, iDisplayLength),iDisplayLength), new User());
	//	Paginator paginator = list.getPaginator();
		JSONArray jsonArray = new JSONArray();
		for (User u : list.getList()) {
			JSONObject j = new JSONObject();
        	j.put("nickname", u.getNickname());
        	j.put("username", u.getUsername()==null?"":u.getUsername());
        	j.put("mobileNumber", u.getMobileNumber()==null?"":u.getMobileNumber());
        	j.put("regisTime", u.getRegisTime()==null?"":u.getRegisTime());
        	j.put("status", u.getStatus()==null?"":u.getStatus());
        	jsonArray.add(j);
		}
		JSONObject json = new JSONObject();
        json.put("aaData", jsonArray.toString());
        json.put("iTotalRecords", list.getCount());
        json.put("iTotalDisplayRecords", list.getCount());
        json.put("sEcho", sEcho);
       // json.put("pageNo", paginator.getPage());
       // json.put("pageSize", paginator.getTotalPages());
		return json.toString();
	}
	
	
}
