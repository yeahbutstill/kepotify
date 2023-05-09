package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "playlists")
public class Playlist extends BaseEntity {

    @NotBlank
    @NotEmpty
    private String name;

    @Lob
    private String description;

    @NotBlank
    @NotEmpty
    private String image;

    @ManyToMany(mappedBy = "followPlaylist")
    @ToString.Exclude
    private Set<Users> follow;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Playlist playlist = (Playlist) o;
        return getId() != null && Objects.equals(getId(), playlist.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
