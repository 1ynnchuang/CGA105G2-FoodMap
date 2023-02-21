package tw.idv.anthony.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

//@Configuration
//@EnableWebMvc
//@ComponentScan({ "tw.idv.anthony.web.*.controller", "tw.idv.anthony.core.exception" })
public class MvcConfig implements WebMvcConfigurer {

// xml的前綴與後綴
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setContentType("text/html;charset=UTF-8");
//		registry.viewResolver(viewResolver);
//	}


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index.html");
	}

//	管理靜態資源
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	外界路徑隱藏
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/");
	}

//	檔案上傳託管CommonsMultipartResolver物件
	@Bean
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8"); // 設定檔名文字編碼
		resolver.setMaxUploadSizePerFile(500 * 1024 * 1024); // 設定單檔大小上限
		resolver.setMaxUploadSize(4 * 1024 * 1024 * 1024); // 設定總大小上限
		return resolver;
	}

//	託管/註冊MappingJackson2HttpMessageConverter
//	JSON字串<--JAVA物件 序列化(相反則為反序列化)
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setPrettyPrint(true);
		converters.add(messageConverter);
	}
}
