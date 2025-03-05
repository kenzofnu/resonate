package com.example.resonate.service;


import com.example.resonate.DTO.Album.AlbumDTO;
import com.example.resonate.DTO.Album.AlbumRequestDTO;
import com.example.resonate.DTO.Artist.ArtistDTO;
import com.example.resonate.DTO.Artist.ArtistRequestDTO;
import com.example.resonate.DTO.Artist.ArtistUpdateDTO;
import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.model.Album;
import com.example.resonate.model.Artist;
import com.example.resonate.model.Song;
import com.example.resonate.repository.AlbumRepository;
import com.example.resonate.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private ArtistRepository artistRepository;
    private AlbumRepository albumRepository;

    public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }


    public Page<ArtistDTO> getAll(Pageable pageable) {

        return artistRepository.findAll(pageable).map(this::toArtistDTO);


    }

    public ArtistDTO addArtist(ArtistRequestDTO artistRequestDTO) {

        Artist artist = new Artist();

        artist.setName(artistRequestDTO.getName());

        artist.setDescription(artistRequestDTO.getDescription());

        artistRepository.save(artist);

        return toArtistDTO(artist);



    }

    @Transactional
    public ArtistDTO updateArtist(Long id, ArtistUpdateDTO artistUpdateDTO) {

        Artist artist = artistRepository.findById(id).orElseThrow(()-> new RuntimeException());

        if(artistUpdateDTO.getName()!=null) {
            artist.setName(artistUpdateDTO.getName());
        }
        if(artistUpdateDTO.getDescription()!=null) {
            artist.setDescription(artistUpdateDTO.getDescription());
        }

        artistRepository.save(artist);

        return toArtistDTO(artist);
    }

    public void deleteArtist(Long id) {

        Artist artist = artistRepository.findById(id).orElseThrow(()-> new RuntimeException());

        artistRepository.delete(artist);
    }

    @Transactional
    public void addAlbumToArtist(Long id, AlbumRequestDTO albumRequestDTO) {

        Artist artist = artistRepository.findById(id).orElseThrow(()-> new RuntimeException());

        Album album = new Album();

        album.setTitle(albumRequestDTO.getTitle());
        album.setReleaseYear(albumRequestDTO.getReleaseYear());
        album.setArtist(artist);

        albumRepository.save(album);

    }


    public ArtistDTO toArtistDTO(Artist artist) {

        List<AlbumDTO> albumDTOList= artist.getAlbumList().stream().map(this::toAlbumDTO).collect(Collectors.toList());

        return new ArtistDTO(artist.getId(),artist.getName(),artist.getDescription(),albumDTOList);

    }

    public AlbumDTO toAlbumDTO(Album album) {

        List<SongDTO> songListDTO= album.getSongList().stream().map(this:: toSongDTO).collect(Collectors.toList());

        return new AlbumDTO(album.getId(),album.getTitle(),album.getReleaseYear(),album.getDuration(), songListDTO);
    }

    public SongDTO toSongDTO(Song song) {
        return new SongDTO(song.getId(),song.getTitle(),song.getNumber(),song.getDuration());
    }


    @Transactional
    public ArtistDTO addExistingAlbum(Long id, Long albumid) {

        Artist artist = artistRepository.findById(id).orElseThrow(()-> new RuntimeException("Artist not found"));

        Album album = albumRepository.findById(albumid).orElseThrow(()-> new RuntimeException("Album not found"));

        if(artist.getAlbumList().contains(album)) {
            throw new RuntimeException("Album already exists");
        }

        album.setArtist(artist);

        artist.getAlbumList().add(album);

        albumRepository.save(album);

        artistRepository.save(artist);

        return toArtistDTO(artist);

    }

    @Transactional
    public ArtistDTO removeAlbumFromArtist(Long id, Long albumid) {

        Artist artist = artistRepository.findById(id).orElseThrow(()-> new RuntimeException("Artist not found"));

        Album album = albumRepository.findById(albumid).orElseThrow(()-> new RuntimeException("Album not found"));

        if(artist.getAlbumList().contains(album)) {
            album.setArtist(null);
        }

        artist.getAlbumList().add(album);

        artistRepository.save(artist);

        albumRepository.save(album);

        return toArtistDTO(artist);

    }

    public Page<ArtistDTO> searchByName(String name, Pageable pageable) {

        return artistRepository.searchByName(name,pageable).map(this::toArtistDTO);
    }

    public Page<SongDTO> findSongsById(Long id, Pageable pageable) {

        Page<Song> page= artistRepository.findSongsById(id,pageable);

        return page.map(this::toSongDTO);
    }

    public Page<AlbumDTO> findAlbumsById(Long id, Pageable pageable) {

        Page<Album> album = artistRepository.findAlbumsById(id, pageable);

        return album.map(this::toAlbumDTO);

    }

    public ArtistDTO findById(Long id) {

        Artist artist = artistRepository.findById(id).orElseThrow(()-> new RuntimeException("Artist not found"));

        return toArtistDTO(artist);
    }
}
