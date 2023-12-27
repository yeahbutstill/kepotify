package com.yeahbutstill.kepotify.entity;

import com.yeahbutstill.kepotify.helpers.YearAttributeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "albums")
@SQLDelete(sql = "UPDATE albums SET status_record='INACTIVE' WHERE id=?")
@SQLRestriction(value = "status_record='ACTIVE'")
public class Album extends AuditTableEntity<UUID> {

    private String title;

    @Lob
    @Column(columnDefinition = "text")
    private String image;

    @Convert(converter = YearAttributeConverter.class)
    private Year release;

    @ManyToMany(mappedBy = "hasAlbums")
    @ToString.Exclude
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album")
    @ToString.Exclude
    private List<Song> songs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Album album = (Album) o;
        return getId() != null && Objects.equals(getId(), album.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}