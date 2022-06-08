package com.example.sortingstudents.controllers;

import com.example.sortingstudents.enums.SortEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HtmlController {


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/sortingAlgorithms")
    @ResponseBody
    public List<SortEnum> getSortingAlgorithms() {
        return List.of(SortEnum.values());
    }
}
