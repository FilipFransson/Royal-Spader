package se.royalspades.service;

import java.util.List;

import se.royalspades.model.Company;

public interface CompanyService {
	public void add(Company company);
	public void edit(Company company);
	public void delete(int companyId);
	public Company getCompany(int companyId);
	@SuppressWarnings("rawtypes")
	public List getAllCompanies();
}
