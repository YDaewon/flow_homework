package com.homework.homework.common.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExtNormalizer {

    private static final Pattern EXT_PATTERN = Pattern.compile("^[a-z]+$");

    public String checkExtension(String ext) {
        if (ext == null) return null;

        String cleaned = ext.trim().toLowerCase();
        cleaned = cleaned.replaceAll("[^a-z]", "");

        log.info("입력값: " + ext + " , 정리된 값: " + cleaned);

        return EXT_PATTERN.matcher(cleaned).matches() ? cleaned : null;
    }
}
