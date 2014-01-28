package se.royalspades.dao;

import java.util.List;

import se.royalspades.model.Company;

public interface CompanyDAO {
	public void add(Company company);
	public void edit(Company company);
	public void delete(int companyId);
	public Company getCompany(int companyId);
	@SuppressWarnings("rawtypes")
	public List getAllCompanies();
}
