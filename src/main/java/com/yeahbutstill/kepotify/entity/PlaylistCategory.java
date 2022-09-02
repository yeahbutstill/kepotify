package com.yeahbutstill.kepotify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistCategory extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String icon;

    @NotBlank
    @NotEmpty
    private String image;

    @OneToMany(mappedBy = "playlistCategory")
    private Set<Playlist> containsPlaylists;

}
