package com.kidzmyujikku.sekolah.controllers;

import com.kidzmyujikku.sekolah.models.Guru;
import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.services.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class GuruController {

    @Autowired
    private GuruService guruService;

    ResponseEntity<MessageModel> response = null;

    // GET /api/guru ** Mendapatkan daftar guru
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/guru", produces = "application/json")
    public ResponseEntity<MessageModel> getAllGuru() {
        MessageModel msg = new MessageModel();

        try {
            msg = guruService.findAll();
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

    // GET /api/guru/{id} ** Mendapatkan informasi guru berdasarkan ID
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/guru/{id}")
    public ResponseEntity<MessageModel> getGuruById(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = guruService.findById(id);
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

    // POST /api/guru ** Menambahkan guru baru
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageModel> addGuru(@RequestBody Guru guru) {

        MessageModel msg = new MessageModel();
        try {
            msg = guruService.save(guru);
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

    // PUT /api/guru/{id} ** Mengupdate informasi guru berdasarkan ID
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping("/guru/{id}")
    public ResponseEntity<MessageModel> updateGuru(@PathVariable("id") Long id, @RequestBody Guru guru) {
        MessageModel msg = new MessageModel();
        try {
            msg = guruService.update(id, guru);
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

    //  DELETE /api/guru/{id} ** Menghapus guru berdasarkan ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/guru/{id}")
    public ResponseEntity<MessageModel> deleteGuru(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = guruService.delete(id);
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
