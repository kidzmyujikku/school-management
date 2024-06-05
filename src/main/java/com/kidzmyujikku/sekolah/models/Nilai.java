package com.kidzmyujikku.sekolah.models;

import jakarta.persistence.*;
@Entity
@Table(name = "nilai")
public class Nilai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nilai")
    private int nilai;

    @ManyToOne
    @JoinColumn(name = "pelajaran_id", referencedColumnName = "id")
    private JadwalPelajaran pelajaran;

    @ManyToOne
    @JoinColumn(name = "siswa_id", referencedColumnName = "id")
    private Siswa siswa;

    @ManyToOne
    @JoinColumn(name = "guru_id", referencedColumnName = "id")
    private Guru guru;

    // Constructors, getters, and setters

    public Nilai() {
    }

    public Nilai(int nilai, JadwalPelajaran pelajaran, Siswa siswa, Guru guru) {
        this.nilai = nilai;
        this.pelajaran = pelajaran;
        this.siswa = siswa;
        this.guru = guru;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public JadwalPelajaran getJadwalPelajaran() {
        return pelajaran;
    }

    public void setJadwalPelajaran(JadwalPelajaran pelajaran) {
        this.pelajaran = pelajaran;
    }

    public Siswa getSiswa() {
        return siswa;
    }

    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }
}
