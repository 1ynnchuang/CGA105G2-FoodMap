package tw.idv.anthony.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;

import static tw.idv.anthony.core.util.Constants.GSON;
import static tw.idv.anthony.core.util.Constants.JSON_MIME_TYPE;

public class CommonUtil {
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);

	public static <T> T getBean(ServletContext sc, Class<T> clazz) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
		return context.getBean(clazz);
	}

//	檢驗用,叫出所有Bean
	public static void printBean(ServletContext sc) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
	}

	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		response.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
