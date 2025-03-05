package com.example.resonate.controller;

import com.example.resonate.DTO.Album.AlbumDTO;
import com.example.resonate.DTO.Album.AlbumRequestDTO;
import com.example.resonate.DTO.Artist.ArtistDTO;
import com.example.resonate.DTO.Artist.ArtistRequestDTO;
import com.example.resonate.DTO.Artist.ArtistUpdateDTO;
import com.example.resonate.DTO.Song.SongDTO;
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


    private ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<Page<ArtistDTO>> getAll(Pageable pageable) {

        return ResponseEntity.ok().body(artistService.getAll(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> findById(Long id) {

        return ResponseEntity.ok().body(artistService.findById(id));

    }

    @PostMapping
    public ResponseEntity<ArtistDTO> addArtist(@Valid @RequestBody ArtistRequestDTO artistRequestDTO) {

        return ResponseEntity.ok(artistService.addArtist(artistRequestDTO));
    }

    @GetMapping("/name")
    public ResponseEntity<Page<ArtistDTO>> searchByName(@RequestParam String name, Pageable pageable) {

        Page<ArtistDTO> artists = artistService.searchByName(name,pageable);

        return ResponseEntity.ok(artists);

    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<Page<SongDTO>> getAllSongs(@PathVariable Long id, Pageable pageable) {

        return ResponseEntity.ok(artistService.findSongsById(id, pageable));

    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<Page<AlbumDTO>> getAllAlbums(@PathVariable Long id, Pageable pageable) {

        return ResponseEntity.ok(artistService.findAlbumsById(id, pageable));

    }


    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @Valid @RequestBody ArtistUpdateDTO artistUpdateDTO) {


        return ResponseEntity.ok(artistService.updateArtist(id,artistUpdateDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/album")
    public ResponseEntity<Void> addAlbumToArtist(@PathVariable Long id, @Valid @RequestBody AlbumRequestDTO albumRequestDTO) {

        artistService.addAlbumToArtist(id,albumRequestDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/add/album/{albumid}")
    public ResponseEntity<ArtistDTO> addExistingAlbum(@PathVariable Long id, @PathVariable Long albumid) {

        return ResponseEntity.ok(artistService.addExistingAlbum(id,albumid));
    }

    @PutMapping("/{id}/remove/album/{albumid}")
    public ResponseEntity<ArtistDTO> removeAlbumFromArtist(@PathVariable Long id, @PathVariable Long albumid) {

        return ResponseEntity.ok(artistService.removeAlbumFromArtist(id,albumid));
    }



}
