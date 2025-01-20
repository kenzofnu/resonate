package com.example.resonate.repository;

import com.example.resonate.DTO.Album.AlbumDTO;
import com.example.resonate.model.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {

    @Query("SELECT a FROM Album a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%',:title,'%'))")
    Page<Album> searchAlbumByTitle(@Param("title") String title, Pageable pageable);
}
