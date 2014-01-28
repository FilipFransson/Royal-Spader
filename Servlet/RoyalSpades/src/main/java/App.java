
public class App {
	
	 public static void main( String[] args ){
		 
		 // http://software.aurorasolutions.org/how-to-oauth-2-0-with-spring-security-2/
		 // http://www.javacodegeeks.com/2012/02/oauth-with-spring-security.html		 
		 // http://keenformatics.blogspot.it/2013/08/how-to-solve-json-infinite-recursion.html
		 
		 // http://www.youtube.com/watch?v=SC0FPuDKei0
		 
		 // http://www.youtube.com/watch?v=8uBcpsIEz2I
		 
		 // http://stackoverflow.com/questions/10826293/restful-authentication-via-spring
		 
		 // http://www.baeldung.com/hibernate-4-spring
		 
		 // http://stackoverflow.com/questions/7774295/spring-xml-file-configuration-hierarchy-help-explanation/7774597#7774597
		 
		 // http://stackoverflow.com/questions/9408721/beancreationexception-in-spring-autowiring
		 
		 // http://www.baeldung.com/spring-nosuchbeandefinitionexception
	
		//Session session = HibernateUtil.getSessionFactory().openSession();  <- don't need that??
	
	    //session.beginTransaction();
	    //Student student = new Student();
	
	    //student.setStudentName("Jeremy");
	    //student.setStudentAge("45");
	
	    //Project project1 = new Project("Secure Systems","Spring");
	    //project1.setStudent(student);
	
	    //student.getStudentProjects().add(project1);
	
	    //Project project2 = new Project("Databases","Spring");
	    //project2.setStudent(student);
	
	   //student.getStudentProjects().add(project2);
	
	    //session.save(student);
	    //session.save(project1);
	    //session.save(project2);
	
	   // session.getTransaction().commit();
	    //System.out.println("Great! Student was saved");
		 
		 
		 
		 
		 /*   
		 	
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="configLocation">
			<value>classpath:hibernate.cfg.xml</value>
		</property>
		<property name="configurationClass">
			<value>org.hibernate.cfg.AnnotationConfiguration</value>
		</property>
	</bean>
		<bean id="transactionManager"
		class="org.springframework.orm.hibernate3.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>	
		 */
	 }
	
}

// http://docs.jboss.org/hibernate/annotations/3.5/reference/en/html_single/

//TODO http://www.cavalr.com/blog/Spring_MVC_-_Spring_Security_with_AJAX_and_JSON

// OBS The persistence files was in a package for them self and model, etc was in another one?

// suppression for rawtypes?? @Transactional needed??

// http://www.mkyong.com/hibernate/hibernate-many-to-many-example-join-table-extra-column-annotation/

// should interface be used? dao-class=

// http://www.techzoo.org/tutorials/java-tutorials/springmvc-hibernate-crud-tutorial-using-eclipse.html


/*  webSecurityConfig.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:beans="http://www.springframework.org/schema/beans"
        xsi:schemaLocation="
                http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-3.2.xsd
                http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">

        <http access-denied-page="/access-denied.html" use-expressions="true">
                <intercept-url pattern="/access-denied*" access="hasAnyRole('ROLE_LOCATION_WRITE','ROLE_POLYGON_WRITE')" />
                <intercept-url pattern="/admin/**" access="hasAnyRole('ROLE_ADMIN')" />
                <intercept-url pattern="/organization/**" access="hasAnyRole('ROLE_ORGANIZATION')" />
                <intercept-url pattern="/location/edit*" access="hasAnyRole('ROLE_LOCATION_WRITE')" />
                <intercept-url pattern="/location/view*" access="permitAll" />

                <intercept-url pattern="/login*" access="isAnonymous()" />
                <intercept-url pattern="/register*" access="isAnonymous()" />
                <intercept-url pattern="/login-denied/**" access="isAnonymous()" />

                <intercept-url pattern="/**" access="permitAll" />

                <form-login login-page='/login.html' default-target-url="/" always-use-default-target="false" authentication-failure-url="/login.html?error=true" />

                <logout />

                <anonymous />

                <session-management invalid-session-url="/">
                        <concurrency-control max-sessions="1" />
                </session-management>
        </http>

        <authentication-manager alias="authenticationManager" erase-credentials="false">
                <authentication-provider ref="restAuthenticationProvider" />
        </authentication-manager>

</beans:beans>
	}
	
	

	private static List list() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List employees = session.createQuery("from Employee").list();
		session.close();
		return employees;
	}
	private static Employee read(Long id) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		Employee employee = (Employee) session.get(Employee.class, id);
		session.close();
		return employee;
	}
	private static Employee save(Employee employee) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		Long id = (Long) session.save(employee);
		employee.setId(id);
		
		session.getTransaction().commit();
		
		session.close();

		return employee;
	}

	private static Employee update(Employee employee) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		session.beginTransaction();

		session.merge(employee);
		
		session.getTransaction().commit();
		
		session.close();
		return employee;

	}

	private static void delete(Employee employee) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		session.delete(employee);
		
		session.getTransaction().commit();
		
		session.close();
	} */
