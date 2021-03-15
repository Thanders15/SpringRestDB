package com.example.demo.controller;

import com.example.demo.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @GetMapping("/posts")
    public List<Post> getPosts(){
        throw new IllegalArgumentException("Not implemented!");
    }

    @GetMapping("/singlePost")
    public List<Post> getSinglePost(){
        throw new IllegalArgumentException("Not implemented!");
    }
}
