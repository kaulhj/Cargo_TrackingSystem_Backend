package com.example.cah.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 Mysql에 테이블이 생성이 된다.
public class User {

    
    //ORM은 JAVA(다른언어 포함 오브젝트를 테이블로 매핑해주는 기술
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략이 프로젝트에서 연결된 db의 넘버링 전략을 따라간다. squence table등등
    private int userId; // 시퀀스, auto_increment

    @Column(nullable = false,length = 20,unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password; //비밀번호

    @Column(nullable = false, length=50)
    private String email; //springphysical naming 전략은 카멜표기법을 언더바 전략으로 변환해줌

    @CreationTimestamp //시간이 자동으로 입력 //save될때 타임스탬프 넣어서 넣어준다.
    private Timestamp createDate;

    //db는 roll타입이 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum 쓰는게 좋다. 어떤 데이터의 도메인생성가능, // ADMIN, USER

    @Column
    private String phoneNum; //비밀번호

    @Column
    private String carNum; //비밀번호





}
