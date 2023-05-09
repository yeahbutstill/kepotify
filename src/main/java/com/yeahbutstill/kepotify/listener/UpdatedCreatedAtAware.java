package com.yeahbutstill.kepotify.listener;

import java.time.LocalDateTime;

public interface UpdatedCreatedAtAware {

    void setUpdatedAt(LocalDateTime localDateTime);
    void setCreatedAt(LocalDateTime localDateTime);

}
