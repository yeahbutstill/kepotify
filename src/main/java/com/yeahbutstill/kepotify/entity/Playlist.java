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
public class Playlist extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @Lob
    private String description;

    @NotBlank
    @NotEmpty
    private String image;

    @ManyToMany(mappedBy = "followPlaylists")
    private Set<Users> follow;

    @ManyToMany(mappedBy = "containsPlaylists")
    private Set<Song> contains;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @NotBlank
    @NotEmpty
    private Users users;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PlaylistCategory playlistCategory;

}
