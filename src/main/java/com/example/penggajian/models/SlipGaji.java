package com.example.penggajian.models;

import jakarta.persistence.*;

@Entity
public class SlipGaji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "penggajian_id", nullable = false)
    private Penggajian penggajian;

    private double tunjangan;
    private double potonganPph21;
    private double potonganBpjs;
    private double potonganLainnya;
    private double totalGajiBersih;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Penggajian getPenggajian() {
        return penggajian;
    }

    public void setPenggajian(Penggajian penggajian) {
        this.penggajian = penggajian;
    }

    public double getTunjangan() {
        return tunjangan;
    }

    public void setTunjangan(double tunjangan) {
        this.tunjangan = tunjangan;
    }

    public double getPotonganPph21() {
        return potonganPph21;
    }

    public void setPotonganPph21(double potonganPph21) {
        this.potonganPph21 = potonganPph21;
    }

    public double getPotonganBpjs() {
        return potonganBpjs;
    }

    public void setPotonganBpjs(double potonganBpjs) {
        this.potonganBpjs = potonganBpjs;
    }

    public double getPotonganLainnya() {
        return potonganLainnya;
    }

    public void setPotonganLainnya(double potonganLainnya) {
        this.potonganLainnya = potonganLainnya;
    }

    public double getTotalGajiBersih() {
        return totalGajiBersih;
    }

    public void setTotalGajiBersih(double totalGajiBersih) {
        this.totalGajiBersih = totalGajiBersih;
    }
}
