package tw.idv.anthony.core.config;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class ,WebSocketConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
//	filter
	@Override
	protected Filter[] getServletFilters() {
	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
	characterEncodingFilter.setEncoding("UTF-8");
	OpenSessionInViewFilter openSessionInViewFilter = new OpenSessionInViewFilter();
	return new Filter[] { characterEncodingFilter, openSessionInViewFilter };
	}
	
//	檔案上傳
	@Override
	protected void customizeRegistration(Dynamic registration) {
	registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}
