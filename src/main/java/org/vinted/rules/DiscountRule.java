package org.vinted.rules;

import org.vinted.enitity.Transaction;

public interface DiscountRule {

     Transaction processTransaction(Transaction transaction);
}
