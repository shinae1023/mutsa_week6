package week6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import week6.dto.requestDto.UserCreateRequestDto;
import week6.entity.User;
import week6.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User crateUser(UserCreateRequestDto dto) {
        User user = new User();
        user.setSocialId(dto.getSocialId());
        user.setPassword(dto.getPassword());
        user.setNickname(dto.getNickname());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getUserBySocialId(String socialId) {
        return userRepository.findBySocialId(socialId);
    }
}
