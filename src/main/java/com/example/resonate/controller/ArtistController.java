package com.example.resonate.controller;

import com.example.resonate.DTO.Album.AlbumRequestDTO;
import com.example.resonate.DTO.Artist.ArtistDTO;
import com.example.resonate.DTO.Artist.ArtistRequestDTO;
import com.example.resonate.DTO.Artist.ArtistUpdateDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resonate/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<Page<ArtistDTO>> getAll(Pageable pageable) {

        return ResponseEntity.ok().body(artistService.getAll(pageable));

    }

    @PostMapping
    public ResponseEntity<ArtistDTO> addArtist(@Valid @RequestBody ArtistRequestDTO artistRequestDTO) {

        return ResponseEntity.ok(artistService.addArtist(artistRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateAlbum(@PathVariable Long id, @Valid @RequestBody ArtistUpdateDTO artistUpdateDTO) {


        return ResponseEntity.ok(artistService.updateArtist(id,artistUpdateDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/album")
    public ResponseEntity<Void> addAlbumToArtist(@PathVariable Long id, @Valid @RequestBody AlbumRequestDTO albumRequestDTO) {

        artistService.addAlbumToArtist(id,albumRequestDTO);

        return ResponseEntity.ok().build();
    }



}
