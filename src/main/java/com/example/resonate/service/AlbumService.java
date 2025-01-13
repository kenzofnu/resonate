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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
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

    public void addSongsToAlbum(Long id, List<SongRequestDTO> songs) {

        Album album = albumRepository.findById(id).orElseThrow(()-> new RuntimeException("Album not found with id "+ id));

        List<Song> songEntities = songs.stream().map(
                dto -> {
                    Song song = new Song();
                    song.setDuration(dto.getDuration());
                    song.setTitle(dto.getTitle());
                    song.setNumber(dto.getNumber());
                    song.setAlbum(album);
                    return song;
                }
        ).collect(Collectors.toList());

        int totalDuration = songs.stream().mapToInt(SongRequestDTO::getDuration).sum();

        album.setDuration(totalDuration);

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
}
