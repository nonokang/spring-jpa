package com.spring.jpa.dao;

import com.spring.jpa.entity.People;
import com.spring.jpa.utils.Page;

public interface PeopleDao extends BaseDao<People>{

	public Page<People> queryList(Page<People> page, String searchName);
}
