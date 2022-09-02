package com.yeahbutstill.kepotify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE users SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Users extends BaseEntity {

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    private LocalDate birthday;

    @NotBlank
    @NotEmpty
    private String password;

    @ManyToMany
    @JoinTable(
            name = "followArtists",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> followArtists;

    @ManyToMany
    @JoinTable(
            name = "followPlaylists",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private Set<Playlist> followPlaylists;

    @ManyToMany
    @JoinTable(
            name = "likeSongs",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> likeSongs;

    @OneToMany(mappedBy = "users")
    private Set<Playlist> createPlaylists;

    @OneToMany(mappedBy = "users")
    private Set<Podcast> createPodcasts;

}
