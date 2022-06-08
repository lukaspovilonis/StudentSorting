package com.example.sortingstudents.services;

import com.example.sortingstudents.dtos.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    public List<Student> readStudentsFromFile(MultipartFile multipartFile) throws IOException {

        InputStream is = multipartFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return br.lines().map(it -> {
            var lineInfo = it.split(",");
            return new Student(lineInfo[0].trim(), Double.valueOf(lineInfo[1].trim()));
        }).collect(Collectors.toList());
    }

}
