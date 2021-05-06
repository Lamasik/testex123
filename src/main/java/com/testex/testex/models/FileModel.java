package com.testex.testex.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class FileModel {
    private String fileName;
    private final Date uploadDate;
    private final String fileType;
    private final byte[] fileContent;
    private final long fileSize;
}
