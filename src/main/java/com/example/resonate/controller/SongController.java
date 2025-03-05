package com.example.resonate.controller;

import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.DTO.Song.SongUpdateDTO;
import com.example.resonate.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resonate/song")
public class SongController {


    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<Page<SongDTO>> getAllSong(Pageable pageable) {

        Page<SongDTO> songs = songService.getAll(pageable);

        return ResponseEntity.ok(songs);

    }

    @GetMapping("/summary")
    public ResponseEntity<Page<SongDTO>> getAllSongSummary(Pageable pageable) {

        Page<SongDTO> songs = songService.getAllSummary(pageable);
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(songService.findById(id));
    }

    @GetMapping("/title")
    public ResponseEntity<Page<SongDTO>> searchByTitle(@RequestParam String title, Pageable pageable) {

        Page<SongDTO> songs = songService.searchByTitle(title,pageable);

        return ResponseEntity.ok(songs);

    }


    @PostMapping()
    public ResponseEntity<SongDTO> addSong(@Valid @RequestBody SongRequestDTO songRequestDTO) {
        return ResponseEntity.ok().body(songService.addSong(songRequestDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {

       songService.deleteSong(id);

       return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Long id, @Valid @RequestBody SongUpdateDTO songUpdateDTO) {

        return ResponseEntity.ok().body(songService.updateSong(id,songUpdateDTO));

    }



}
