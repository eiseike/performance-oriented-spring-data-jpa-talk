package com.example.connections;

import com.example.domain.BankTransferRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankTransferService {

  private final BankTransferRepository bankTransferRepository;

  public BankTransferService(BankTransferRepository bankTransferRepository) {
    this.bankTransferRepository = bankTransferRepository;
  }

  @Transactional
  public List<BankTransferDto> findBySenderId(String senderId) {
    return bankTransferRepository.findBySenderId(senderId)
        .stream()
        .map(it -> new BankTransferDto(it.getId(), it.getAmount(), it.getReceiver().getId(),
            it.getSender().getId()))
        .toList();
  }
}
