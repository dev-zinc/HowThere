package com.howthere.app.domain.rent;

import com.howthere.app.entity.rent.RentCar;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Data
@Component
@ToString
@NoArgsConstructor
public class RentCarFileDTO {
    private Long id;
    @NotNull
    private String filePath;
    @NotNull private String fileUuid; //"0000-0000-0000-0000.png"
    @NotNull private String fileName;
    @NotNull private Long fileSize;

    @Builder

    public RentCarFileDTO(Long id, @NotNull String filePath, @NotNull String fileUuid, @NotNull String fileName, @NotNull Long fileSize) {
        this.id = id;
        this.filePath = filePath;
        this.fileUuid = fileUuid;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
}
