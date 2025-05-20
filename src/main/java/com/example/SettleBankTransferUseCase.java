package com.example;

import com.example.domain.BankTransfer;
import com.example.domain.BankTransferRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class SettleBankTransferUseCase {
    private final BankTransferRepository bankTransferRepository;
    private final TransactionTemplate transactionTemplate;

    public SettleBankTransferUseCase(BankTransferRepository bankTransferRepository,
        TransactionTemplate transactionTemplate) {
        this.bankTransferRepository = bankTransferRepository;
        this.transactionTemplate = transactionTemplate;
    }


    public void execute(String bankTransferId) {
        transactionTemplate.executeWithoutResult(s -> {
            BankTransfer bankTransfer = bankTransferRepository.findByIdOrThrow(bankTransferId);
            bankTransfer.settle();
            bankTransferRepository.save(bankTransfer);
            /*
            Hibernate: select bt1_0.id,bt1_0.value,bt1_0.currency_code,bt1_0.receiver_id,bt1_0.reference,bt1_0.sender_id,bt1_0.state,bt1_0.version from bank_transfer bt1_0 where bt1_0.id=?
            Hibernate: update bank_transfer set state=?,version=? where id=? and version=?

            With @DynamicUpdate Hibernate will only update the changed fields in the database.
             */
        });
    }
}
