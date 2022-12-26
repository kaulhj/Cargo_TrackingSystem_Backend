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
public class getPositionAndName {


    @Column
    private Integer positionIdx;

    @Column
    private String username;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column
    private String weather;

    @Column
    private String etc;

    @Column
    private String baggage;

    @CreationTimestamp
    private Timestamp createdAt;

    @CreationTimestamp
    private Timestamp updatedAt;

    @Column
    private Integer pointNum;

    @Column
    private int measureId;

    @Column
    private int endTag;

    @Column
    private String coordinate;

}
