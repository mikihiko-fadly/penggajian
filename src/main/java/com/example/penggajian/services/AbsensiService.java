package com.example.penggajian.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.penggajian.models.Absensi;
import com.example.penggajian.repositories.AbsensiRepository;

@Service
public class AbsensiService {

    private final AbsensiRepository absensiRepository;

    @Autowired
    public AbsensiService(AbsensiRepository absensiRepository) {
        this.absensiRepository = absensiRepository;
    }

    public void saveAbsensi(Absensi absensi) {
        // Simpan absensi ke database
        absensiRepository.save(absensi);
    }

    public List<Absensi> getAllAbsensi() {
        return absensiRepository.findAll();
    }

}

