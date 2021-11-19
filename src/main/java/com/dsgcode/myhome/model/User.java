package com.dsgcode.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;

    @ManyToMany()
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();       // NPE 제외를 위해 ArrayList로

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)   // board - user 서로 갖기, orphanRemoval = true가 된 애들은 지운다!
    private List<Board> boards = new ArrayList<>();

}
