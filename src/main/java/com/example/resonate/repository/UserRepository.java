package com.example.resonate.repository;

import com.example.resonate.DTO.User.UserDTO;
import com.example.resonate.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.playlistList p WHERE p.id = :playlistId")
    List<User> findByPlaylistsId(Long playlistId);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);


}
