# SpringBootJPA
JPA在SpringBoot框架下的应用

## 关于源码

这是一个完整的maven项目，包括数据库（修改配置文件为自己的数据库信息即可），下载整包即可运行，jpa的配置信息以及每个类的作用在源码中都有注解

其中提供了简单的jpa应用，具体业务上需要的复杂操作只需要自定义方法即可

springboot省去了SSM（H）的复杂配置，而且内置了tomcat。直接启动即可使用，无需做过多累赘的配置方便使用开发

后期使用springboot做微服务也是非常方便快捷的

## 自定义查询

对于JpaRepository的继承为接口提供了一系列的数据库基本操作，当然这些无法满足显示业务的需求，那么就需要使用自定义方法操作常见自定义查询片段如下：

And					findByLastnameAndFirstname								… where x.lastname = ?1 and x.firstname = ?2

Or					findByLastnameOrFirstname								… where x.lastname = ?1 or x.firstname = ?2

Is,Equals			findByFirstname,findByFirstnameIs,findByFirstnameEquals	… where x.firstname = ?1

Between				findByStartDateBetween									… where x.startDate between ?1 and ?2

LessThan			findByAgeLessThan										… where x.age < ?1

LessThanEqual		findByAgeLessThanEqual									… where x.age <= ?1

GreaterThan			findByAgeGreaterThan									… where x.age > ?1

GreaterThanEqual	findByAgeGreaterThanEqual								… where x.age >= ?1

After				findByStartDateAfter									… where x.startDate > ?1

Before				findByStartDateBefore									… where x.startDate < ?1

IsNull				findByAgeIsNull											… where x.age is null

IsNotNull,NotNull	findByAge(Is)NotNull									… where x.age not null

Like				findByFirstnameLike										… where x.firstname like ?1

NotLike				findByFirstnameNotLike									… findByFirstnameNotLike

StartingWith		findByFirstnameStartingWith								… where x.firstname like ?1 (parameter bound with appended %)

EndingWith			findByFirstnameEndingWith								… where x.firstname like ?1 (parameter bound with prepended %)

Containing			findByFirstnameContaining								… where x.firstname like ?1 (parameter bound wrapped in %)

OrderBy				findByAgeOrderByLastnameDesc							… where x.age = ?1 order by x.lastname desc

Not					findByLastnameNot										… where x.lastname <> ?1

In					findByAgeIn(Collection<Age> ages)						… where x.age in ?1

NotIn				findByAgeNotIn(Collection<Age> ages)					… where x.age not in ?1

True				findByActiveTrue()										… where x.active = true

False				findByActiveFalse()										… where x.active = false

IgnoreCase			findByFirstnameIgnoreCase								… where UPPER(x.firstame) = UPPER(?1)



## 查询结果限制语句

first、top以及Distinct 关键字
 
User findFirstByUsernameOrderByUsernameAsc(String username);

List<User> findTop10ByUsername(String username, Sort sort);
    
List<User> findTop10ByUsername(String username, Pageable pageable);

## 自定义查询语句Using @Query

@Query 注解的使用非常简单，只需在声明的方法上面标注该注解，同时提供一个 JPQL 查询语句即可

@Query("select u from User u where u.email = ?1")
User getByEmail(String eamil);

@Query("select u from User u where u.username = ?1 and u.password = ?2")
User getByUsernameAndPassword(String username, String password);

@Query("select u from User u where u.username like %?1%")
List<User> getByUsernameLike(String username);
