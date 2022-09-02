package com.yeahbutstill.kepotify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Song extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String title;

    @NotBlank
    @NotEmpty
    private Integer duration;

    @ManyToMany
    @JoinTable(
            name = "contains",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
    private Set<Playlist> containsPlaylists;

    @ManyToMany
    @JoinTable(
            name = "sing",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> singArtists;

    @ManyToMany(mappedBy = "likeSongs")
    private Set<Users> users;
    @ManyToOne
    @JoinColumn(name = "album_id")
    @NotBlank
    @NotEmpty
    private Album album;

}
