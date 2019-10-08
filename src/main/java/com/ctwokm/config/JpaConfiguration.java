package com.ctwokm.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
/**
 * @author Ctwokm
 *jpa的配置类
 */
@Order(Ordered.HIGHEST_PRECEDENCE)//定义组件的加载顺序，这里为最高级
@Configuration//表明这是一个配置类
@EnableTransactionManagement(proxyTargetClass = true)//启用JPA的事物管理
@EnableJpaRepositories(basePackages = "com.ctwokm.repository")//启动JPA资源库并设置接口资源库的位置
@EntityScan(basePackages = "com.ctwokm.pojo")//实体类位置
public class JpaConfiguration {
 
    /**
     * @Description: 这里说明为什么要声明一个PersistenceExceptionTranslationPostProcessor 的Bean对象，引用Spring官方文档的一句话：
     * (1)scanned by Spring component-scanning
     * (2)catch platformspecific exceptions and rethrow them as one of Spring’s unified unchecked exceptions
            But if you’re using Hibernate contextual sessions and not a Hibernate template,how can the exception
            translation take place?
          翻译过来就是：@Repository有两作用：
          (1):用于被容器扫描：
          (2):捕获平台特定的异常并将它们重新抛出，作为Spring的一个未检查的异常。(用于事务的管理，例如捕获异常回滚)
          但是，如果您使用的是Hibernate contextual sessions上下文会话而不是Hibernate template，那么异常转换是如何发生的呢?
          那么，这就是配置这个类的作用。
     * @return
     */
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}