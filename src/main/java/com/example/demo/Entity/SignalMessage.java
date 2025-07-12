package com.example.demo.Entity;

public class SignalMessage {
    private String type;
    private String sdp;
    private Object candidate;

    // Getters and setters
    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getSdp() {
      return sdp;
    }

    public void setSdp(String sdp) {
      this.sdp = sdp;
    }

    public Object getCandidate() {
      return candidate;
    }

    public void setCandidate(Object candidate) {
      this.candidate = candidate;
    }
  }

