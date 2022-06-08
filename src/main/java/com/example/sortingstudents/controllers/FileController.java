package com.example.sortingstudents.controllers;


import com.example.sortingstudents.dtos.Student;
import com.example.sortingstudents.enums.SortEnum;
import com.example.sortingstudents.services.FileService;
import com.example.sortingstudents.services.Sorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    Sorting sorting;

    @PostMapping("/uploadFiles")
    @ResponseBody
    public List<Student> uploadFiles(@RequestParam("files") MultipartFile files, @RequestParam("sortingAlgorithm") SortEnum sortingAlgorithm) throws IOException {

        List<Student> students = fileService.readStudentsFromFile(files);

        sorting.sort(students, sortingAlgorithm);

        return students;
    }
}
