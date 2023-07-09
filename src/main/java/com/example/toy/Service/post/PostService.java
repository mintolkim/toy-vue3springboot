package com.example.toy.Service.post;

import com.example.toy.Repository.PostRepository;
import com.example.toy.RequestForm.PostForm;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.toy.util.ServiceUtil.serviceUtilMethod;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 글 등록
    public Map<String, Object> writePost(PostForm postForm) {
        return serviceUtilMethod(
            () -> postRepository.writePost(postForm),
            "글 등록이 완료되었습니다.",
            "글 등록에 실패하였습니다."
        );
    }

    // 글 수정
    public Map<String, Object> updatePost(PostForm postForm) {
        return serviceUtilMethod(
            () -> postRepository.updatePost(postForm),
            "글 수정이 완료되었습니다.",
            "글 수정에 실패하였습니다."
        );
    }

    // 글 삭제
    public Map<String, Object> deletePost(Long postId) {
        return serviceUtilMethod(
            () -> postRepository.deletePost(postId),
            "글 삭제가 완료되었습니다.",
            "글 삭제에 실패하였습니다."
        );
    }


    // 글 조회
    public Map<String, Object> getPostList(PostForm postForm, int pageNo) {
        return serviceUtilMethod(
            () -> postRepository.getPostList(postForm, pageNo),
            "글 조회가 완료되었습니다.",
            "글 조회에 실패하였습니다."
        );
    }

    // 글 상세보기
    public Map<String, Object> viewPost(Long postId) {
        return serviceUtilMethod(
            () -> postRepository.viewPost(postId),
            "글 조회가 완료되었습니다.",
            "글 조회에 실패하였습니다."
        );
    }

    // 글 갯수
    public Map<String, Object> getPostCnt(Long userId, Long menuId) {
        return serviceUtilMethod(
                () -> postRepository.getPostCnt(userId, menuId),
                "글 조회가 완료되었습니다.",
                "글 조회에 실패하였습니다."
        );
    }
}
