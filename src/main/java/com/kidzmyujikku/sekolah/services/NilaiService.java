package com.kidzmyujikku.sekolah.services;

import com.kidzmyujikku.sekolah.models.*;
import com.kidzmyujikku.sekolah.repositories.GuruRepository;
import com.kidzmyujikku.sekolah.repositories.JadwalPelajaranRepository;
import com.kidzmyujikku.sekolah.repositories.NilaiRepository;
import com.kidzmyujikku.sekolah.repositories.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NilaiService { // Mengganti GuruService dengan NilaiService

    @Autowired
    private NilaiRepository nilaiRepository; // Mengganti GuruRepository dengan NilaiRepository

    @Autowired
    private GuruRepository guruRepository; // Mengganti GuruRepository dengan NilaiRepository

    @Autowired
    private JadwalPelajaranRepository jadwalPelajaranRepository; // Mengganti GuruRepository dengan NilaiRepository

    @Autowired
    private SiswaRepository siswaRepository; // Mengganti GuruRepository dengan NilaiRepository

    private MessageModel createResponse(boolean status, String message, Object data) {
        MessageModel msg = new MessageModel();
        msg.setStatus(status);
        msg.setMessage(message);
        msg.setData(data);
        return msg;
    }

    public MessageModel findAll() {
        List<Nilai> data = nilaiRepository.findAll(); // Mengganti guruRepository dengan nilaiRepository
        String message = (data.size() > 0) ? "success" : "not found";
        return createResponse(!data.isEmpty(), message, data);
    }

    public MessageModel findById(Long id) {
        Optional<Nilai> data = nilaiRepository.findById(id); // Mengganti guruRepository dengan nilaiRepository
        String message = data.isPresent() ? "success" : "not found";
        return createResponse(data.isPresent(), message, data.orElse(null));
    }

    public MessageModel save(Nilai nilai) { // Mengganti Guru dengan Nilai
        Optional<Guru> dataGuru = guruRepository.findById(nilai.getGuru().getId());
        Optional<Siswa> dataSiswa = siswaRepository.findById(nilai.getSiswa().getId());
        Optional<JadwalPelajaran> dataJadwalPelajaran = jadwalPelajaranRepository.findById(nilai.getJadwalPelajaran().getId());

        if(nilai.getNilai() > 100){
            return createResponse(false, "bad request", "Nilai harus lebih kecil dari 100");
        }

        if(dataGuru.isEmpty()){
            return createResponse(false, "not found", "ID guru tidak ditemukan");
        }

        if (dataSiswa.isEmpty()) {
            return createResponse(false, "not found", "ID siswa tidak ditemukan");
        }

        if (dataJadwalPelajaran.isEmpty()) {
            return createResponse(false, "not found", "ID pelajaran tidak ditemukan");
        }

        nilaiRepository.save(nilai); // Mengganti guruRepository dengan nilaiRepository
        return createResponse(true, "success", "Berhasil membuat data nilai"); // Mengganti guru.getNama() dengan nilai.getNama()
    }

    public MessageModel update(Long id, Nilai nilai) { // Mengganti Guru dengan Nilai

        Optional<Guru> dataGuru = guruRepository.findById(nilai.getGuru().getId());
        Optional<Siswa> dataSiswa = siswaRepository.findById(nilai.getSiswa().getId());
        Optional<JadwalPelajaran> dataJadwalPelajaran = jadwalPelajaranRepository.findById(nilai.getJadwalPelajaran().getId());

        if(nilai.getNilai() > 100){
            return createResponse(false, "bad request", "Nilai harus lebih kecil dari 100");
        }

        if(dataGuru.isEmpty()){
            return createResponse(false, "not found", "ID guru tidak ditemukan");
        }

        if (dataSiswa.isEmpty()) {
            return createResponse(false, "not found", "ID siswa tidak ditemukan");
        }

        if (dataJadwalPelajaran.isEmpty()) {
            return createResponse(false, "not found", "ID pelajaran tidak ditemukan");
        }

        Optional<Nilai> data = nilaiRepository.findById(id); // Mengganti guruRepository dengan nilaiRepository
        if (data.isPresent()) {
            Nilai existingNilai = data.get(); // Mengganti Guru dengan Nilai
            existingNilai.setNilai(nilai.getNilai()); // Mengganti Guru dengan Nilai
            existingNilai.setGuru(nilai.getGuru()); // Mengganti Guru dengan Nilai
            existingNilai.setSiswa(nilai.getSiswa()); // Mengganti Guru dengan Nilai
            nilaiRepository.save(existingNilai); // Mengganti guruRepository dengan nilaiRepository
            return createResponse(true, "success", "Berhasil mengupdate data nilai"); // Mengganti guru.getNama() dengan nilai.getNama()
        } else {
            return createResponse(false, "not found", "ID nilai tidak ditemukan");
        }
    }

    public MessageModel delete(Long id) {
        Optional<Nilai> data = nilaiRepository.findById(id); // Mengganti guruRepository dengan nilaiRepository
        if (data.isPresent()) {
            nilaiRepository.deleteById(id); // Mengganti guruRepository dengan nilaiRepository
            return createResponse(true, "success", "Berhasil menghapus data dengan ID Nilai " + id); // Mengganti Guru dengan Nilai
        } else {
            return createResponse(false, "not found", "ID nilai tidak ditemukan");
        }
    }
}
