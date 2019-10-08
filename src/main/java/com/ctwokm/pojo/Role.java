package com.ctwokm.pojo;
import javax.persistence.*;
import java.io.Serializable;
 
/**
 * @author Ctwokm
 *创建role角色类的实体类
 *这个类和用户类是多对多的关系
 *一个用户可以拥有多个角色，一个角色可以有多个用户
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
}

