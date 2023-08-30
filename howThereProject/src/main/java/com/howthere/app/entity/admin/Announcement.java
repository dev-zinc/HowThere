package com.howthere.app.entity.admin;

import com.howthere.app.auditing.Period;
import com.howthere.app.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_ANNOUNCEMENT")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Announcement extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    @ToString.Exclude
    private Member admin;

    @NotNull private String announcementTitle;
    @NotNull private String announcementContent;

    public void setAdmin(Member admin) {
        this.admin = admin;
    }

    @Builder
    public Announcement(Long id, Member admin, String announcementTitle, String announcementContent) {
        this.id = id;
        this.admin = admin;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
    }
}
