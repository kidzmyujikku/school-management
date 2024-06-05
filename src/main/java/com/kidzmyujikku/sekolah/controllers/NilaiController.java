package com.kidzmyujikku.sekolah.controllers;

import com.kidzmyujikku.sekolah.models.MessageModel;
import com.kidzmyujikku.sekolah.models.Nilai;
import com.kidzmyujikku.sekolah.services.NilaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class NilaiController {

    @Autowired
    private NilaiService nilaiService;

    ResponseEntity<MessageModel> response = null;

    // GET /api/nilai ** Mendapatkan daftar nilai
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping(value = "/nilai", produces = "application/json")
    public ResponseEntity<MessageModel> getAllNilai() {
        MessageModel msg = new MessageModel();

        try {
            msg = nilaiService.findAll();
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

    // GET /api/nilai/{id} ** Mendapatkan informasi nilai berdasarkan ID
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @GetMapping("/nilai/{id}")
    public ResponseEntity<MessageModel> getNilaiById(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = nilaiService.findById(id);
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

    // POST /api/nilai ** Menambahkan nilai baru
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PostMapping("/nilai")
    public ResponseEntity<MessageModel> addNilai(@RequestBody Nilai nilai) {

        MessageModel msg = new MessageModel();
        try {
            msg = nilaiService.save(nilai);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.CREATED);
            } else if(msg.getMessage().equals("bad request")) {
                response = new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
            }else if(msg.getMessage().equals("not found")) {
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

    // PUT /api/nilai/{id} ** Mengupdate informasi nilai berdasarkan ID
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    @PutMapping("/nilai/{id}")
    public ResponseEntity<MessageModel> updateNilai(@PathVariable("id") Long id, @RequestBody Nilai nilai) {
        MessageModel msg = new MessageModel();
        try {
            msg = nilaiService.update(id, nilai);
            if (msg.getMessage().equals("success")) {
                response = new ResponseEntity(msg, HttpStatus.OK);
            } else if(msg.getMessage().equals("not found")) {
                response = new ResponseEntity(msg, HttpStatus.NOT_FOUND);
            }else if(msg.getMessage().equals("bad request")) {
                response = new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            msg.setStatus(false);
            msg.setMessage("error");
            msg.setData(e.getMessage());
            response = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    //  DELETE /api/nilai/{id} ** Menghapus nilai berdasarkan ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/nilai/{id}")
    public ResponseEntity<MessageModel> deleteNilai(@PathVariable("id") Long id) {
        MessageModel msg = new MessageModel();
        try {
            msg = nilaiService.delete(id);
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
