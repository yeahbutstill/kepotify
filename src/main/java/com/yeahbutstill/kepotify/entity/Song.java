package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
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
@Table(name = "songs")
public class Song extends BaseEntity {

    private String title;
    private Integer duration;

    @ManyToMany(mappedBy = "singSong")
    @ToString.Exclude
    private Set<Artist> artists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "album_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private Album album;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Song song = (Song) o;
        return getId() != null && Objects.equals(getId(), song.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
