package com.menu.qrbhojan.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class CafeSpecialIdGenerator {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "dhirajpasangutsabchitra"; // 16-char secret key

    public static String encrypt(String data) {
        try{
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            String base64Encrypted = Base64.getEncoder().encodeToString(encryptedBytes);
            // Ensure the output is 15 characters long
            return adjustLength(base64Encrypted, 15);
        }catch (Exception e){
            log.error("Error while encrypting data: {}", e.getMessage());
            return null;
        }
    }

    public String decrypt(String encryptedData) {
        try{
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Ensure the encrypted data is properly padded back to its original form
            String paddedData = adjustLength(encryptedData, 24); // Base64 padding
            byte[] decodedBytes = Base64.getDecoder().decode(paddedData);
            byte[] originalBytes = cipher.doFinal(decodedBytes);

            return new String(originalBytes);
        }catch (Exception e){
            log.error("Error while decrypting data: {}", e.getMessage());
            return null;
        }
    }

    private static String adjustLength(String input, int length) {
        if (input.length() > length) {
            return input.substring(0, length); // Truncate
        } else {
            // Pad with extra characters
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < length) {
                sb.append('X'); // Add padding character
            }
            return sb.toString();
        }
    }
    public String generateCafeSpecialId(String cafeName, Long cafeId, String cafeOwnerName) {
        return encrypt(cafeName + "-" + cafeId + "-" + cafeOwnerName);
    }

}
