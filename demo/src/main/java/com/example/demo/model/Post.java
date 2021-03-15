package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Getter
@Setter
@Entity
public class Post {

    @Id
    private BigInteger id;
    private String title;
    private String content;
}
