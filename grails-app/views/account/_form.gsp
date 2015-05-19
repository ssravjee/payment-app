<%@ page import="com.Account" %>



<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="account.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${accountInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'balance', 'error')} required">
	<label for="balance">
		<g:message code="account.balance.label" default="Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="balance" required="" value="${fieldValue(bean: accountInstance, field: 'balance')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'emailAddress', 'error')} ">
	<label for="emailAddress">
		<g:message code="account.emailAddress.label" default="Email Address" />
		
	</label>
	<g:textField name="emailAddress" value="${accountInstance?.emailAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'transactions', 'error')} ">
	<label for="transactions">
		<g:message code="account.transactions.label" default="Transactions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${accountInstance?.transactions?}" var="t">
    <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="transaction" action="create" params="['account.id': accountInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>
</li>
</ul>

</div>

