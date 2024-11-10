package com.cp.kku.demo.service;

import com.cp.kku.demo.model.Company;
import com.cp.kku.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Long id, Company companyDetails) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();
            existingCompany.setName(companyDetails.getName());
            existingCompany.setAddress(companyDetails.getAddress());
            existingCompany.setContact(companyDetails.getContact());
            existingCompany.setTaxId(companyDetails.getTaxId());
            existingCompany.setWebsite(companyDetails.getWebsite());
            return companyRepository.save(existingCompany);
        } else {
            return null; // หรือสามารถโยนข้อผิดพลาด
        }
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
