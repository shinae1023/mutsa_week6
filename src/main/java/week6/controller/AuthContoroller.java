//package week6.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import week6.dto.responseDto.UserResponseDto;
//import week6.entity.CustomOAuth2User;
//import week6.entity.User;
//import week6.service.UserService;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping("api/auth")
//public class AuthContoroller {
//    private final UserService userService;
//
//    @GetMapping("/me")
//    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal CustomOAuth2User customOAuth2User) {
//        log.info("GET /api/auth/me 요청 수신");
//        if (customOAuth2User == null) {
//            log.warn("/api/auth/me 요청 - 인증되지 않은 사용자 접근 시도");
//            // SecurityConfig에서 .anyRequest().authenticated() 설정 덕분에
//            // 이 블록은 일반적으로 실행되지 않고 401 Unauthorized가 먼저 발생해야 합니다.
//            // 하지만 방어적으로 코드를 작성합니다.
//            return ResponseEntity.status(401).body(null); // 또는 적절한 에러 DTO 반환
//        }
//
//        User user = customOAuth2User.getUser(); // CustomOAuth2User에서 실제 User 엔티티 추출
//
//        log.info("인증된 사용자 정보 조회 성공: {}", user.getNickname());
//
//        // 사용자 정보를 담을 DTO 생성 및 반환
//        // UserResponseDto가 없다면 User 엔티티 자체를 반환하거나 Map<String, Object>으로 구성하여 반환할 수 있습니다.
//        // 여기서는 UserResponseDto가 있다고 가정합니다.
//        UserResponseDto responseDto = new UserResponseDto(user);
//
//        return ResponseEntity.ok(responseDto);
//    }
//}
