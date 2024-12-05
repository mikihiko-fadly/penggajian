package com.example.penggajian.repositories;

import com.example.penggajian.models.Penggajian;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PenggajianRepository extends JpaRepository<Penggajian, Long> {
    List<Penggajian> findByKaryawan_Id(Long karyawanId);
    @Modifying
    @Query("DELETE FROM Penggajian p WHERE p.karyawan.id = :karyawanId")
    void deleteByKaryawanId(@Param("karyawanId") Long karyawanId);
    @Query("SELECT p FROM Penggajian p WHERE " +
    "LOWER(p.karyawan.nama) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "CAST(p.gajiPokok AS string) LIKE CONCAT('%', :keyword, '%')")
    Page<Penggajian> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
