package com

class Transaction {

	String debtor
	String creditor
	BigDecimal amount
	Date transactionDate

	static belongsTo = [account: Account]

	static constraints = {
    }

    String toString() {
    	return "debtor : " + debtor + " creditor " + creditor + " £" + amount + " - transaction Date  : " + transactionDate
    }
}
