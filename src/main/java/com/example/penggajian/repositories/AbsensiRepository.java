package com.example.penggajian.repositories;

import com.example.penggajian.models.Absensi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface AbsensiRepository extends JpaRepository<Absensi, Long> {
    // Anda bisa menambahkan query kustom jika diperlukan
}

