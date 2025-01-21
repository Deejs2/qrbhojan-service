package com.menu.qrbhojan.common.dto;

import com.menu.qrbhojan.common.entity.FileType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileResponse {
    private String filePath;
    private FileType fileType;

    public FileResponse(String filePath, FileType fileType) {
        this.filePath = filePath;
        this.fileType = fileType;
    }
}
