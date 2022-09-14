package com.barisyenigun.ToDo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "username",unique = true,nullable = false)
    private String username;
    @Column(name = "password",unique = true,nullable = false)
    private String password;
}
