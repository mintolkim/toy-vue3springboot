package com.example.toy.Service.post;

import com.example.toy.Repository.PostRepository;
import com.example.toy.RequestForm.PostForm;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 글 등록
    public Map<String, Object> writePost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            postRepository.writePost(postForm);
            result.put("status", "200");
            result.put("message", "글 등록이 완료되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "글 등록에 실패하였습니다.");
        }

        return result;
    }

    // 글 수정
    public Map<String, Object> updatePost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            postRepository.updatePost(postForm);
            result.put("status", "200");
            result.put("message", "글 수정이 완료되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "글 수정에 실패하였습니다.");
        }
        return result;
    }

    // 글 삭제
    public Map<String, Object> deletePost(PostForm postForm) {
        Map<String, Object> result = new HashMap<>();
        try {
            postRepository.deletePost(postForm);
            result.put("status", "200");
            result.put("message", "글 삭제가 완료되었습니다.");
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "글 삭제에 실패하였습니다.");
        }
        return result;
    }


    // 글 조회
    public Map<String, Object> getPostList(UserForm userForm, int pageNo) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<PostForm> postFormList = postRepository.getPostList(userForm, pageNo);
            result.put("status", "200");
            result.put("result", postFormList);
        } catch (Exception e) {
            result.put("status", "500");
            result.put("message", "글 조회에 실패하였습니다.");
        }
        return result;
    }

}
