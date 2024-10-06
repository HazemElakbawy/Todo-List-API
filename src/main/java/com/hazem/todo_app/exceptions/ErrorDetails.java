package com.hazem.todo_app.exceptions;

import java.util.Date;

public class ErrorDetails {
  private Date timestamp;
  private String message;
  private String details;
  private int status;

  public ErrorDetails(Date timestamp, String message, String details, int status) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
    this.status = status;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}