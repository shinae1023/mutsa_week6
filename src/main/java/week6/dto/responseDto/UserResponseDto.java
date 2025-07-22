package week6.dto.responseDto;

import lombok.Getter;
import lombok.Setter;
import week6.entity.User;

import java.util.Optional;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String socialId;
    private String nickname;

    public UserResponseDto(User user) {
        this.id = user.getUserId();
        this.socialId = user.getSocialId();
        this.nickname = user.getNickname();
    }
}
