package com.qxcwl.tooltip.service.user;


import com.qxcwl.tooltip.service.CrudService;
import com.qxcwl.tooltip.dao.user.UserDao;
import com.qxcwl.tooltip.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {


	public int insert(User user) {
		dao.deleteUserRole(user);
		dao.insertUserRole(user);
		return super.insert(user);
	}


	public int update(User user) {
		dao.deleteUserRole(user);
		dao.insertUserRole(user);
		return super.update(user);
	}
}
