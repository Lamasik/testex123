package com.testex.testex.controllers;

import com.testex.testex.classes.FileCache;
import com.testex.testex.models.FileModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    private final FileCache fileCache;

    @Autowired
    public FileController(FileCache fileCache) {
        this.fileCache = fileCache;
    }

    /**
     * GET - получение по id/ список /files
     * POST - создание новое сущности/ удалением (редко) /files (+ данные сохраняемой сущности)
     * PUT/PATCH - изменение  сущестуетсвующей сущности /files/{id} + данные сущности
     * DELETE - удаление по id /files/{id}
     */

    @GetMapping
    public List<FileModel> getAllFiles() {
        return fileCache.getAllFiles();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void storeNewFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        fileCache.storeNewFile(
                new FileModel(
                        multipartFile.getOriginalFilename(),
                        new Date(),
                        multipartFile.getContentType(),
                        multipartFile.getBytes(),
                        multipartFile.getSize()
                )
        );
    }

    @PutMapping("/{file}")
    public boolean updateFileByFile(@PathVariable String file,
                                        @RequestParam("file") String newFile) {

       return fileCache.updateFileByFileName(file, newFile);
    }

    @DeleteMapping("/{fileName}")
    public boolean deleteFileByFileName(@PathVariable String fileName) {
        return fileCache.deleteFileByName(fileName);
    }

}
/*
*  @RequestMapping(value = "/files/{file_name:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        // Прежде всего стоит проверить, если необходимо, авторизован ли пользователь и имеет достаточно прав на скачивание файла. Если нет, то выбрасываем здесь Exception

        //Авторизованные пользователи смогут скачать файл
        Path file = Paths.get(PathUtil.getUploadedFolder(), fileName);
        if (Files.exists(file)){
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/vnd.ms-excel");

            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                LOG.info("Error writing file to output stream. Filename was '{}'" + fileName, e);
                throw new RuntimeException("IOError writing file to output stream");
            }
        }
    }*/