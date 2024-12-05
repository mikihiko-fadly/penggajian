package com.example.penggajian.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Halaman login (form login)
    @GetMapping("/")
    public String showLoginPage() {
        return "login";  // Menampilkan form login yang hanya meminta kode akses
    }

    // Menangani login dan validasi kode
    @PostMapping("/login")
    public String handleLogin(@RequestParam String kode, Model model) {
        String kodeValid = "1234"; // Kode akses yang valid

        // Validasi kode akses
        if (kode.equals(kodeValid)) {
            return "redirect:/home"; // Jika kode benar, arahkan ke halaman home
        } else {
            model.addAttribute("error", "Kode akses salah!");
            return "login";  // Jika kode salah, tampilkan kembali form login dengan error
        }
    }

    // Halaman home setelah login berhasil
    // @GetMapping("/home")
    // public String homePage() {
    //     return "home";  // Halaman yang muncul setelah login berhasil
    // }
}
