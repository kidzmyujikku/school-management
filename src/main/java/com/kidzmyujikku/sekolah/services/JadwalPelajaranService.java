package com.kidzmyujikku.sekolah.services;

import com.kidzmyujikku.sekolah.models.Guru;
import com.kidzmyujikku.sekolah.models.JadwalPelajaran;
import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.repositories.GuruRepository;
import com.kidzmyujikku.sekolah.repositories.JadwalPelajaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JadwalPelajaranService {

    @Autowired
    private JadwalPelajaranRepository jadwalPelajaranRepository;

    @Autowired
    private GuruRepository guruRepository;

    private MessageModel createResponse(boolean status, String message, Object data) {
        MessageModel msg = new MessageModel();
        msg.setStatus(status);
        msg.setMessage(message);
        msg.setData(data);
        return msg;
    }

    public MessageModel findAll() {
        List<JadwalPelajaran> data = jadwalPelajaranRepository.findAll();
        String message = (data.size() > 0) ? "success" : "not found";
        return createResponse(!data.isEmpty(), message, data);
    }

    public MessageModel findById(Long id) {
        Optional<JadwalPelajaran> data = jadwalPelajaranRepository.findById(id);
        String message = data.isPresent() ? "success" : "not found";
        return createResponse(data.isPresent(), message, data.orElse(null));
    }

    public MessageModel save(JadwalPelajaran jadwalPelajaran) {
        Optional<Guru> dataGuru = guruRepository.findById(jadwalPelajaran.getGuru().getId());
        System.out.println(dataGuru);
        if (dataGuru.isPresent()) {
            jadwalPelajaranRepository.save(jadwalPelajaran);
            return createResponse(true, "success", "Berhasil membuat data jadwal pelajaran " + jadwalPelajaran.getMataPelajaran());
        }else{
            return createResponse(false, "not found", "ID guru tidak ditemukan");
        }
    }

    public MessageModel update(Long id, JadwalPelajaran jadwalPelajaran) {
        Optional<JadwalPelajaran> data = jadwalPelajaranRepository.findById(id);
        if (data.isPresent()) {
            JadwalPelajaran existingJadwalPelajaran = data.get();
            existingJadwalPelajaran.setHari(jadwalPelajaran.getHari());
            existingJadwalPelajaran.setJamMulai(jadwalPelajaran.getJamMulai());
            existingJadwalPelajaran.setJamSelesai(jadwalPelajaran.getJamSelesai());
            existingJadwalPelajaran.setMataPelajaran(jadwalPelajaran.getMataPelajaran());
            jadwalPelajaranRepository.save(existingJadwalPelajaran);
            return createResponse(true, "success", "Berhasil mengupdate data jadwal pelajaran " + jadwalPelajaran.getMataPelajaran());
        } else {
            return createResponse(false, "not found", "ID jadwal pelajaran tidak ditemukan");
        }
    }

    public MessageModel delete(Long id) {
        Optional<JadwalPelajaran> data = jadwalPelajaranRepository.findById(id);
        if (data.isPresent()) {
            jadwalPelajaranRepository.deleteById(id);
            return createResponse(true, "success", "Berhasil menghapus data dengan ID jadwal pelajaran " + id);
        } else {
            return createResponse(false, "not found", "ID jadwal pelajaran tidak ditemukan");
        }
    }
}
