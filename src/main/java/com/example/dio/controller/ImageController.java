package com.example.dio.controller;


import com.example.dio.service.ImageService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/food-item/{foodId}/image")
    public ResponseEntity<ResponseStructur<String>> uploadImage(@PathVariable long foodId, @RequestParam("imageUrl") MultipartFile imageUrl) throws IOException {
        return ResponseBuilder.created("Image uploaded !!",
                imageService.uploadImage(imageUrl, foodId));

    }
}
