package week6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String address;
    private Timestamp created_at;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;
}
