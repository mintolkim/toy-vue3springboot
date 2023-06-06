package com.example.toy.Service.post;

import com.example.toy.Repository.PostRepository;
import com.example.toy.RequestForm.PostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 글 등록
    public Map<String, Object> writePost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    // 글 수정
    public Map<String, Object> updatePost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    // 글 삭제
    public Map<String, Object> deletePost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }


    // 글 조회
    public Map<String, Object> listPost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }
}
