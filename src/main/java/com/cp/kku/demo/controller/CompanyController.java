package com.cp.kku.demo.controller;

import com.cp.kku.demo.model.Company;
import com.cp.kku.demo.model.Purchase;
import com.cp.kku.demo.service.CompanyService;
import com.cp.kku.demo.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PurchaseService purchaseService;

    // แสดงหน้ารายการบริษัททั้งหมด
    @GetMapping
    public String listCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "listCompany";
    }

    // แสดงรายละเอียดบริษัท
    @GetMapping("/show/{id}")
    public String showCompanyDetails(@PathVariable("id") Long id, Model model) {
        // ดึงข้อมูลบริษัท
        Company company = companyService.getCompanyById(id).orElse(null);

        // ดึงประวัติการซื้อจากฐานข้อมูล
        List<Purchase> purchases = purchaseService.getPurchasesByCompanyId(id);

        if (company != null) {
            model.addAttribute("company", company);
            model.addAttribute("purchases", purchases);  // ส่งข้อมูลประวัติการซื้อ
            return "showCompany";  // ส่งไปยังหน้า showCompany.html
        } else {
            return "redirect:/companies";  // หากไม่พบบริษัท ให้กลับไปที่รายการบริษัท
        }
    }


    // แสดงหน้าเพิ่มบริษัทใหม่
    @GetMapping("/new")
    public String showAddCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }

    // บันทึกข้อมูลบริษัทใหม่
    @PostMapping("/save")
    public String saveCompany(Company company) {
        companyService.createCompany(company);
        return "redirect:/companies";
    }

    // แสดงหน้าแก้ไขข้อมูลบริษัท
    @GetMapping("/edit/{id}")
    public String showEditCompanyForm(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id).orElse(null);
        if (company != null) {
            model.addAttribute("company", company);
            return "editCompany";
        } else {
            return "redirect:/companies";
        }
    }

    // บันทึกการแก้ไขข้อมูลบริษัท
    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable("id") Long id, Company companyDetails) {
        companyService.updateCompany(id, companyDetails);
        return "redirect:/companies";
    }

    // ลบบริษัท
    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }
}
