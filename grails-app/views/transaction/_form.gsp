<%@ page import="com.Transaction" %>



<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="transaction.account.label" default="Account" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="account" name="account.id" from="${com.Account.list()}" optionKey="id" required="" value="${transactionInstance?.account?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="transaction.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="amount" required="" value="${fieldValue(bean: transactionInstance, field: 'amount')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'creditor', 'error')} ">
	<label for="creditor">
		<g:message code="transaction.creditor.label" default="Creditor" />
		
	</label>
	<g:textField name="creditor" value="${transactionInstance?.creditor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'debtor', 'error')} ">
	<label for="debtor">
		<g:message code="transaction.debtor.label" default="Debtor" />
		
	</label>
	<g:textField name="debtor" value="${transactionInstance?.debtor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: transactionInstance, field: 'transactionDate', 'error')} required">
	<label for="transactionDate">
		<g:message code="transaction.transactionDate.label" default="Transaction Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="transactionDate" precision="day"  value="${transactionInstance?.transactionDate}"  />
</div>

