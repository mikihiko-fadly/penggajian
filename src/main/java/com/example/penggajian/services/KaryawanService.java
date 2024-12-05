package com.example.penggajian.services;

import com.example.penggajian.models.Karyawan;
import com.example.penggajian.repositories.KaryawanRepository;
import com.example.penggajian.repositories.PenggajianRepository;
import com.example.penggajian.repositories.SlipGajiRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KaryawanService {

    @Autowired
    private KaryawanRepository karyawanRepository;
    @Autowired
    private PenggajianRepository penggajianRepository;
    @Autowired 
    private SlipGajiRepository slipGajiRepository;

    public List<Karyawan> getAllKaryawan() {
        return karyawanRepository.findAll();
    }

    public Karyawan getKaryawanById(Long id) {
        return karyawanRepository.findById(id).orElse(null);
    }

    public Karyawan saveKaryawan(Karyawan karyawan) {
        return karyawanRepository.save(karyawan);
    }

    public Karyawan updateKaryawan(Karyawan karyawan) {
        return karyawanRepository.save(karyawan);
    }

    // public void deleteKaryawan(Long id) {
    //     Karyawan karyawan = karyawanRepository.getReferenceById(id);
    //    karyawanRepository.delete(karyawan);
    // }

    @Transactional
    public void deleteKaryawan(Long id) {
    // Hapus SlipGaji yang terkait
    slipGajiRepository.deleteByPenggajianKaryawanId(id);

    // Hapus Penggajian yang terkait
    penggajianRepository.deleteByKaryawanId(id);

    // Hapus Karyawan
    karyawanRepository.deleteById(id);
}
}
