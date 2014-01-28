package se.royalspades.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.royalspades.model.Company;
import se.royalspades.service.CompanyService;

@Controller
@RequestMapping(value ="/api/company")
public class CompanyController {
	@Autowired CompanyService companyService; 
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Company> getAllCompaniesResponse(){
		return companyService.getAllCompanies();
	}
}


/*     <bean id="txManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager">
        <property name="sessionFactory" ref="sessionFactory" />
    </bean>      (hibernate.cfg.xml mellan 2 sista bean*/