package com

import com.icegreen.greenmail.util.*

class GatewayService {

	
	def transferFundsAndSendEmail(Account sender, Account receiver, BigDecimal transferAmount) {
		
	    sender.balance = sender.balance - transferAmount
		receiver.balance = receiver.balance + transferAmount
		
		sender.save(flush:true)
		receiver.save(flush:true)
		
		
		def now = new Date(System.currentTimeMillis());
		
		Transaction creditTx = new Transaction(creditor: receiver.name, debtor: sender.name, amount: transferAmount, transactionDate: now, account: sender ).save(failOnError: true)

		Transaction debtorTx = new Transaction(creditor: sender.name , debtor: receiver.name , amount: transferAmount, transactionDate: now, account: receiver ).save(failOnError: true)

		//TODO:i18n
		def senderMessage = "Your " + sender.name + " a/c has been debited by " + transferAmount + " as you transfered it to " + receiver.name
		//TODO:i18n
		def receiverMessage = "Your " + receiver.name + " a/c has been credited by " + transferAmount + " as " + sender.name + " made a funds transfer to your a/c"

		sendEmailToSenderAndReceiver(sender.emailAddress, senderMessage, receiver.emailAddress, receiverMessage)
	  }
	
	 def sendEmailToSenderAndReceiver(senderEmail, senderMessage, receiverEmail, receiverMessage) {
		 GreenMail email = new GreenMail();
		 email.start();
		 //TODO:i18n
		 GreenMailUtil.sendTextEmailTest(receiverEmail, senderEmail, "Your Account has been Debited",
										 senderMessage); // --- Place your sending code here
		 //TODO:i18n
		 GreenMailUtil.sendTextEmailTest(senderEmail, receiverEmail, "Your Account has been Credited",
										 receiverMessage); // --- Place your sending code here
		 email.stop();
 
	 }
}
