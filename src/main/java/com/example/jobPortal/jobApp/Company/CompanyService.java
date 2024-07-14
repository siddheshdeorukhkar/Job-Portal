package com.example.jobPortal.jobApp.Company;

import java.util.List;
public interface CompanyService {
    List<Company>findAll();

    void saveCompany(Company company);

    boolean updateCompany(Long id, Company updatedCompany);

    boolean deleteCompanyById(Long id);
}
