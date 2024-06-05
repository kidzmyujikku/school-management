package com.kidzmyujikku.sekolah.models;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "jadwal_pelajaran")
public class JadwalPelajaran {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hari", nullable = false)
    private String hari;

    @Column(name = "jam_mulai", nullable = false)
    private Time jamMulai;

    @Column(name = "jam_selesai", nullable = false)
    private Time jamSelesai;

    @Column(name = "mata_pelajaran", nullable = false)
    private String mataPelajaran;

    @ManyToOne
    @JoinColumn(name = "guru_id")
    private Guru guru;

    public JadwalPelajaran(){

    }

    public JadwalPelajaran(String hari, Time jamMulai, Time jamSelesai, String mataPelajaran, Guru guru) {
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.mataPelajaran = mataPelajaran;
        this.guru = guru;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Time getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Time jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Time getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Time jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getMataPelajaran() {
        return mataPelajaran;
    }

    public void setMataPelajaran(String mataPelajaran) {
        this.mataPelajaran = mataPelajaran;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }
}
