package com.example.penggajian.controllers;

import com.example.penggajian.models.Penggajian;
import com.example.penggajian.models.Karyawan;
import com.example.penggajian.repositories.KaryawanRepository;
import com.example.penggajian.repositories.PenggajianRepository;
import com.example.penggajian.services.PenggajianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Controller
@RequestMapping("/penggajian")
public class PenggajianController {

    @Autowired
    private PenggajianService penggajianService;

    @Autowired
    private KaryawanRepository karyawanRepository;

    @Autowired
    private PenggajianRepository penggajianRepository;

    @GetMapping
    public String listPenggajian(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(defaultValue = "gajiPokok") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @PageableDefault Pageable pageable,
            Model model) {
    
        Sort sortOrder = direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortOrder);
    
        Page<Penggajian> penggajianPage = penggajianRepository.searchByKeyword(keyword, sortedPageable);
    
        model.addAttribute("penggajianList", penggajianPage.getContent());
        model.addAttribute("currentPage", penggajianPage.getNumber());
        model.addAttribute("totalPages", penggajianPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);
    
        return "penggajian";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        List<Karyawan> karyawanList = karyawanRepository.findAll();
        model.addAttribute("penggajian", new Penggajian());
        model.addAttribute("karyawanList", karyawanList);
        return "add-penggajian";
    }

    
    @PostMapping("/add")
    public String addPenggajian(@ModelAttribute Penggajian penggajian) {
        penggajianService.savePenggajian(penggajian);
        return "redirect:/penggajian";
    }

    
    
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Penggajian penggajian = penggajianService.getPenggajianById(id);
        if (penggajian != null) {
            List<Karyawan> karyawanList = karyawanRepository.findAll();
            model.addAttribute("penggajian", penggajian);
            model.addAttribute("karyawanList", karyawanList);
            return "update-penggajian"; // pastikan ini adalah nama file HTML yang benar
        }
        return "redirect:/penggajian"; // jika penggajian tidak ditemukan, redirect ke daftar penggajian
    }
    @PostMapping("/update/{id}")
    public String updatePenggajian(@PathVariable Long id, @ModelAttribute Penggajian penggajian) {
        penggajian.setId(id); // pastikan ID tetap
        penggajianService.updatePenggajian(penggajian);
        return "redirect:/penggajian"; // setelah update, kembali ke daftar penggajian
    }

    
}
