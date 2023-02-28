package com.alibou.security.config;

import com.alibou.security.auth.TelegramAuthToken;
import com.alibou.security.auth.WidgetAuthValidator;
import com.google.gson.JsonObject;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;


public class TelegramAuthenticationProvider implements AuthenticationProvider {
    private final WidgetAuthValidator widgetAuthValidator;

    public TelegramAuthenticationProvider(WidgetAuthValidator widgetAuthValidator) {
        this.widgetAuthValidator = widgetAuthValidator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JsonObject details = (JsonObject) authentication.getDetails();
        if (widgetAuthValidator.isValidRequest(details)){
            authentication.setAuthenticated(true);
        }else {
            throw new BadCredentialsException("Bad credentials");
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(TelegramAuthToken.class);
    }
}
