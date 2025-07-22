package week6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import week6.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialIdAndSocialType(String socialId, Enum socialType);

    boolean existsBySocialIdAndSocialType(String socialId, Enum socialType);

    Optional<User> findBySocialId(String socialId);
}
