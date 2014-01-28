package se.royalspades.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.royalspades.dao.CompanyDAO;
import se.royalspades.model.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
	
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public void add(Company company) {
		getCurrentSession().save(company);
	}

	@Override
	public void edit(Company company) {
		getCurrentSession().update(company);
	}

	@Override
	public void delete(int companyId) {
		getCurrentSession().delete(getCompany(companyId));
	}

	@Override
	public Company getCompany(int companyId) {
		return (Company)getCurrentSession().get(Company.class, companyId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllCompanies() {
		return getCurrentSession().createCriteria(Company.class).list();
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
