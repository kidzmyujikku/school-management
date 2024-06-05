package com.kidzmyujikku.sekolah.services;

import com.kidzmyujikku.sekolah.models.Guru;
import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.repositories.GuruRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuruService {

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
        List<Guru> data = guruRepository.findAll();
        String message = (data.size() > 0) ? "success" : "not found";
        return createResponse(!data.isEmpty(), message, data);
    }

    public MessageModel findById(Long id) {
        Optional<Guru> data = guruRepository.findById(id);
        String message = data.isPresent() ? "success" : "not found";
        return createResponse(data.isPresent(), message, data.orElse(null));
    }

    public MessageModel save(Guru guru) {
        guruRepository.save(guru);
        return createResponse(true, "success", "Berhasil membuat data guru " + guru.getNama());
    }

    public MessageModel update(Long id, Guru guru) {
        Optional<Guru> data = guruRepository.findById(id);
        if (data.isPresent()) {
            Guru existingGuru = data.get();
            existingGuru.setNama(guru.getNama());
            existingGuru.setAlamat(guru.getAlamat());
            guruRepository.save(existingGuru);
            return createResponse(true, "success", "Berhasil mengupdate data guru " + guru.getNama());
        } else {
            return createResponse(false, "not found", "ID guru tidak ditemukan");
        }
    }

    public MessageModel delete(Long id) {
        Optional<Guru> data = guruRepository.findById(id);
        if (data.isPresent()) {
            guruRepository.deleteById(id);
            return createResponse(true, "success", "Berhasil menghapus data dengan ID Guru " + id);
        } else {
            return createResponse(false, "not found", "ID guru tidak ditemukan");
        }
    }
}
