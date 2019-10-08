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
		userRepository.deleteAll();
		roleRepository.deleteAll();
		departmentRepository.deleteAll();

		Department department = new Department();
		department.setName("开发部");
		departmentRepository.save(department);
		Assert.assertNotNull(department.getId());

		Role role = new Role();
		role.setName("admin");
		roleRepository.save(role);
		Assert.assertNotNull(role.getId());

		User user = new User();
		user.setName("user");
		user.setCreateDate(new Date());
		user.setDepartment(department);
		List<Role> roles = roleRepository.findAll();
		Assert.assertNotNull(roles);
		user.setRoles(roles);
		userRepository.save(user);
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