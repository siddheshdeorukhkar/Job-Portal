package com.example.jobPortal.jobApp.Company.Impl;

import com.example.jobPortal.jobApp.Company.Company;
import com.example.jobPortal.jobApp.Company.CompanyRepository;
import com.example.jobPortal.jobApp.Company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    @Override
    public void saveCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company>companyOptional= companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company= companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
