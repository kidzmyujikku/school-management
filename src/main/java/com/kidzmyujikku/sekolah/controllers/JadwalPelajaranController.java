package com.kidzmyujikku.sekolah.controllers;

import com.kidzmyujikku.sekolah.models.JadwalPelajaran;
import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.services.JadwalPelajaranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class JadwalPelajaranController {

    @Autowired
    private JadwalPelajaranService jadwalPelajaranService;

    ResponseEntity<MessageModel> response = null;

    // GET /api/jadwal-pelajaran ** Mendapatkan daftar jadwal pelajaran
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/jadwal-pelajaran", produces = "application/json")
    public ResponseEntity<MessageModel> getAllJadwalPelajaran() {
        MessageModel msg = new MessageModel();

        try {
            msg = jadwalPelajaranService.findAll();
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.OK);
            } else if(msg.getMessage().equals("not found")) {
                response = new ResponseEntity(msg, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // GET /api/jadwal-pelajaran/{id} ** Mendapatkan informasi jadwal pelajaran berdasarkan ID
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/jadwal-pelajaran/{id}")
    public ResponseEntity<MessageModel> getJadwalPelajaranById(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = jadwalPelajaranService.findById(id);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.OK);
            } else if(msg.getMessage().equals("not found")) {
                response = new ResponseEntity(msg, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // POST /api/jadwal-pelajaran ** Menambahkan jadwal pelajaran baru
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/jadwal-pelajaran")
    public ResponseEntity<MessageModel> addJadwalPelajaran(@RequestBody JadwalPelajaran jadwalPelajaran) {

        MessageModel msg = new MessageModel();
        try {
            msg = jadwalPelajaranService.save(jadwalPelajaran);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.CREATED);
            } else if(msg.getMessage().equals("not found")) {
                response = new ResponseEntity(msg, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // PUT /api/jadwal-pelajaran/{id} ** Mengupdate informasi jadwal pelajaran berdasarkan ID
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping("/jadwal-pelajaran/{id}")
    public ResponseEntity<MessageModel> updateJadwalPelajaran(@PathVariable("id") Long id, @RequestBody JadwalPelajaran jadwalPelajaran) {
        MessageModel msg = new MessageModel();
        try {
            msg = jadwalPelajaranService.update(id, jadwalPelajaran);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.OK);
            } else if(msg.getMessage().equals("not found")) {
                response = new ResponseEntity(msg, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // DELETE /api/jadwal-pelajaran/{id} ** Menghapus jadwal pelajaran berdasarkan ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/jadwal-pelajaran/{id}")
    public ResponseEntity<MessageModel> deleteJadwalPelajaran(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = jadwalPelajaranService.delete(id);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.OK);
            } else if(msg.getMessage().equals("not found")) {
                response = new ResponseEntity(msg, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
