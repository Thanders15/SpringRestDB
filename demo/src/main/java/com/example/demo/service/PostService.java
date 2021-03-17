package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post>getPosts(){
        return postRepository.findAllPost();
    }

    public Post getSinglePost(Long id){
        return postRepository.findById(id).orElseThrow();
    }

}
