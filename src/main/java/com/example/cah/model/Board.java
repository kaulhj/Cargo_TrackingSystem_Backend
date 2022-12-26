//package com.example.cah.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class Board {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
//    private int id;
//
//    @Column(nullable = false,length = 100)
//    private String title;
//
//    @Lob //대용량 데이터 시 사용
//    private String content; //섬머노트라는 라이브러리 <html>태그가 섞여서 디자인이 됨
//
//    @ColumnDefault("0")
//    private int count; //조회수
//
//    @ManyToOne(fetch = FetchType.EAGER) //바로 무조건 가져온다.//many는 보드, 유저는 one , 한명의 유저는 여러개의 게시글 쓸 수 있다.
//    @JoinColumn(name = "userId")
//    private User user; //db는 오브젝트를 저장할 수 없다. fk, 자바는 오브젝트를 저장할 수 있다.
//
//    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //필요 하면 들고오는것// 연관관계주인이 아니다, fk가 아니다, db에 컬럼을 만들지 않는다.reply테이블에 fk가 생긴다.
//    private List<Reply> reply;
//
//    @CreationTimestamp //인서트 혹은 업데이트시 현재시간 자동 들어옴
//    private Timestamp createDate;
//
//
//}
