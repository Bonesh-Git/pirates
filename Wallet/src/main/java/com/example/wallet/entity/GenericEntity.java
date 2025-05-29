package com.example.wallet.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;


import java.util.UUID;

@Document
public abstract class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private UUID id;
    @Field
    private String createdBy = "ADMIN";
    @Field
    private String lastModifiedBy = "ADMIN";
    @Version
    private Long version;

    public GenericEntity(String createdBy, String lastModifiedBy) {
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;

    }

    public GenericEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }


}
