//package com.example.cah.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class Reply {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = false,length = 200)
//    private String content;
//
//    @ManyToOne // 여러 답변은 하나의 게시글에 쓰여질 수 있다.
//    @JoinColumn(name = "boardId")
//    private Board board;
//
//    @ManyToOne
//    @JoinColumn(name="userId")
//    private User user;
//
//    @CreationTimestamp
//    private Timestamp createDate;
//}
