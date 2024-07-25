package com.habr.egribanov.geometry.dao.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.ProxyUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Evgeny Gribanov
 * @version 28.05.2024
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Version
    protected Long version;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;

    public LocalDateTime getCreatedDate() {
        return LocalDateTime.ofInstant(this.createdDate.toInstant(), ZoneId.systemDefault());
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Optional<LocalDateTime> getLastModifiedDate() {
        return this.lastModifiedDate == null ? Optional.empty() : Optional.of(
                LocalDateTime.ofInstant(this.lastModifiedDate.toInstant(), ZoneId.systemDefault())
        );
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (!this.getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        } else {
            var that = (AbstractEntity) obj;
            return this.getId() != null && this.getId().equals(that.getId());
        }
    }

    public int hashCode() {
        int hashCode = 17;
        hashCode += this.getId() == null ? 0 : this.getId().hashCode() * 31;
        return hashCode;
    }

    @PrePersist
    protected void onCreate() {
        this.createdDate = Date.from(Instant.now());
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Date.from(Instant.now());
    }
}
