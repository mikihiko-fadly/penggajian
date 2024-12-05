package com.example.penggajian.repositories;

import com.example.penggajian.models.Karyawan;


import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// @Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {
     @Query("SELECT k FROM Karyawan k WHERE " +
           "LOWER(k.nama) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(k.nik) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        Page<Karyawan> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

      
}

