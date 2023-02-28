package com.alibou.security.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
public class TelegramAuthToken extends AbstractAuthenticationToken {

    public TelegramAuthToken() {
        super(null);
    }

    public TelegramAuthToken(String id,
                             String username,
                             String firstName,
                             String lastName,
                             String authDate,
                             String photoUrl,
                             String hash) {
        super(null);
        JsonObject details = new JsonObject();
        details.addProperty("id", id);
        details.addProperty("username", username);
        details.addProperty("first_name", firstName);
        details.addProperty("username", username);
        details.addProperty("last_name", lastName);
        details.addProperty("auth_date", authDate);
        details.addProperty("photo_url", photoUrl);
        details.addProperty("hash", hash);
        this.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return ((JsonObject) this.getDetails()).get("id");
    }


}
