package com.kidzmyujikku.sekolah.controllers;

import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.models.Siswa;
import com.kidzmyujikku.sekolah.services.SiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SiswaController {

    @Autowired
    private SiswaService siswaService;

    ResponseEntity<MessageModel> response = null;

    // GET /api/siswa ** Mendapatkan daftar siswa
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/siswa", produces = "application/json")
    public ResponseEntity<MessageModel> getAllSiswa() {
        MessageModel msg = new MessageModel();

        try {
            msg = siswaService.findAll();
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

    // GET /api/siswa/{id} ** Mendapatkan informasi siswa berdasarkan ID
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/siswa/{id}")
    public ResponseEntity<MessageModel> getSiswaById(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = siswaService.findById(id);
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

    // POST /api/siswa ** Menambahkan siswa baru
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/siswa")
    public ResponseEntity<MessageModel> addSiswa(@RequestBody Siswa siswa) {

        MessageModel msg = new MessageModel();
        try {
            msg = siswaService.save(siswa);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.CREATED);
            } else if(msg.getMessage().equals("error")) {
                response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    // PUT /api/siswa/{id} ** Mengupdate informasi siswa berdasarkan ID
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping("/siswa/{id}")
    public ResponseEntity<MessageModel> updateSiswa(@PathVariable("id") Long id, @RequestBody Siswa siswa) {
        MessageModel msg = new MessageModel();
        try {
            msg = siswaService.update(id, siswa);
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

    //  DELETE /api/siswa/{id} ** Menghapus siswa berdasarkan ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/siswa/{id}")
    public ResponseEntity<MessageModel> deleteSiswa(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = siswaService.delete(id);
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
