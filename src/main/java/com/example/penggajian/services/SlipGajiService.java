package com.example.penggajian.services;

import com.example.penggajian.models.SlipGaji;
import com.example.penggajian.repositories.SlipGajiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlipGajiService {

    @Autowired
    private SlipGajiRepository slipGajiRepository;

    public List<SlipGaji> getAllSlipGaji() {
        return slipGajiRepository.findAll();
    }
}
