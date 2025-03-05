package com.example.resonate.service;


import com.example.resonate.DTO.Album.AlbumDTO;
import com.example.resonate.DTO.Album.AlbumRequestDTO;
import com.example.resonate.DTO.Album.AlbumUpdateDTO;
import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.model.Album;
import com.example.resonate.model.Song;
import com.example.resonate.repository.AlbumRepository;
import com.example.resonate.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    private SongRepository songRepository;

    public AlbumService(AlbumRepository albumRepository, SongRepository songRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    public Page<AlbumDTO> getAll(Pageable pageable) {

        return albumRepository.findAll(pageable).map(this::toAlbumDTO);


    }

    public AlbumDTO addAlbum(AlbumRequestDTO albumRequestDTO) {

            Album album = new Album();

            album.setTitle(albumRequestDTO.getTitle());
            album.setReleaseYear(albumRequestDTO.getReleaseYear());

            albumRepository.save(album);

            return toAlbumDTO(album);

    }

    @Transactional
    public AlbumDTO updateAlbum(Long id, AlbumUpdateDTO albumUpdateDTO) {

        Album album = albumRepository.findById(id).orElseThrow(()->new RuntimeException("Album not found with id "+ id));

        if(albumUpdateDTO.getTitle()!=null) {
            album.setTitle(albumUpdateDTO.getTitle());
        }

        if(albumUpdateDTO.getReleaseYear()!=null) {
            album.setReleaseYear(albumUpdateDTO.getReleaseYear());
        }

        albumRepository.save(album);

        return toAlbumDTO(album);
    }


    public void deleteAlbum(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(()->new RuntimeException("Album not found with id "+ id));

        albumRepository.delete(album);

    }

    @Transactional
    public void addSongsToAlbum(Long id, List<SongRequestDTO> songs) {

        Album album = albumRepository.findById(id).orElseThrow(()-> new RuntimeException("Album not found with id "+ id));

        List<Song> songEntities = songs.stream().map(
                dto -> {
                    Song song = new Song();
                    song.setDuration(dto.getDuration());
                    song.setTitle(dto.getTitle());
                    song.setNumber(dto.getNumber());
                    song.setAlbum(album);
                    album.getSongList().add(song);
                    return song;
                }
        ).collect(Collectors.toList());

        int duration= album.getSongList().stream().mapToInt(Song::getDuration).sum();

        album.setDuration(duration);

        albumRepository.save(album);

        songRepository.saveAll(songEntities);

    }

    public AlbumDTO toAlbumDTO(Album album) {

        List<SongDTO> songListDTO= album.getSongList().stream().map(this:: toSongDTO).collect(Collectors.toList());

        return new AlbumDTO(album.getId(),album.getTitle(),album.getReleaseYear(),album.getDuration(), songListDTO);
    }

    public SongDTO toSongDTO(Song song) {
        return new SongDTO(song.getId(),song.getTitle(),song.getNumber(),song.getDuration());
    }

    @Transactional
    public AlbumDTO removeSongFromAlbum(Long id, Long songid) {

        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException());

        Song song = songRepository.findById(songid).orElseThrow(()-> new RuntimeException());

        if(!album.getSongList().contains(song)) {
            throw new RuntimeException("Song is not part of the album");
        }

        song.setAlbum(null);
        album.getSongList().remove(song);


       int duration= album.getSongList().stream().mapToInt(Song::getDuration).sum();

        album.setDuration(duration);

        songRepository.save(song);

        albumRepository.save(album);

        return toAlbumDTO(album);


    }

    @Transactional
    public AlbumDTO addExistingSong(Long id, Long songid) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException());

        Song song = songRepository.findById(songid).orElseThrow(()-> new RuntimeException());

        if(album.getSongList().contains(song)) {
            throw new RuntimeException("Song already exists in album");
        }

        song.setAlbum(album);

        album.getSongList().add(song);

        int duration= album.getSongList().stream().mapToInt(Song::getDuration).sum();

        album.setDuration(duration);


        songRepository.save(song);
        albumRepository.save(album);

        return toAlbumDTO(album);

    }

    public Page<SongDTO> getSongs(Long id, Pageable pageable) {

        Album album = albumRepository.findById(id).orElseThrow(()-> new RuntimeException("album not found"));

        Page<Song> song = songRepository.findByAlbumId(id,pageable);

        return song.map(this::toSongDTO);


    }

    public Page<AlbumDTO> searchAlbumByTitle(String title, Pageable pageable) {

        return albumRepository.searchAlbumByTitle(title, pageable).map(this::toAlbumDTO);
    }

    public AlbumDTO findById(Long id) {

        return toAlbumDTO(albumRepository.findById(id).orElseThrow(()-> new RuntimeException("Album not found")));
    }
}
