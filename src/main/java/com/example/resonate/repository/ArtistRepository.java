package com.example.resonate.repository;

import com.example.resonate.DTO.Artist.ArtistDTO;
import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.model.Album;
import com.example.resonate.model.Artist;
import com.example.resonate.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT a from Artist a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Artist> searchByName(@Param("name")String name, Pageable pageable);

    @Query("Select s from Song s WHERE s.album.artist.id=:id")
    Page<Song> findSongsById(@Param("id")Long id, Pageable pageable);

    @Query("SELECT a from Album a WHERE a.artist.id=:id")
    Page<Album> findAlbumsById(@Param("id")Long id, Pageable pageable);
}
