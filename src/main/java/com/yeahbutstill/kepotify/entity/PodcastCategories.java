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
@Table(name = "podcast_categories")
@SQLDelete(sql = "UPDATE podcast_categories SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class PodcastCategories extends AuditTableEntity<UUID> {

    @NotBlank
    @NotEmpty
    private String name;

    @NotEmpty
    @NotBlank
    @Lob
    private String icon;

    @NotEmpty
    @NotBlank
    @Lob
    private String image;

    @OneToMany(mappedBy = "podcastCategories")
    @ToString.Exclude
    private List<Podcast> containsPodcasts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PodcastCategories that = (PodcastCategories) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
