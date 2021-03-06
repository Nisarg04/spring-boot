package com.accenture.lkm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.entity.EmployeeEntityBean;
import com.accenture.lkm.business.bean.Employee;

@Repository
public class EmployeeDAOWrapper {
	
	@Autowired
	public EmployeeDAO dao;

	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<>();
		
		Iterable<EmployeeEntityBean> listEn = dao.findAll();
		listEn.forEach(i -> {
			Employee e = new Employee();
			BeanUtils.copyProperties(i, e);
			list.add(e);
		});
		return list;
	}

	public Integer addEmployee(Employee emp) {
		EmployeeEntityBean empEntity = new EmployeeEntityBean();
		BeanUtils.copyProperties(emp, empEntity);
		try {
			dao.save(empEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empEntity.getId();
	}
	

}
