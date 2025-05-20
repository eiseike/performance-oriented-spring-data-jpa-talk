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
            Hibernate: select bt1_0.id,
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
                         left join account r1_0 on r1_0.id = bt1_0.receiver_id
                         left join account s1_0 on s1_0.id = bt1_0.sender_id
                where bt1_0.id = ?
Hibernate: update bank_transfer set value=?,currency_code=?,receiver_id=?,reference=?,sender_id=?,state=?,version=? where id=? and version=?

             Why the join? we only changed bankTransfer's state.
             Because to being managed by Hibernate, the entity must be in the persistence context.
             For that every *To* relation must be handled, and BankTransfer has two Account field
              annotated with @ManyToOne, which is eager by default.
             */
        });
    }
}
