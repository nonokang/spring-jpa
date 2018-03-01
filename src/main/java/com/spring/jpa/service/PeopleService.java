package com.spring.jpa.service;

import com.spring.jpa.entity.People;
import com.spring.jpa.utils.Page;

public interface PeopleService {

	public People findById(Integer id);
	
	public Page<People> pageList(Page<People> page, String searchName);
	
	public void save(People bean);
	
	public void update(People bean);
	
	public void opera(String ids, String type);
	
}
