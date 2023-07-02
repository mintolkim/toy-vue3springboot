package com.example.toy.Service.user;

import com.example.toy.Entity.User;
import com.example.toy.Repository.UserRepository;
import com.example.toy.RequestForm.UserForm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import static com.example.toy.util.ServiceUtil.serviceUtilMethod;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Value("${file.upload-dir}")
    private String profileImgPath;

    // 회원 가입
    public Map<String, Object> Join(UserForm userForm){
        return serviceUtilMethod(
            () -> userRepository.join(userForm),
            "회원가입이 완료되었습니다.",
            "회원가입에 실패했습니다."
        );
    }

    // 로그인
    public Map<String, Object> login(UserForm userForm){
        return serviceUtilMethod(
            () -> userRepository.login(userForm),
            "로그인에 성공하였습니다. ",
            "로그인에 실패하였습니다."
        );
    }


    // 아이디 중복체크
    public String idUnique(UserForm userForm) {
        Boolean idUnique = userRepository.isIdUnique(userForm);
        String result = idUnique ? "사용 가능한 아이디입니다." : "이미 사용중인 아이디입니다.";
        return result;
    }

    // 회원 탈퇴
    public Map<String, Object> deleteUser(UserForm userForm) {
        return serviceUtilMethod(
            () -> userRepository.deleteUser(userForm),
            "회원탈퇴가 정상적으로 되었습니다.",
            "회원탈퇴에 실패했습니다."
        );
    }

    // 유저 프로필 업로드
    public Map<String, Object> uploadProfileImg(Long id, MultipartFile profileImg) throws IOException {
        String userImg;
        if (!profileImg.isEmpty()) {
            String fileName = StringUtils.cleanPath(profileImg.getOriginalFilename());
            Path path = Paths.get(profileImgPath + fileName);
            Files.copy(profileImg.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            userImg = profileImgPath + fileName;
        } else {
            userImg = "user.png";
        }
        return serviceUtilMethod(
                () -> userRepository.uploadProfileImg(id, userImg),
                "유저 프로필 사진을 등록했습니다.",
                "유저 프로필 사진 등록에 실패했습니다."
        );
    }

    // jpql 로 사용자 찾기
    public Map<String, Object> findUserJpql(String username) {
        return serviceUtilMethod(
                () -> userRepository.findUserJpql(username),
                "유저 아이디를 성공적으로 가져왔습니다.",
                "유저 아이디 조회를 실패했습니다."
        );
    }
}
