package com.example.wallet.carrier.user;


public record UserTransactionRequestCarrier(String sourceUsername , String destinationUsername , Long amount) {
}
