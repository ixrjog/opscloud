package com.baiyi.opscloud.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author feixue
 */
@Configuration
@MapperScan(
        basePackages  = {"com.baiyi.opscloud.mapper.opscloud"},
        sqlSessionTemplateRef = "opscloudSqlSessionTemplate"
)
public class DatasourceOpscloudConfig {

    @Bean
    @Primary
    public SqlSessionTemplate opscloudSqlSessionTemplate(SqlSessionFactory opscloudDataSourceSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(opscloudDataSourceSqlSessionFactory);
    }

    @Bean
    @Primary
    public DataSourceTransactionManager opscloudTransactionManager(DataSource opscloudDataSource) {
        return new DataSourceTransactionManager(opscloudDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory opscloudDataSourceSqlSessionFactory(DataSource opscloudDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(opscloudDataSource);
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/opscloud/*.xml")); // 2. xml 所在路径
        return factoryBean.getObject();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.opscloud")
    public DataSourceProperties opscloudDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.opscloud.configuration")
    public DataSource opscloudDataSource(DataSourceProperties opscloudDataSourceProperties) {
        return opscloudDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class) // 3. 可以显示指定连接池，也可以不显示指定；即此行代码可以注释掉
                .build();
    }

}
