package tw.idv.anthony.core.app;

public class TestAppS {
	public static void main(String args[]) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		MemberDao mamberdao = applicationContext.getBean(MemberDao.class);
//		Member member = mamberdao.selectById(1);
//		System.out.print(member.getNickname());
////		使用父類別有繼承closable
//		((ConfigurableApplicationContext)applicationContext).close();
		
		
////		使用xml混用@
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		MemberService memberService = applicationContext.getBean(MemberService.class);
//		member = memberService.register(member);
//		((ConfigurableApplicationContext)applicationContext).close();
		
		
		
////		使用GenericApplicationContext IoC容器物件
//		GenericApplicationContext applicationContext = new GenericApplicationContext();
//		new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("applicationContext.xml");
//		applicationContext.refresh();
//		MemberService memberService = applicationContext.getBean(MemberService.class);
//		((ConfigurableApplicationContext)applicationContext).close();
		
////		java組態混用@
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//		MemberService memberService = applicationContext.getBean(MemberService.class);
//		member = memberService.register(member);
//		((ConfigurableApplicationContext)applicationContext).close();
	}

}
