package com.example.jobPortal.jobApp.Company;

import com.example.jobPortal.jobApp.Job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public List<Company>findAll(){
        return companyService.findAll();
    }
//    @PostMapping
//    public String saveCompany(Company company){
//        companyService.saveCompany(company);
//        return "company created";
//    }
    @PostMapping
    public ResponseEntity<String>createCompany(@RequestBody Company company){
        companyService.saveCompany(company);
        return new ResponseEntity<>("Company Added Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateCompanyById(@PathVariable Long id,
                                                   @RequestBody Company updatedCompany){
        boolean updated= companyService.updateCompany(id, updatedCompany);
        if(updated){
            return new ResponseEntity<>("company updated", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Company with this id not found", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCompany(@PathVariable Long id){
        boolean deleted= companyService.deleteCompanyById(id);
        if(deleted){
            return new ResponseEntity<>("company deleted with id "+ id, HttpStatus.OK);
        }
        return new ResponseEntity<>("No company found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long id){
        Company company= companyService.findCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
