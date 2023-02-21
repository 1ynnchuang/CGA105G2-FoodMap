package tw.idv.anthony.core.config;

import org.hibernate.SessionFactory;
import org.hibernate.dialect.MySQL8Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;

//
//@Configuration
////可以取代傳統的XML配置
//@ComponentScan("tw.idv.anthony.web.*.*.impl")
////掃描指定的Bean元件
//@EnableTransactionManagement
////交易控制
//@EnableAspectJAutoProxy
public class AppConfig {

	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setResourceRef(true);
		bean.setJndiName("jdbc/javaFramework");
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}

	
//	註冊實體類別
	@Bean
	public SessionFactory sessionFactory() throws IllegalArgumentException, NamingException {
		return new LocalSessionFactoryBuilder(dataSource()).scanPackages("web.*.entity")
				.addProperties(getHibernateProperties()).buildSessionFactory();
	}


	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", MySQL8Dialect.class.getName());
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("hibernate.format_sql", "false");
		properties.setProperty("hibernate.current_session_context_class", SpringSessionContext.class.getName());
		return properties;
	}

//	交易控制用
	@Bean
	public TransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		return new HibernateTransactionManager(sessionFactory());
	}
}
