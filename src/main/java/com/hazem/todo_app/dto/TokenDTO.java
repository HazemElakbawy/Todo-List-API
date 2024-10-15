package com.hazem.todo_app.dto;

public class TokenDTO {
  private String refreshToken;
  private String accessToken;

  public TokenDTO(String refreshToken, String accessToken) {
    this.refreshToken = refreshToken;
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
