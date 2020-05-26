package com.interview.monitor.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
