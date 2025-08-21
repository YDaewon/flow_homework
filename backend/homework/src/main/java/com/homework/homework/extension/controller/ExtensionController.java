package com.homework.homework.extension.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.homework.common.response.BaseResponse;

@RestController
@RequestMapping("/api")
public class ExtensionController {
    
    @GetMapping("/test")
    public ResponseEntity<BaseResponse<String>> test(){
        return ResponseEntity.ok(new BaseResponse<>("test!!"));
    }

    @GetMapping("/extensions")
    public ResponseEntity<BaseResponse<String>> getExtensions() {
        return ResponseEntity.ok(new BaseResponse<>("확장자 목록 불러오기"));
    }
}
