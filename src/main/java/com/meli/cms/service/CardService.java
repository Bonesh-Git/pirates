package com.meli.cms.service;

import com.meli.cms.entity.Card;
import com.meli.cms.entity.CardType;
import com.meli.cms.repository.CardRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CardService {

    private final CardRepository cardRepository;
    private final CustomerService customerService;
    private final BranchService branchService;
    private final DepositService depositService;


    public CardService(CardRepository cardRepository, CustomerService customerService, BranchService branchService, DepositService depositService) {
        this.cardRepository = cardRepository;
        this.customerService = customerService;
        this.branchService = branchService;
        this.depositService = depositService;
    }

    public void createCard(UUID customerId, CardType cardType, UUID branchId, UUID depositId) {


        String cvv2 = String.format("%03d", (int) (Math.random() * 1000));
        String expYear = String.valueOf(LocalDate.now().getYear() + 5);
        String expMonth = String.valueOf(LocalDate.now().getMonthValue());


        if (cardType.equals(CardType.DEBIT)) {
            int type = 100;
            String serial = "10000";

            cardRepository.save(new Card(type, serial, cvv2, expMonth, expYear,
                    customerService.getCustomer(customerId), branchService.getBranch(branchId), depositService.getDeposit(depositId)));

        } else if (cardType.equals(CardType.GIFT)) {
            int type = 200;
            String serial = "20000";

            cardRepository.save(new Card(type, serial, cvv2, expMonth, expYear,
                    customerService.getCustomer(customerId), branchService.getBranch(branchId), depositService.getDeposit(depositId)));
        } else throw new RuntimeException("Invalid card type");
    }
}