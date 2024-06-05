package com.kidzmyujikku.sekolah.services;

import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.models.Siswa;
import com.kidzmyujikku.sekolah.repositories.SiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiswaService {

    @Autowired
    private SiswaRepository siswaRepository;

    private MessageModel createResponse(boolean status, String message, Object data) {
        MessageModel msg = new MessageModel();
        msg.setStatus(status);
        msg.setMessage(message);
        msg.setData(data);
        return msg;
    }

    public MessageModel findAll() {
        List<Siswa> data = siswaRepository.findAll();
        String message = (data.size() > 0) ? "success" : "not found";
        return createResponse(!data.isEmpty(), message, data);
    }

    public MessageModel findById(Long id) {
        Optional<Siswa> data = siswaRepository.findById(id);
        String message = data.isPresent() ? "success" : "not found";
        return createResponse(data.isPresent(), message, data.orElse(null));
    }

    public MessageModel save(Siswa siswa) {
        siswaRepository.save(siswa);
        return createResponse(true, "success", "Berhasil membuat data siswa " + siswa.getNama());
    }

    public MessageModel update(Long id, Siswa siswa) {
        Optional<Siswa> data = siswaRepository.findById(id);
        if (data.isPresent()) {
            Siswa existingSiswa = data.get();
            existingSiswa.setNama(siswa.getNama());
            existingSiswa.setKelas(siswa.getKelas());
            existingSiswa.setAlamat(siswa.getAlamat());
            siswaRepository.save(existingSiswa);
            return createResponse(true, "success", "Berhasil mengupdate data siswa " + siswa.getNama());
        } else {
            return createResponse(false, "not found", "ID siswa tidak ditemukan");
        }
    }

    public MessageModel delete(Long id) {
        Optional<Siswa> data = siswaRepository.findById(id);
        if (data.isPresent()) {
            siswaRepository.deleteById(id);
            return createResponse(true, "success", "Berhasil menghapus data dengan ID siswa " + id);
        } else {
            return createResponse(false, "not found", "ID siswa tidak ditemukan");
        }
    }
}
