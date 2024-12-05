package com.example.penggajian.services;

// import com.example.penggajian.models.Karyawan;
import com.example.penggajian.models.Penggajian;
import com.example.penggajian.models.SlipGaji;
import com.example.penggajian.repositories.PenggajianRepository;
import com.example.penggajian.repositories.SlipGajiRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenggajianService {

    @Autowired
    private PenggajianRepository penggajianRepository;

    @Autowired
    private SlipGajiRepository slipGajiRepository;

    public Penggajian savePenggajian(Penggajian penggajian) {
        Penggajian savedPenggajian = penggajianRepository.save(penggajian);
        
        SlipGaji slipGaji = new SlipGaji();
        slipGaji.setPenggajian(savedPenggajian);
        slipGaji.setTunjangan(calculateTunjangan(savedPenggajian.getGajiPokok(), savedPenggajian.getBonus()));
        slipGaji.setPotonganPph21(calculatePph21(savedPenggajian.getGajiPokok()));
        slipGaji.setPotonganBpjs(calculateBpjs(savedPenggajian.getGajiPokok()));
        slipGaji.setTotalGajiBersih(
            calculateTotalGajiBersih(savedPenggajian.getGajiPokok(), slipGaji.getTunjangan(), slipGaji.getPotonganPph21(), slipGaji.getPotonganBpjs())
        );

        slipGajiRepository.save(slipGaji);
        return savedPenggajian;
    }

    private double calculateTunjangan(double gajiPokok, double bonus) {
        return (gajiPokok + bonus) * 0.1;
    }

    private double calculatePph21(double gajiPokok) {
        return gajiPokok * 0.05;
    }

    private double calculateBpjs(double gajiPokok) {
        return gajiPokok * 0.04;
    }

    private double calculateTotalGajiBersih(double gajiPokok, double tunjangan, double potonganPph21, double potonganBpjs) {
        return gajiPokok + tunjangan - potonganPph21 - potonganBpjs;
    }

    public List<Penggajian> getAllPenggajian() {
        return penggajianRepository.findAll();
    }

    public Penggajian getPenggajianById(Long id) {
        Optional<Penggajian> penggajian = penggajianRepository.findById(id);
        return penggajian.orElse(null); 
    }

    public void updatePenggajian(Penggajian penggajian) {
        penggajianRepository.save(penggajian); 
    }
   
}
