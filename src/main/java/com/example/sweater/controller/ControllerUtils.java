package com.example.sweater.controller;

import com.example.sweater.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ControllerUtils {
    @Value("${upload.path}")
    private String uploadPath;

    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> errorMapCollector = Collectors.toMap(
                result -> result.getField() + "Error", FieldError::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(errorMapCollector);
    }

    void saveImage(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
            message.setFilename(resultFileName);
        }
    }
}
