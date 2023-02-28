package com.alibou.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
  @JsonProperty("grant_type")
  private String grantType;

  private String email;

  String password;
  private String id;
  private String username;
  @JsonProperty("first_name")

  private String firstName;
  @JsonProperty("last_name")

  private String lastName;
  @JsonProperty("auth_date")

  private String authDate;
  @JsonProperty("photo_url")

  private String photoUrl;
  private String hash;
}
