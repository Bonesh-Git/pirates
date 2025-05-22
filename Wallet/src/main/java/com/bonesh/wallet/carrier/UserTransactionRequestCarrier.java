package com.bonesh.wallet.carrier;

import java.util.UUID;

public record UserTransactionRequestCarrier(String sourceUsername , String destinationUsername , Long amount) {
}
