import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import se.royalspades.model.Brand;
import se.royalspades.model.Category;
import se.royalspades.model.GroceryListProduct;
import se.royalspades.model.Product;
import se.royalspades.model.StoreProduct;
import se.royalspades.model.User;
import se.royalspades.service.UserService;


public class App {
	
	 public static void main( String[] args ){
		 	
		 
		 
		 
		 // http://beansgocrazy.blogspot.se/2011/07/custom-authentication-with-spring.html
		 
		 // http://forum.spring.io/forum/spring-projects/security/115172-md5-hash-on-database-digest-authentication
		 
		 // The service and DAO needed for GroceryListProduct?? how to set the volume value? straight on model?
		 
		 //https://github.com/Fruzenshtein/security-spr/tree/master/src/main/webapp/WEB-INF/pages
//https://github.com/fgakk/samples/tree/master/java/spring/restful-token-login/src/main/java/com/fga/examples/restful_token_login/service		 
		 /*
		 // anvander sessions nu och ar inte stateless??
		  * 
		  * http://www.baeldung.com/2011/11/20/basic-and-digest-authentication-for-a-restful-service-with-spring-security-3-1/
		  * https://github.com/eugenp/REST#readme
		  * http://www.javacodegeeks.com/2012/12/authentication-against-a-restful-service-with-spring-security.html
		  * http://security.stackexchange.com/questions/19620/securing-a-javascript-single-page-app-with-restful-backend

   <http auto-config="false" create-session="stateless">
      <http-basic entry-point-ref="authenticationEntryPoint" />
   </http>

   <beans:bean id="authenticationEntryPoint"
     class="com.blogspot.vozis.security.VozisBasicAuthenticationEntryPoint">
      <beans:property name="realmName" value="Vozis" />
   </beans:bean>

   <authentication-manager alias="authenticationManager">
      <authentication-provider user-service-ref="userService" />
   </authentication-manager>

   <beans:bean id="userService" class="com.blogspot.vozis.security.UserServiceImpl" />
</beans:beans>   

		 */
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
