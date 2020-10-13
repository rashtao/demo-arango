package com.example.demo.model;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import org.springframework.data.annotation.Id;

@Edge
public class EdgeModel {

    @Id
    private String id;

    @From
    private DocumentModel from;

    @To
    private DocumentModel to;

    private Byte edgeType;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentModel getFrom() {
        return from;
    }

    public void setFrom(DocumentModel from) {
        this.from = from;
    }

    public DocumentModel getTo() {
        return to;
    }

    public void setTo(DocumentModel to) {
        this.to = to;
    }

    public Byte getEdgeType() {
        return edgeType;
    }

    public void setEdgeType(Byte edgeType) {
        this.edgeType = edgeType;
    }
}
