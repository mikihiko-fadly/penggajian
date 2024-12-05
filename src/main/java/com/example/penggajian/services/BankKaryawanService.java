package com.example.penggajian.services;

import com.example.penggajian.models.BankKaryawan;
import com.example.penggajian.repositories.BankKaryawanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankKaryawanService {

    @Autowired
    private BankKaryawanRepository bankKaryawanRepository;

    public List<BankKaryawan> getAllBankKaryawan() {
        return bankKaryawanRepository.findAll();
    }

    public BankKaryawan getBankKaryawanById(Long id) {
        Optional<BankKaryawan> bankKaryawan = bankKaryawanRepository.findById(id);
        return bankKaryawan.orElse(null);
    }

    public void saveBankKaryawan(BankKaryawan bankKaryawan) {
        bankKaryawanRepository.save(bankKaryawan);
    }

    public void deleteBankKaryawan(Long id) {
        bankKaryawanRepository.deleteById(id);
    }
}
