package com.letaotao.services.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.BitSet;

@Data
@Entity
@Table(name = "block_logs")
public class BlockModel {
    @Id
    @Column(name = "id")
    private int id;

    private String start;
    private String end;

    @Lob
    private byte[] result;

    private String status;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
