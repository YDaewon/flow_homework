package com.homework.homework.extension.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.homework.common.response.BaseResponse;
import com.homework.homework.extension.entity.Extension;
import com.homework.homework.extension.entity.dto.CustomExtensionDto;
import com.homework.homework.extension.entity.dto.ToggleActiveDto;
import com.homework.homework.extension.service.ExtensionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExtensionController {

    private final ExtensionService extensionService;
    
    @GetMapping("/extensions")
    public ResponseEntity<BaseResponse<List<Extension>>> getExtensions() {
        List<Extension> extensions = extensionService.getAllExtensions();
        return ResponseEntity.ok(new BaseResponse<>(extensions));
    }
    
    @PatchMapping("/extension")
    public ResponseEntity<BaseResponse<Extension>> toggleExtension(@RequestBody ToggleActiveDto dto) {
        Extension extension = extensionService.toggleExtension(dto);
        return ResponseEntity.ok(new BaseResponse<>(extension));
    }
    
    @PostMapping("/extension")
    public ResponseEntity<BaseResponse<Extension>> createExtension(@RequestBody CustomExtensionDto dto) {
        Extension extension = extensionService.createExtension(dto);
        return ResponseEntity.ok(new BaseResponse<>(extension));
    }

    @DeleteMapping("/extension")
    public ResponseEntity<BaseResponse<Extension>> deleteExtension(@RequestBody CustomExtensionDto dto){
        Extension extension = extensionService.deleteExtension(dto);
        return ResponseEntity.ok(new BaseResponse<>(extension));
    }
}
