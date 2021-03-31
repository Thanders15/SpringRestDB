package SpringDB.service;


import SpringDB.model.Post;
import SpringDB.repository.PostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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


    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postToEdit = postRepository.findById(post.getId()).orElseThrow();
        postToEdit.setTitle(post.getTitle());
        postToEdit.setContent(post.getContent());
        return postToEdit;
    }

    public void deletePost(Long id) {
         postRepository.deleteById(id);
    }
}
