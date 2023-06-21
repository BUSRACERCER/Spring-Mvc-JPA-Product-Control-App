package com.works.mvcdata.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    private Long pid;
    //@Lob file yı veri tabanının içinde tutabilmek için

    @Lob
    private Blob image;
}
