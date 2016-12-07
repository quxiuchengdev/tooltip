package com.qxcwl.tooltip.web.admin.role;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qxcwl.tooltip.common.page.Page;
import com.qxcwl.tooltip.common.utils.StringUtils;
import com.qxcwl.tooltip.model.DataEntity;
import com.qxcwl.tooltip.model.menu.Menu;
import com.qxcwl.tooltip.model.role.Role;
import com.qxcwl.tooltip.model.user.User;
import com.qxcwl.tooltip.service.menu.MenuService;
import com.qxcwl.tooltip.service.role.RoleService;
import com.qxcwl.tooltip.web.BaseController;
import com.qxcwl.tooltip.web.admin.utils.DictUtils;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "${adminPath}/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;
	
	@ModelAttribute("role")
	public Role get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return roleService.get(id);
		}else{
			return new Role();
		}
	}
	
	@RequestMapping(value = {"list",""})
	public String list(Role role ,HttpServletRequest request, HttpServletResponse response,Model model){
		//PageList<Role> list= roleService.findPage(new Paginator(request,true), new Role());
		Page<Role> page= roleService.findPage(new Page(request,response), new Role());
		model.addAttribute("list", page.getList());
		model.addAttribute("page", page);
		return "modules/role/roleList";
	}
	
	@RequestMapping(value = "form")
	public String form(Role role,HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("role", role);
		return "modules/role/roleForm";
	}
	
	@RequestMapping(value = "save")
	public String save(Role role, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		String menuIds = request.getParameter("menuIds");
		if(StringUtils.isNotEmpty(menuIds)){
			List<Menu> menuList = Lists.newArrayList();
			String[] menuIdArr = menuIds.split(",");
			for (String menuId : menuIdArr) {
				Menu menu = menuService.get(menuId);
				menuList.add(menu);
			}
			role.setMenuList(menuList);
		}
		role.setDelFlag(DataEntity.DEL_FLAG_NORMAL);
		if(StringUtils.isEmpty(role.getId())){
			role.setId(UUID.randomUUID().toString().replace("-", ""));
			role.setUpdateDate(new Date());
			role.setCreateDate(new Date());
			role.setUpdateBy(new User("1"));
			role.setCreateBy(new User("1"));
			roleService.insert(role);
		}else{
			role.setUpdateDate(new Date());
			role.setUpdateBy(new User("1"));
			roleService.update(role);
		}
		return "redirect:" + adminPath + "/role/list?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Role role, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		roleService.delete(role);
		return "redirect:" + adminPath + "/role/list?repage";
	}

	@ResponseBody
	@RequestMapping(value = "datatables")
	public String datatables(Role role, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,
			@RequestParam(required=false,defaultValue="10") int start ,
			@RequestParam(required=false,defaultValue="1") int length) {
		//
		String[] sortCols = {"name","enname", "roleType", "useable", "remarks"};
		String draw = request.getParameter("draw");
		//需要排序的列
		String orderColumn = request.getParameter("order[0][column]");
		//排序方式
		String orderDir = request.getParameter("order[0][dir]");

		String search = request.getParameter("search[value]");
		
		Page<Role> list= roleService.findPage(new Page(getPage(start, length),length), new Role());
		JSONArray jsonArray = new JSONArray();
		for (Role r : list.getList()) {
			JSONObject j = new JSONObject();
        	j.put("name", r.getName());
        	j.put("enname", r.getEnname());
        	j.put("roleType", r.getRoleType());
        	j.put("useable", DictUtils.getDictLabel(r.getUseable(),"sys_is_able",""));
        	j.put("remarks", r.getRemarks());
        	j.put("id", r.getId());
        	//j.put("status", u.getStatus()==null?"":u.getStatus());
        	jsonArray.add(j);
		}
		JSONObject json = new JSONObject();
        json.put("data", jsonArray);
        json.put("totalRecords", list.getCount());
        json.put("recordsFiltered", list.getCount());
        json.put("draw", draw);
		return json.toString();
	}

	@ResponseBody
	@RequestMapping(value = "treeview")
	public String treeview(String roleId, HttpServletRequest request, HttpServletResponse response, Model model) {
		Role role = roleService.get(roleId);
		List<Menu> menuList = menuService.findList(new Menu());
		JSONArray ja = new JSONArray();
		for (Menu menu : menuList) {
			JSONObject jo = new JSONObject();

			jo.put("id", menu.getId());
			jo.put("pId", menu.getParent().getId());
			jo.put("name", menu.getName());

			ja.add(jo);
		}

		JSONArray roleMenuList = new JSONArray();
		if (role != null) {
			for (Menu menu : role.getMenuList()) {
				JSONObject jo = new JSONObject();
				//menu = menuService.get(menu.getId());
				jo.put("id", menu.getId());
				jo.put("pId", menu.getParent().getId());
				jo.put("name", menu.getName());

				roleMenuList.add(jo);
			}
		}
		JSONObject result = new JSONObject();
		result.put("menuList",ja);
		result.put("roleMenuList",roleMenuList);
		return result.toString();
	}
	
	
}
