package com.yeahbutstill.kepotify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE artist SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Artist extends BaseEntity {

    @NotEmpty
    @NotBlank
    private String name;

    private String instagram;
    private String facebook;
    private String twitter;
    private String wikipedia;

    @Lob
    private String bio;

    @ManyToMany
    @JoinTable(
            name = "hasAlbum",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Album> hasAlbums;

    @ManyToMany
    @JoinTable(
            name = "perform",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "concert_id")
    )
    private Set<Concert> performs;

    @ManyToMany(mappedBy = "followArtists")
    private Set<Users> follow;

    @ManyToMany(mappedBy = "singArtists")
    private Set<Song> sing;

}