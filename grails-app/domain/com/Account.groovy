package com

class Account {
	String name
	BigDecimal balance = 200
	String emailAddress


	static hasMany = [transactions: Transaction]

    static constraints = {
    	name()
    	balance()
    	emailAddress()
    }

    String toString() {
    	return name + " " + balance
    }
}
