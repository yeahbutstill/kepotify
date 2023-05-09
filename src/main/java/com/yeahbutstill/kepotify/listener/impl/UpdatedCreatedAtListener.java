package com.yeahbutstill.kepotify.listener.impl;

import com.yeahbutstill.kepotify.listener.UpdatedCreatedAtAware;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class UpdatedCreatedAtListener {

    @PreUpdate
    public void setLastUpdatedAt(UpdatedCreatedAtAware object) {
        object.setUpdatedAt(LocalDateTime.now());
    }

    @PrePersist
    public void setLastCreatedAt(UpdatedCreatedAtAware object) {
        object.setCreatedAt(LocalDateTime.now());
    }

}
