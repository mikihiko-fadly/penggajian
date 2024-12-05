package com.example.penggajian.repositories;

import com.example.penggajian.models.SlipGaji;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SlipGajiRepository extends JpaRepository<SlipGaji, Long> {
    @Modifying
@Query("DELETE FROM SlipGaji s WHERE s.penggajian.karyawan.id = :karyawanId")
void deleteByPenggajianKaryawanId(@Param("karyawanId") Long karyawanId);
@Query("SELECT s FROM SlipGaji s WHERE s.penggajian.id = :penggajianId")
Optional<SlipGaji> findByPenggajianId(@Param("penggajianId") Long penggajianId);

}
