package com.example.penggajian.controllers;

import com.example.penggajian.models.Absensi;
import com.example.penggajian.repositories.AbsensiRepository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping ("/absensi")
public class AbsensiController {

    @Autowired
    private AbsensiRepository absensiRepository;

    @GetMapping
    public String listAbsensi(Model model) {
        // Mengambil semua data absensi
        List<Absensi> absensiList = absensiRepository.findAll();
        model.addAttribute("absensiList", absensiList);
        return "absensi"; // Kembali ke halaman absensi.html
    }
    
    @GetMapping("/add")
    public String showAddAbsensiForm(Model model) {
        Absensi absensi = new Absensi();
        model.addAttribute("absensi",absensi);
        return "add-absensi"; 
    }

    
    @PostMapping("/save")
    public String saveAbsensi(@RequestParam String namaKaryawan,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date tanggal,
                           @RequestParam String status) {
    Absensi absensi = new Absensi();
    absensi.setNamaKaryawan(namaKaryawan);
    absensi.setTanggal(tanggal);
    absensi.setStatus(status);

    absensiRepository.save(absensi); 
    return "redirect:/absensi";
}
}
