package com.spring.jpa.dao.impl;

import org.springframework.stereotype.Repository;

import com.spring.jpa.dao.PeopleDao;
import com.spring.jpa.entity.People;
import com.spring.jpa.utils.Page;

@Repository(value="peopleDao")
public class PeopleDaoImpl extends BaseDaoImpl<People> implements PeopleDao{

	@Override
	public Class<People> getEntityClass() {
		return People.class;
	}

	@Override
	public Page<People> queryList(Page<People> page, String searchName) {
		StringBuilder sb = new StringBuilder();
		sb.append("from People where opera<>'-1'");
		if(null != searchName && !"".equals(searchName.trim())){
			sb.append(" and name like ?");
			page = pageList(sb.toString(), page, "%"+searchName+"%");
		}else{
			page =pageList(sb.toString(), page);
		}
		return page;
	}

}
