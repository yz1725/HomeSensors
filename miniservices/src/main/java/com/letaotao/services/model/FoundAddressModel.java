package com.letaotao.services.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "found_addresses")
public class FoundAddressModel {
    @Id
    @Column(name = "id")
    private int id;

    private String privateKey;
    private String publicAddress;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
