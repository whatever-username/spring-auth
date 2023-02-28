package com.alibou.security.auth;

import com.google.gson.JsonObject;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class WidgetAuthValidator {
    @Value("${app.bot-token}")
    private String botToken;
    public boolean isValidRequest(JsonObject params) {
        String hash = params.remove("hash").getAsString();
        String str = params.entrySet().stream()
            .filter(en -> !en.getValue().isJsonNull())
            .sorted(Map.Entry.comparingByKey())
            .map(en -> en.getKey() + "=" + en.getValue().getAsString()).collect(Collectors.joining("\n"));
        return calculateHash(str).equals(hash);
    }

    @SneakyThrows
    private String calculateHash(String data) {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        sha256_HMAC.init(new SecretKeySpec(MessageDigest.getInstance("SHA-256").digest(botToken.getBytes(StandardCharsets.UTF_8)), "SHA256"));
        byte[] encoded = sha256_HMAC.doFinal(data.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : encoded) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
