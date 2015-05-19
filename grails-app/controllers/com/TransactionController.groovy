package com

import org.springframework.dao.DataIntegrityViolationException

class TransactionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def showall() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [accountInstanceList: Account.list(params), accountInstanceTotal: Account.count() ]
    }

    def transactions() {
        def accountId = params.selectedAccount
        def account = Account.get(accountId)
        def c = Transaction.findAllByAccount(account)
        
        render(view: "transactions", model: [transactionInstanceList: c, transactionInstanceTotal: c.size(), account: account])
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [transactionInstanceList: Transaction.list(params), transactionInstanceTotal: Transaction.count()]
    }

    def create() {
        [transactionInstance: new Transaction(params)]
    }

    def save() {
        def transactionInstance = new Transaction(params)
        if (!transactionInstance.save(flush: true)) {
            render(view: "create", model: [transactionInstance: transactionInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
        redirect(action: "show", id: transactionInstance.id)
    }

    def show() {
        def transactionInstance = Transaction.get(params.id)
        if (!transactionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect(action: "list")
            return
        }

        [transactionInstance: transactionInstance]
    }

    def edit() {
        def transactionInstance = Transaction.get(params.id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect(action: "list")
            return
        }

        [transactionInstance: transactionInstance]
    }

    def update() {
        def transactionInstance = Transaction.get(params.id)
        if (!transactionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (transactionInstance.version > version) {
                transactionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'transaction.label', default: 'Transaction')] as Object[],
                          "Another user has updated this Transaction while you were editing")
                render(view: "edit", model: [transactionInstance: transactionInstance])
                return
            }
        }

        transactionInstance.properties = params

        if (!transactionInstance.save(flush: true)) {
            render(view: "edit", model: [transactionInstance: transactionInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'transaction.label', default: 'Transaction'), transactionInstance.id])
        redirect(action: "show", id: transactionInstance.id)
    }

    def delete() {
        def transactionInstance = Transaction.get(params.id)
        if (!transactionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect(action: "list")
            return
        }

        try {
            transactionInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'transaction.label', default: 'Transaction'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
