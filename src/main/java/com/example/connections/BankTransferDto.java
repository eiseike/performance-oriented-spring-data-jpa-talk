package com.example.connections;

import com.example.domain.Amount;

public class BankTransferDto {

  private String id;
  private String senderId;
  private String receiverId;
  private Amount amount;

  public BankTransferDto(String id, Amount amount, String senderId, String receiverId) {
    this.id = id;
    this.amount = amount;
    this.senderId = senderId;
    this.receiverId = receiverId;
  }

  public String getId() {
    return id;
  }

  public String getSenderId() {
    return senderId;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public Amount getAmount() {
    return amount;
  }

}
