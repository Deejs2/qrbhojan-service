package com.menu.qrbhojan.utils;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ImageResponse {
    private ImageResponse() {}
     public static String setImageUrl(String baseUrl, String imagePath) {
        if (imagePath != null) {
            // Replace the system's file separator with "&&&"
            String safeImagePath = imagePath.replace(File.separator, "&&&");
            // URL encode the "&&&" to "%26%26%26"
            String encodedImagePath = URLEncoder.encode(safeImagePath, StandardCharsets.UTF_8);
            // Replace the encoded "&&&" directly with "%26%26%26" if necessary
            encodedImagePath = encodedImagePath.replace("%26%26%26", "&&&");
            // Concatenate the base URL with the modified image path
            return baseUrl + "/getImage/" + encodedImagePath;
        }
        return null;
    }
}
