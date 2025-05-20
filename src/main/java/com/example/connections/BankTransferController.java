package com.example.connections;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankTransferController {

    private final BankTransferService bankTransferService;

    public BankTransferController(BankTransferService bankTransferService) {
        this.bankTransferService = bankTransferService;
    }

    @GetMapping("/transfer/{id}")
    void transfer(@PathVariable String id) {
        List<BankTransferDto> bySenderId = bankTransferService.findBySenderId(id);
    }
}
