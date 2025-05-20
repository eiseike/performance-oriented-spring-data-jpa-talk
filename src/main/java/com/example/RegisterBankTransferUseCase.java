package com.example;

import com.example.domain.Account;
import com.example.domain.AccountRepository;
import com.example.domain.Amount;
import com.example.domain.BankTransfer;
import com.example.domain.BankTransferRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterBankTransferUseCase {
    private final AccountRepository accountRepository;
    private final BankTransferRepository bankTransferRepository;

    public RegisterBankTransferUseCase(AccountRepository accountRepository, BankTransferRepository bankTransferRepository) {
        this.accountRepository = accountRepository;
        this.bankTransferRepository = bankTransferRepository;
    }

    @Transactional
    public void execute(String bankTransferId, String reference, String senderId, String receiverId, Amount amount) {
        Account sender = accountRepository.findByIdOrThrow(senderId);
        Account receiver = accountRepository.findByIdOrThrow(receiverId);

        BankTransfer bankTransfer = new BankTransfer(bankTransferId, reference, sender, receiver, amount);
        bankTransferRepository.save(bankTransfer);

        /*

        Transactional : the Accounts being managed after findByIdOrThrow returns
        Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select a1_0.id,a1_0.first_name,a1_0.iban,a1_0.last_name from account a1_0 where a1_0.id=?
Hibernate: select bt1_0.id,bt1_0.value,bt1_0.currency_code,r1_0.id,r1_0.first_name,r1_0.iban,r1_0.last_name,bt1_0.reference,s1_0.id,s1_0.first_name,s1_0.iban,s1_0.last_name,bt1_0.state from bank_transfer bt1_0 left join account r1_0 on r1_0.id=bt1_0.receiver_id left join account s1_0 on s1_0.id=bt1_0.sender_id where bt1_0.id=?
Hibernate: insert into bank_transfer (value,currency_code,receiver_id,reference,sender_id,state,id) values (?,?,?,?,?,?,?)

        is baaaaaad
         */
    }
}
