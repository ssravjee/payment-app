package com

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.dao.DataIntegrityViolationException

import com.icegreen.greenmail.util.*

class AccountController {
	def gatewayService
	 
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
        //redirect(action: "pay", params: params)
    }

    def pay() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [accountInstanceList: Account.list(params), accountInstanceTotal: Account.count()]
    }

    def fundsTransfer() {
		Account sender = Account.get(params.fromAccount as Integer)
		Account receiver = Account.get(params.toAccount as Integer)
		//Validate user input
		if(! params.transferAmount.isNumber()){
			flash.message = message(code: 'typeMismatch.java.math.BigDecimal', args: [params.transferAmount])
			redirect(action: "Pay", params: params)
			return
		}
		//convert to BigDecimal
		BigDecimal balanceTransfer = params.transferAmount.toBigDecimal()
		
		if(balanceTransfer > sender.balance) {
			flash.message = message(code: 'transaction.not.successful', args: [message(code: 'account.label', default: 'Account'), params.fromAccount])
			redirect(action: "Pay", params: params)
			return
		}
		GreenMail greenMail = new GreenMail(); //uses test ports by default
		gatewayService.transferFundsAndSendEmail(sender, receiver, balanceTransfer, greenMail)
		flash.message = message(code: 'received.funds.transfer.request', args: [balanceTransfer, sender.name, receiver.name])
		redirect(action: "Pay",  model: [accountInstance: sender])
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [accountInstanceList: Account.list(params), accountInstanceTotal: Account.count()]
    }

    def create() {
        [accountInstance: new Account(params)]
    }

    def save() {
        def accountInstance = new Account(params)
        if (!accountInstance.save(flush: true)) {
            render(view: "create", model: [accountInstance: accountInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'account.label', default: 'Account'), accountInstance.id])
        redirect(action: "show", id: accountInstance.id)
    }

    def show() {
		println("entered account / show action ")

        def accountInstance = Account.get(params.id)
        if (!accountInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])
            redirect(action: "list")
            return
        }

        [accountInstance: accountInstance]
	
    }

	
	def showtransactions() {
		println "entered show transcations action"
		def accountInstance = Account.get(params.id)
		if (!accountInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])
			redirect(action: "list")
			return
		}
		
		def c = Transaction.findAllByAccount(accountInstance)
		
		render(view: "transactions", model: [transactionInstanceList: c, transactionInstanceTotal: c.size(), account: accountInstance])
	}
	
    def edit() {
        def accountInstance = Account.get(params.id)
        if (!accountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])
            redirect(action: "list")
            return
        }

        [accountInstance: accountInstance]
    }

    def update() {
        def accountInstance = Account.get(params.id)
        if (!accountInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (accountInstance.version > version) {
                accountInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'account.label', default: 'Account')] as Object[],
                          "Another user has updated this Account while you were editing")
                render(view: "edit", model: [accountInstance: accountInstance])
                return
            }
        }

        accountInstance.properties = params

        if (!accountInstance.save(flush: true)) {
            render(view: "edit", model: [accountInstance: accountInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'account.label', default: 'Account'), accountInstance.id])
        redirect(action: "show", id: accountInstance.id)
    }

    def delete() {
        def accountInstance = Account.get(params.id)
        if (!accountInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'account.label', default: 'Account'), params.id])
            redirect(action: "list")
            return
        }

        try {
            accountInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'account.label', default: 'Account'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'account.label', default: 'Account'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
