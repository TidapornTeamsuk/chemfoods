package com.cp.kku.demo.repository;

import com.cp.kku.demo.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    // สามารถเพิ่ม Query Methods ตามที่ต้องการได้ เช่น findByName(String name)
}
