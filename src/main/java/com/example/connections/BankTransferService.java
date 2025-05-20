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
       bt1_0.receiver_id,
       bt1_0.reference,
       bt1_0.sender_id,
       bt1_0.state,
       bt1_0.version
from bank_transfer bt1_0
         left join account s1_0 on s1_0.id = bt1_0.sender_id
where s1_0.id = ?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?

      very baaaaaaaaaad

      why it is happened: bankTransferRepository.findBySenderId only joins to @ManyToOne Account sender to filter the returned BankTransfers;
      but these are managed and in the map every BankTransfer object gets its own @ManyToOne Account receiver in a separate query.
      It is a Nasty N+1 problem.

     */
  }
}
