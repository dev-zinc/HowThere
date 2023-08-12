package com.howthere.app.entity.admin;

import com.howthere.app.auditing.Period;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @NotNull private String announcementTitle;
    @NotNull private String announcementContent;

    @Builder
    public Announcement(Long id, @NotNull String announcementTitle, @NotNull String announcementContent) {
        this.id = id;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
    }
}
