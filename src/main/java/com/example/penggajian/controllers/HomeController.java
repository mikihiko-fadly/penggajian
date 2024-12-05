package com.example.penggajian.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String showHomePage(Model model) {
        
        return "home"; 
    }

    @GetMapping("/list-penggajian")
    public String penggajian() {
        return "penggajian"; // Halaman Penggajian
    }

    @GetMapping("/list-karyawan")
    public String karyawan() {
        return "karyawan"; // Halaman Karyawan
    }

    @GetMapping("/list-absensi")
    public String absensi() {
        return "absensi"; // Halaman Absensi
    }

    @GetMapping("/list-bank-karyawan")
    public String bankKaryawan() {
        return "bank-karyawan"; // Halaman Bank Karyawan
    }

    
}

