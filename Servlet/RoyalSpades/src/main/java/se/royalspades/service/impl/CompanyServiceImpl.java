package se.royalspades.service.impl;

import java.util.List;

//import javax.transaction.Transactional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.royalspades.dao.CompanyDAO;
import se.royalspades.model.Company;
import se.royalspades.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDao;
	
	@Transactional
	public void add(Company company) {
		companyDao.add(company);
	}

	@Transactional
	public void edit(Company company) {
		companyDao.edit(company);
	}

	@Transactional
	public void delete(int companyId) {
		companyDao.delete(companyId);
	}

	@Transactional
	public Company getCompany(int companyId) {
		return companyDao.getCompany(companyId);
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public List getAllCompanies() {
		return companyDao.getAllCompanies();
	}

}
