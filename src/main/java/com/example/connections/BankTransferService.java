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
    /*
    Hibernate:select bt1_0.id,
       bt1_0.value,
       bt1_0.currency_code,
       r1_0.id,
       r1_0.first_name,
       r1_0.iban,
       r1_0.last_name,
       bt1_0.reference,
       s1_0.id,
       s1_0.first_name,
       s1_0.iban,
       s1_0.last_name,
       bt1_0.state,
       bt1_0.version
from bank_transfer bt1_0
         join account r1_0 on r1_0.id = bt1_0.receiver_id
         join account s1_0 on s1_0.id = bt1_0.sender_id
where s1_0.id = ?

      very goooooooood

      why it is happened: bankTransferRepository.findBySenderId only joined to @ManyToOne Account sender to filter the returned BankTransfers;
      but these were managed and in the map every BankTransfer object got its own @ManyToOne Account receiver in a separate query.
      this caused a Nasty N+1 problem.
      But with explicit join fetch commands in the repository we asked Hibernate to join the receiver as well:

          @Query("SELECT bt FROM BankTransfer bt join fetch bt.receiver join fetch bt.sender WHERE bt.sender.id = :senderId")
          List<BankTransfer> findBySenderId(String senderId);

      this way we get the BankTransfer objects with their @ManyToOne Account receiver and sender objects in one query.

      to avoid N+1 problems it is also a ggod general rule of thumb to change the fetch type of the @ManyToOne to lazy.


     */
  }
}
