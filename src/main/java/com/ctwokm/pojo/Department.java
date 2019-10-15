package com.ctwokm.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ctwokm
 *创建一个department部门类的实体类
 *这个实体类与用户（员工）属于一对多的关系
 *一个部门可以拥有多个用户（员工）
 *
 */


/*
 * @Entity 标注用于实体类声明语句之前，指出该Java 类为实体类，将映射到指定的数据库表。
 * 如声明一个实体类 Customer，它将映射到数据库中的 customer 表上
 */
@Entity
/*
 * @Table
 * 当实体类与其映射的数据库表名不同名时需要使用 @Table 标注说明，
 * 该标注与 @Entity 标注并列使用，置于实体类声明语句之前，
 * 可写于单独语句行，也可与声明语句同行。
 * 
 * @Table 标注的常用选项是 name，用于指明数据库的表名 
 * @Table 标注还有一个两个选项 catalog 和 schema 用于设置表所属的数据库目录或模式，通常为数据库名。
 */
@Table(name = "department")
public class Department implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * @Id 标注用于声明一个实体类的属性映射为数据库的主键列。
	 * 该属性通常置于属性声明语句之前，可与声明语句同行，也可写在单独行上。
	 * @Id标注也可置于属性的getter方法之前。
	 */
	/*
	 * @GeneratedValue 用于标注主键的生成策略，通过 strategy 属性指定。
	 * 默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：
	 * SqlServer 对应 identity，MySQL 对应 auto increment。 
	 * 在 javax.persistence.GenerationType 中定义了以下几种可供选择的策略：
	 * IDENTITY：采用数据库 ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
	 * AUTO： JPA自动选择合适的策略，是默认选项；
	 * SEQUENCE：通过序列产生主键，通过 @SequenceGenerator 注解指定序列名，MySql 不支持这种方式
	 * TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
	 */
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