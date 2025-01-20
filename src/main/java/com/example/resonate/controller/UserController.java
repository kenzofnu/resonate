package com.example.resonate.controller;

import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.DTO.Playlist.PlaylistRequestDTO;
import com.example.resonate.DTO.Song.SongRequestDTO;
import com.example.resonate.DTO.User.UserDTO;
import com.example.resonate.DTO.User.UserRequestDTO;
import com.example.resonate.DTO.User.UserUpdateDTO;
import com.example.resonate.model.User;
import com.example.resonate.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resonate/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getALl(Pageable pageable) {
        return ResponseEntity.ok(userService.getAll(pageable));
    }

    @GetMapping("/name")
    public ResponseEntity<Page<UserDTO>> findByName(@RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok().body(userService.findByName(name, pageable));
    }


    @PostMapping
    public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {

        return ResponseEntity.ok().body(userService.addNewUser(userRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {

        return ResponseEntity.ok(userService.updateUser(id, userUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{id}/playlist")
    public ResponseEntity<PlaylistDTO> createPlaylist(@PathVariable Long id, @Valid @RequestBody PlaylistRequestDTO playlistRequestDTO) {

        return ResponseEntity.ok(userService.createPlaylist(id,playlistRequestDTO));
    }



}
