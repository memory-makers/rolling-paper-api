package com.memorymakerpeople.memoryrollingpaper.sticker.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // new 클래스() 제한
@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
@Entity
@DynamicInsert // null로 들어가야 하는 경우 대응(기본값)
@DynamicUpdate // 변경한 필드만 대응
@Table(name = "tbl_sticker")
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stickerId;
    private int stickerSize;
    private String type;
    private float rotate;
    private float x;
    private float y;
    private float scale;
    private Long paperId;

    @Builder
    public Sticker(Long stickerId, int stickerSize, String type, float rotate, float x, float y, float scale, Long paperId) {
        this.stickerId = stickerId;
        this.stickerSize = stickerSize;
        this.type = type;
        this.rotate = rotate;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.paperId = paperId;
    }

    public StickerRes GetStickerRes() {
        return new StickerRes.StickerResBuilder().
                stickerId(this.stickerId).
                stickerSize(this.stickerSize).
                type(this.type).
                rotate(this.rotate).
                x(this.x).
                y(this.y).
                scale(this.scale).
                paperId(this.paperId).
                build();
    }
}