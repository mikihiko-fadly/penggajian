package com.example.penggajian.controllers;

import com.example.penggajian.models.BankKaryawan;
import com.example.penggajian.services.BankKaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bank-karyawan")
public class BankKaryawanController {

    @Autowired
    private BankKaryawanService bankKaryawanService;

    // Menampilkan semua data BankKaryawan
    @GetMapping
    public String listBankKaryawan(Model model) {
        model.addAttribute("bankKaryawanList", bankKaryawanService.getAllBankKaryawan());
        return "bank-karyawan";
    }

    // Menampilkan form tambah BankKaryawan
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("bankKaryawan", new BankKaryawan());
        return "add-bank-karyawan";
    }

    // Menyimpan data BankKaryawan
    @PostMapping("/save")
    public String saveBankKaryawan(@ModelAttribute BankKaryawan bankKaryawan) {
        bankKaryawanService.saveBankKaryawan(bankKaryawan);
        return "redirect:/bank-karyawan";
    }

    // Menampilkan form edit BankKaryawan
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BankKaryawan bankKaryawan = bankKaryawanService.getBankKaryawanById(id);
        model.addAttribute("bankKaryawan", bankKaryawan);
        return "update-bank-karyawan";
    }

    // Mengupdate data BankKaryawan
    @PostMapping("/update/{id}")
    public String updateBankKaryawan(@PathVariable Long id, @ModelAttribute BankKaryawan bankKaryawan) {
        bankKaryawan.setId(id);
        bankKaryawanService.saveBankKaryawan(bankKaryawan);
        return "redirect:/bank-karyawan";
    }

    // Menghapus data BankKaryawan
    @GetMapping("/delete/{id}")
    public String deleteBankKaryawan(@PathVariable Long id) {
        bankKaryawanService.deleteBankKaryawan(id);
        return "redirect:/bank-karyawan";
    }
}
