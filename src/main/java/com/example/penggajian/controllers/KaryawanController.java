package com.example.penggajian.controllers;

import com.example.penggajian.models.Karyawan;
import com.example.penggajian.repositories.KaryawanRepository;
// import com.example.penggajian.repositories.KaryawanRepository;
import com.example.penggajian.services.KaryawanService;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Controller
@RequestMapping("/karyawan")
public class KaryawanController {
    
    @Autowired
    private KaryawanService karyawanService;
    @Autowired
    private KaryawanRepository karyawanRepository;
  
    @GetMapping
    public String listKaryawan(
        @RequestParam(required = false, defaultValue = "") String keyword,
        @RequestParam(defaultValue = "nama") String sort,
        @RequestParam(defaultValue = "asc") String direction,
        @PageableDefault Pageable pageable,
        Model model) {

    Sort sortOrder = direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);

    Page<Karyawan> karyawanPage = karyawanRepository.searchByKeyword(keyword, sortedPageable);

    model.addAttribute("karyawanList", karyawanPage.getContent());
    model.addAttribute("currentPage", karyawanPage.getNumber());
    model.addAttribute("totalPages", karyawanPage.getTotalPages());
    model.addAttribute("keyword", keyword);
    model.addAttribute("sort", sort);
    model.addAttribute("direction", direction);

    return "karyawan";
}

    
    
    @GetMapping("/add")
    public String showAddKaryawanForm(Model model) {
        Karyawan karyawan = new Karyawan();
        model.addAttribute("karyawan", karyawan);
        return "add-karyawan"; 
    }
    

    
    @PostMapping("/add")
    public String addKaryawan(@ModelAttribute Karyawan karyawan) {
        karyawanService.saveKaryawan(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/update/{id}")
    public String updateKaryawanForm(@PathVariable Long id, Model model) {
        Karyawan karyawan = karyawanService.getKaryawanById(id);
        model.addAttribute("karyawan", karyawan);
        return "update-karyawan";
    }

    @PostMapping("/update/{id}")
    public String updateKaryawan(@PathVariable Long id, @ModelAttribute Karyawan karyawan) {
        karyawan.setId(id);
        karyawanService.updateKaryawan(karyawan);
        return "redirect:/karyawan";
    }

    @GetMapping("/delete/{id}")
    public String deleteKaryawan(@PathVariable Long id, Model model) {
        try {
            karyawanService.deleteKaryawan(id);
            return "redirect:/karyawan";
        } catch (Exception e) {
            model.addAttribute("error", "Gagal menghapus karyawan: " + e.getMessage());
            return "karyawan"; // Kembali ke halaman dengan pesan error
        }
    }
    


   
}


