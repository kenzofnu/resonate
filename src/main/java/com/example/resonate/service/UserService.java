package com.example.resonate.service;

import com.example.resonate.DTO.Playlist.PlaylistDTO;
import com.example.resonate.DTO.Playlist.PlaylistRequestDTO;
import com.example.resonate.DTO.Song.SongDTO;
import com.example.resonate.DTO.User.UserDTO;
import com.example.resonate.DTO.User.UserRequestDTO;
import com.example.resonate.DTO.User.UserUpdateDTO;
import com.example.resonate.model.Playlist;
import com.example.resonate.model.Role;
import com.example.resonate.model.Song;
import com.example.resonate.model.User;
import com.example.resonate.repository.PlaylistRepository;
import com.example.resonate.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PlaylistRepository playlistRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PlaylistRepository playlistRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserDTO> getAll(Pageable pageable) {

        return userRepository.findAll(pageable).map(this::toUserDTO);

    }


    public UserDTO toUserDTO(User user) {

        List<PlaylistDTO> playlistDTO = user.getPlaylistList().stream().map(this::toPlaylistDTO).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getName(),user.getEmail(),user.getPassword(),user.getDob(), playlistDTO);
    }

    public UserDTO addNewUser(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setDob(userRequestDTO.getDob());
        user.setRole(Role.USER);
        user.setPlaylistList(new ArrayList<>());

        String hashedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return toUserDTO(user);
    }

    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

        if(userUpdateDTO.getName()!=null) {
            user.setName(userUpdateDTO.getName());
        }
        if(userUpdateDTO.getEmail()!=null) {
            user.setEmail(userUpdateDTO.getEmail());
        }

        if(userUpdateDTO.getDob()!=null) {
            user.setDob(userUpdateDTO.getDob());
        }

        if(userUpdateDTO.getPassword()!=null) {
            String hashedPassword = passwordEncoder.encode(userUpdateDTO.getPassword());
            user.setPassword(hashedPassword);
        }

        userRepository.save(user);

        return toUserDTO(user);

    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

        userRepository.delete(user);
    }

    public Page<UserDTO> findByName(String name, Pageable pageable) {

        return userRepository.findByNameContainingIgnoreCase(name, pageable).map(this::toUserDTO);

    }

    @Transactional
    public PlaylistDTO createPlaylist(Long id, PlaylistRequestDTO playlistRequestDTO) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException());

        Playlist playlist = new Playlist();
        playlist.setName(playlistRequestDTO.getName());

        if(playlistRequestDTO.getDescription()!=null) {
            playlist.setDescription(playlistRequestDTO.getDescription());
        }


        playlistRepository.save(playlist);

        user.getPlaylistList().add(playlist);

        userRepository.save(user);

        return toPlaylistDTO(playlist);

    }


    public PlaylistDTO toPlaylistDTO(Playlist playlist) {
        List<SongDTO> songDTO = playlist.getSongList().stream().map(this::toSongDTO).collect(Collectors.toList());
        return new PlaylistDTO(playlist.getId(), playlist.getName(), songDTO, playlist.getDuration(), playlist.getDescription());
    }

        public SongDTO toSongDTO(Song song) {
        return new SongDTO(song.getId(),song.getTitle(),song.getNumber(),song.getDuration());
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        return toUserDTO(user);
    }

    public Page<PlaylistDTO> findPlaylistById(Long id, Pageable pageable) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        List<PlaylistDTO> playlistDTOS =  user.getPlaylistList().stream().map(this::toPlaylistDTO).collect(Collectors.toList());

        return new PageImpl<>(playlistDTOS,pageable,playlistDTOS.size());

    }
}
