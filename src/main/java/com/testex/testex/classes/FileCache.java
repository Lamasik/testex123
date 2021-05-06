package com.testex.testex.classes;

import com.testex.testex.models.FileModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class FileCache {

    private static FileCache instance;
    // fileName -> fileModel
    private ConcurrentHashMap<String, FileModel> files = new ConcurrentHashMap<>();

    private FileCache() {}

    public static FileCache getInstance() {

        if (Objects.isNull(instance)) {
            instance = new FileCache();
        }

        return instance;
    }

    public boolean storeNewFile(FileModel fileModel) {
        log.info("Added new file: {}", fileModel);
        // добавляем новый файл в наш кэш
        files.put(fileModel.getFileName(), fileModel);
        return true;
    }

    public FileModel getFileModelByFileName(String fileName) {
        FileModel fileModel = files.get(fileName);
        log.info("Searched file: {}", fileModel);
        return fileModel;
    }

    public List<FileModel> getAllFiles() {
        List<FileModel> fileModels = new ArrayList<>();
        files.forEach((fileName, fileModel) -> {
            fileModels.add(fileModel);
        });
        return fileModels;
    }

    public boolean deleteFileByName(String fileName) {
        log.info("Deleted file by name: {}", fileName);
        files.remove(fileName);
        return true;
    }

    public boolean updateFileByFileName(String fileName, String newFileName) {
        log.info("Update file fileName: {} newFileName: {}", fileName, newFileName);
        FileModel fileModel = files.get(fileName);

        fileModel.setFileName(newFileName);

        files.put(fileModel.getFileName(), fileModel);

        return true;
    }
}

//@Slf4j
//class test {
//    public static void main(String[] args) {
//       FileCache cache = FileCache.getInstance();
//       cache.storeNewFile(new FileModel("fileName.txt", new Date(), "txt", new byte[0], 1L));
//       FileModel fileModel = cache.getFileModelByFileName("fileName.txt");
//       log.info(String.valueOf(fileModel));
//    }
//}