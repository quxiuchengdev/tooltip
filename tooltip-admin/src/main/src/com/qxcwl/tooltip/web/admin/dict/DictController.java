package com.qxcwl.tooltip.web.admin.dict;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qxcwl.tooltip.common.page.Page;
import com.qxcwl.tooltip.common.utils.StringUtils;
import com.qxcwl.tooltip.model.DataEntity;
import com.qxcwl.tooltip.model.dict.Dict;
import com.qxcwl.tooltip.model.user.User;
import com.qxcwl.tooltip.service.dict.DictService;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(value = "${adminPath}/dict")
public class DictController extends BaseController {
	
	@Autowired
	private DictService dictService;
	
	@ModelAttribute("dict")
	public Dict get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return dictService.get(id);
		}else{
			return new Dict();
		}
	}
	
	@RequestMapping(value = {"list",""})
	public String list(Dict dict ,HttpServletRequest request, HttpServletResponse response,Model model){
		return "modules/dict/dictList";
	}
	
	@RequestMapping(value = "form")
	public String form(Dict dict,HttpServletRequest request, HttpServletResponse response,Model model){
		model.addAttribute("dict", dict);
		return "modules/dict/dictForm";
	}
	
	@RequestMapping(value = "add")
	public String add(Dict dict,HttpServletRequest request, HttpServletResponse response,Model model){
		dict.setId(null);
		dict.setValue(null);
		dict.setLabel(null);
		dict.setSort(dict.getSort()+10);
		model.addAttribute("dict", dict);
		return "modules/dict/dictForm";
	}
	
	@RequestMapping(value = "save")
	public String save(Dict dict, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(StringUtils.isEmpty(dict.getId())){
			dict.setDelFlag(DataEntity.DEL_FLAG_NORMAL);
			dict.setId(UUID.randomUUID().toString().replace("-", ""));
			dict.setUpdateDate(new Date());
			dict.setCreateDate(new Date());
			dict.setUpdateBy(new User("1"));
			dict.setCreateBy(new User("1"));
			dictService.insert(dict);
		}else{
			dict.setUpdateDate(new Date());
			dict.setUpdateBy(new User("1"));
			dictService.update(dict);
		}
		return "redirect:" + adminPath + "/dict/list?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Dict dict, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		dictService.delete(dict);
		return "redirect:" + adminPath + "/dict/list?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "datatables")
	public String datatables(Dict dict, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,
			@RequestParam(required=false,defaultValue="10") int start ,
			@RequestParam(required=false,defaultValue="1") int length) {
		//定义需要排序的列
        String[] sortCols = {"","value", "label", "type", "sort","description"};
		String sEcho = request.getParameter("sEcho");
		//需要排序的列
		String orderColumn = request.getParameter("order[0][column]");
		//排序方式
		String orderDir = request.getParameter("order[0][dir]");

		String search = request.getParameter("search[value]");

		List<String> orderBys = Lists.newArrayList();
		if(StringUtils.isNotEmpty(orderColumn)){
			//String columnsData = request.getParameter("columns["+orderColumn+"][data]");
			String columnsData = sortCols[Integer.valueOf(orderColumn)];

			if(StringUtils.isNotEmpty(columnsData)){

				orderBys.add(columnsData + " " + orderDir);
			}
		}

		//全局搜索
		if(StringUtils.isNotEmpty(search)){
			dict.setGlobalSearch(search);
		}
		Page<Dict> page = new Page<Dict>(getPage(start, length),getLimit(length));

		page.setOrderBys(orderBys);

		Page<Dict> dictPage = dictService.findPage(page, dict);
		
		JSONObject json = new JSONObject();
		List<Dict> list = dictPage.getList();
		JSONArray ja = new JSONArray();
		for (Dict dict1 : list) {
			JSONObject jo = new JSONObject();
			jo.put("description", dict1.getDescription());
			jo.put("id", dict1.getId());
			jo.put("label", dict1.getLabel());
			jo.put("sort", dict1.getSort());
			jo.put("type", dict1.getType());
			jo.put("value", dict1.getValue());
			ja.add(jo);
		}

		json.put("aaData", JSON.parseArray(JSON.toJSONString(list)));
        json.put("iTotalRecords", dictPage.getCount());
        json.put("iTotalDisplayRecords", dictPage.getCount());
        json.put("sEcho", sEcho);

		return json.toJSONString();
	}
	
}
