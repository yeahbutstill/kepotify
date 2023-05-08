package com.yeahbutstill.kepotify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
//@SQLDelete(sql = "UPDATE users SET status_record='INACTIVE' WHERE id=?")
//@Where(clause = "status_record='ACTIVE'")
public class Users extends BaseEntity {

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    private LocalDate birthday;

    @NotBlank
    @NotEmpty
    private String password;

    @ManyToMany
    @JoinTable(
            name = "follows",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private Set<Artist> followArtist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Users users = (Users) o;
        return getId() != null && Objects.equals(getId(), users.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
