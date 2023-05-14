package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
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
@Table(name = "podcasts")
@SQLDelete(sql = "UPDATE podcasts SET status_record='INACTIVE' WHERE id=?")
@Where(clause = "status_record='ACTIVE'")
public class Podcast extends AuditTableEntity<UUID> {

    @NotBlank
    @NotEmpty
    private String name;

    @Lob
    private String about;

    @Lob
    private String image;

    @OneToMany(mappedBy = "podcast")
    @ToString.Exclude
    private List<Episode> episodes;

    @ManyToOne
    @JoinColumn(name = "podcast_categories_id", referencedColumnName = "id")
    private PodcastCategories podcastCategories;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userPodcasts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Podcast podcast = (Podcast) o;
        return getId() != null && Objects.equals(getId(), podcast.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
