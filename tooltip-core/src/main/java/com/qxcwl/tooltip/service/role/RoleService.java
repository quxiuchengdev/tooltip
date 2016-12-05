package com.qxcwl.tooltip.service.role;

import com.qxcwl.tooltip.service.CrudService;
import com.qxcwl.tooltip.dao.role.RoleDao;
import com.qxcwl.tooltip.model.role.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService extends CrudService<RoleDao,Role> {

	public int insert(Role role) {
		dao.deleteRoleMenu(role);
		dao.insertRoleMenu(role);
		return super.insert(role);
	}

	public int update(Role role) {
		dao.deleteRoleMenu(role);
		dao.insertRoleMenu(role);
		return super.update(role);
	}
}
