package com.example.dio.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Image;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.ImageRepository;
import com.example.dio.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Cloudinary cloudinary;
    private final ImageRepository imageRepository;
    private final FoodItemRepository foodItemRepository;

    @Override
    @Transactional
    public String uploadImage(MultipartFile file, long foodId) throws IOException {

        FoodItem foodItem = foodItemRepository.findById(foodId)
                .orElseThrow(()-> new UserNotFoundByIdException("Food item is not found"));

        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        String url = result.get("url").toString();

        Image image =new Image();
        image.setImageUrl(url);
        image.setFoodItem(foodItem);

        imageRepository.save(image);
        return result.get("url").toString();
    }
}
