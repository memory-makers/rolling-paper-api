package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DynamicUpdate // 변경한 필드만 대응
@Table(name = "tbl_sticker")
@Builder
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stickerId;
    private int stickerSize;
    private String type;
    private float rotate;
    private float x;
    private float y;
    private float scale;
    private int paperId;

}

//1.postStickerRequestDto를 만들어서 파라미터로 전체 다 복사하여 전달(Builder 패턴 사용)
//2.postStickerRequestDto를 만들어서 파라미터로 private Sticker sticker; 객체를 전달
//3.Sticker 자체를 파라미터로 전달