package com.howthere.app.entity.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(schema = "TBL_HOUSE_FILE")
@Getter @ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseFile extends FileEntity {
    //@ManyToOne(fetch = FetchType.LAZY)
    //private House house;
    private boolean thumb = Boolean.FALSE;
}
