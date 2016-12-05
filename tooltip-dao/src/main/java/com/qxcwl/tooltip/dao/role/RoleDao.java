package com.qxcwl.tooltip.dao.role;


import com.qxcwl.tooltip.annotation.MyBatisDao;
import com.qxcwl.tooltip.dao.CrudDao;
import com.qxcwl.tooltip.model.role.Role;

/**
 * @Author quxiucheng
 * @ClassName RoleDao
 * @Description
 * @Date 2016/10/18 10:58
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {

	/**
	 * 插入中间表,批量插入
	 * @param role
	 * @return
	 */
	public int insertRoleMenu(Role role);

	/**
	 * 删除中间表,批量删除
	 * @param role
	 * @return
	 */
	public int deleteRoleMenu(Role role);

}
