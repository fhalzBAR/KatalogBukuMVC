/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.katalogbukumvc.controller;

import com.mycompany.katalogbukumvc.model.Buku;
import com.mycompany.katalogbukumvc.model.BukuModel;
import com.mycompany.katalogbukumvc.view.BukuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Lenovo
 */
public class BukuController {

    private final BukuModel model;
    private final BukuView view;

    public BukuController(BukuModel model, BukuView view) {
        this.model = model;
        this.view = view;
        
        view.addSimpanListener(event -> simpanBuku());
        view.addClear(event -> clearBuku());
        view.addHapusListener(wvent -> hapusBuku());
        
        view.tampilkanData(model.getSemuaBuku());
    }

    public void simpanBuku() {
        String judul = view.getJudul().trim();
        String penulis = view.getPenulis().trim();
        String tahun = view.getTahunTerbit().trim();

        if (judul.isEmpty() || penulis.isEmpty() || tahun.isEmpty()) {
            view.tampilkanPeringatan("Input Belum Lengkap", "Judul, Penulis, Tahun Terbit Wajib diisi");
            return;
        }

        int tahunTerbit;
        try {
            tahunTerbit = Integer.parseInt(tahun);
        } catch (Exception e) {
            view.tampilkanPeringatan("Input Tidak Valid", "Tahun Terbit Harus Berupa Angka");
            return;
        }
        
        model.tambahBuku(new Buku(judul, penulis, tahunTerbit));
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();

    }

    public void hapusBuku() {
        int baris = view.getBarisTerpilih();
        if (baris == -1) {
            view.tampilkanPeringatan("Hapus Buku", "Pilih dulu baris yang akan di hapus");
            return;
        }
        
        model.hapusBuku(baris);
        view.tampilkanData(model.getSemuaBuku());
    }

    public void clearBuku() {
        model.hapusSemua();
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();
    }
}
