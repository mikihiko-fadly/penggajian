package com.example.penggajian.controllers;

import com.example.penggajian.models.SlipGaji;
import com.example.penggajian.repositories.SlipGajiRepository;
// import com.example.penggajian.services.SlipGajiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/slip-gaji")
public class SlipGajiController {

    // @Autowired
    // private SlipGajiService slipGajiService;
    @Autowired
    private SlipGajiRepository slipGajiRepository;

    @GetMapping("/{penggajianId}")
    public String getSlipGaji(@PathVariable Long penggajianId, Model model) {
        SlipGaji slipGaji = slipGajiRepository.findByPenggajianId(penggajianId)
            .orElseThrow(() -> new RuntimeException("Slip Gaji tidak ditemukan!"));
        
        model.addAttribute("slipGaji", slipGaji);
        return "slip-gaji-list"; 
    }
}
