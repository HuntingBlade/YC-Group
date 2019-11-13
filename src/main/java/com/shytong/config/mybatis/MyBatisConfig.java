package com.shytong.config.mybatis;

import com.github.pagehelper.PageInterceptor;
import com.shytong.core.database.BaseDao;
import com.shytong.core.database.BaseDaoImpl;
import com.shytong.core.database.plugins.PagePlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * MyBatis基础配置
 *
 * @author liuzh
 * @since 2015-12-19 10:11
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;
    @Autowired
    @Qualifier("pageInterceptor")
    PageInterceptor pageInterceptor;

    @Bean(name = "mybatisConfiguration")
    public   org.apache.ibatis.session.Configuration mybatisConfiguration(){
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//configuration.setLogImpl(Log);
        return configuration;
    }


    @Bean(name = "reflectorFactory")
    public ReflectorFactory reflectorFactory(org.apache.ibatis.session.Configuration configuration ){

        return configuration.getReflectorFactory();
    }

    @Bean
     public PageInterceptor pageInterceptor(){
        PageInterceptor pageHelper = new PageInterceptor();

             Properties properties = new Properties();
            properties.setProperty("offsetAsPageNum","true");
            properties.setProperty("rowBoundsWithCount","true");
//           properties.setProperty("PageRowBounds","true");
             properties.setProperty("helperDialect","mysql");
        properties.setProperty("supportMethodsArguments","true");
           pageHelper.setProperties(properties);
            return pageHelper;
         }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(PageInterceptor pageInterceptor) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //      bean.setTypeAliasesPackage("tk.mybatis.springboot.model");

//        //分页插件
        PagePlugin pagePlugin = new PagePlugin();
       Properties properties = new Properties();
        properties.setProperty("dbType", "mysql");
       properties.setProperty("printSql", "true");
        pagePlugin.setProperties(properties);

//        pageInterceptor.setProperties(properties);
        //添加插件
       bean.setPlugins(new Interceptor[]{pagePlugin,pageInterceptor});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //bean.setConfiguration();

//            bean.setConfigurationProperties();
            bean.setMapperLocations(resolver.getResources("classpath:com/shytong/**/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sqlMapClientTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseDao")
    public BaseDao baseDao(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate, @Qualifier("sqlMapClientTemplate") SqlSessionTemplate sqlSessionTemplate) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        BaseDaoImpl baseDao = new BaseDaoImpl();
//        baseDao.setJdbcTemplate(jdbcTemplate);
        baseDao.setSqlSessionTemplate(sqlSessionTemplate);
//        baseDao.setDbcode("false");
//        baseDao.setDbtype("mysql");
        return baseDao;
   }
}