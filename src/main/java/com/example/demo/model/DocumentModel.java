package com.example.demo.model;

import com.arangodb.springframework.annotation.PersistentIndex;
import org.springframework.data.annotation.Id;

@PersistentIndex(fields = {"uuid"}, unique = true)
public class DocumentModel {

    @Id
    private String id;

    private String uuid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
