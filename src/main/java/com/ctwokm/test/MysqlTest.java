package com.ctwokm.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.ctwokm.pojo.Department;
import com.ctwokm.pojo.Role;
import com.ctwokm.pojo.User;
import com.ctwokm.repository.DepartmentRepository;
import com.ctwokm.repository.RoleRepository;
import com.ctwokm.repository.UserRepository;

import java.util.Date;
import java.util.List;

/**
 * @author Ctwokm
 *测试类
 *测试使用jpa对mysql的相关操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlTest {

	//打印日志
	private static Logger logger = LoggerFactory.getLogger(MysqlTest.class);

	//注入接口
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Before
	public void initData() {
		//先清空所有的表
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();
		
		//插入部门
		Department department = new Department();
		department.setName("开发部");
		departmentRepository.save(department);
		//断言id不为空，如果是空抛出异常
		Assert.assertNotNull(department.getId());

		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.assertNotNull(role.getId());

		User user = new User();
		//为用户添加名称
		user.setName("user");
		//为用户添加创建日期
		user.setCreateDate(new Date());
		//为用户添加所在部门
		user.setDepartment(department);
		//查询所有角色
		List<Role> roles = roleRepository.findAll();
		//断言角色不为空
		Assert.assertNotNull(roles);
		//将所有角色添加给用户
		user.setRoles(roles);
		//保存
		userRepository.save(user);
		//断言id不为空，不为空说明保存成功，否则抛出异常
		Assert.assertNotNull(user.getId());
	}

	@Test
	public void findPage() {
		Pageable pageable = PageRequest.of(0, 10, new Sort(Sort.Direction.ASC, "id"));
		Page<User> page = userRepository.findAll(pageable);
		Assert.assertNotNull(page);
		for (User user : page.getContent()) {
			logger.info("====user==== user name:{}, department name:{}, role name:{}", user.getName(),
					user.getDepartment().getName(), user.getRoles().get(0).getName());
		}
	}

	@Test
	public void testFindByName() {
		String name = "user";
		User user = userRepository.findByName(name);
		Assert.assertNotNull(user);
		logger.info(user.getName());
	}
}