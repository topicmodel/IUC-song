package com.how2java.tmall.service;

import com.how2java.tmall.dao.InventorDAO;
import com.how2java.tmall.dao.UserDAO;
import com.how2java.tmall.pojo.Inventor;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;

	public boolean isExist(String name) {
		User user = getByName(name);
		return null != user;
	}

	public User getByName(String name) {
		return userDAO.findByName(name);
	}

	public User getById(int id) {
		return userDAO.findById(id);
	}

	//根据用户名和密码检查是否存在用户
	public User get(String name, String password) {
		return userDAO.getByNameAndPassword(name, password);
	}

	public Page4Navigator<User> list(int start, int size, int navigatePages) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		Pageable pageable = new PageRequest(start, size, sort);
		Page pageFromJPA = userDAO.findAll(pageable);
		return new Page4Navigator<>(pageFromJPA, navigatePages);
	}

	public void add(User user) {
		userDAO.save(user);
	}

	public void update(User user) {
		userDAO.save(user);
	}
}
