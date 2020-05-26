package com.interview.monitor.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "chat_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Message> messageList;

}
