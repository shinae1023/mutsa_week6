package week6.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import week6.dto.KakaoUserInfo;
import week6.entity.CustomOAuth2User;
import week6.entity.Role;
import week6.entity.SocialType;
import week6.entity.User;
import week6.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static week6.entity.Role.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        log.info("카카오 OAuth2 로그인 시작");

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("카카오 사용자 정보 : {}", oAuth2User.getAttributes());

        String socialId = oAuth2User.getName();
        Map<String,Object> attributes = oAuth2User.getAttributes();

        log.info("socialId: {}", socialId);

        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(attributes);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + USER.name()));

        User user = saveOrUpdate(kakaoUserInfo);

        return new CustomOAuth2User(user,attributes,authorities);
    }

    private User saveOrUpdate(KakaoUserInfo kakaoUserInfo){
        Optional<User> existingUser = userRepository.findBySocialIdAndSocialType(
                kakaoUserInfo.getId(), SocialType.KAKAO);

        if (existingUser.isPresent()) {
            // 기존 사용자라면 정보 업데이트
            User user = existingUser.get();
            user.updateNickname(kakaoUserInfo.getNickname());
            return userRepository.save(user);
        } else {
            // 신규 사용자라면 회원가입
            User newUser = User.builder()
                    .nickname(kakaoUserInfo.getNickname())
                    .socialId(kakaoUserInfo.getId())
                    .email(kakaoUserInfo.getEmail())
                    .socialType(SocialType.KAKAO)
                    .role(USER)  // 기본 권한
                    .build();

            return userRepository.save(newUser);
        }
    }
}
