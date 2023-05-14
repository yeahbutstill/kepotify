package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "playlist_categories")
@SQLDelete(sql = "UPDATE playlist_categories SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class PlaylistCategorie extends AuditTableEntity<UUID> {

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    @Lob
    private String icon;

    @NotBlank
    @NotEmpty
    @Lob
    private String image;

    @OneToMany(mappedBy = "playlistCategories")
    @ToString.Exclude
    private List<Playlist> containPlaylists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlaylistCategorie that = (PlaylistCategorie) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
