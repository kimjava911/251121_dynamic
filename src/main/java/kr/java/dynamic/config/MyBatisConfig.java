package kr.java.dynamic.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("kr.java.dynamic.model.mapper") // Mapper Interface
public class MyBatisConfig {

    @Bean
    public DataSource dataSource() {
        System.out.println("데이터소스 & 커넥션 풀 시작");
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(dotenv.get("DB_URL"));
        ds.setUsername(dotenv.get("DB_USERNAME"));
        ds.setPassword(dotenv.get("DB_PASSWORD"));

        // 커넥션 풀
        ds.setInitialSize(5);
        ds.setMaxTotal(20);
        System.out.println("데이터소스 & 커넥션 풀 완료");
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("SQL 세션 만들기 시작");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        // 로깅
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setLogImpl(StdOutImpl.class);   // 여기서 STDOUT 로깅 강제
        bean.setConfiguration(config);

        // Mapper XML
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/*.xml")
        );
        System.out.println("SQL 세션 만들기 완료");
        return bean.getObject();
    }
}