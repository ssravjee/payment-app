
<%@ page import="com.Transaction" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-transaction" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-transaction" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list transaction">
			
				<g:if test="${transactionInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="transaction.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="account" action="show" id="${transactionInstance?.account?.id}">${transactionInstance?.account?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.amount}">
				<li class="fieldcontain">
					<span id="amount-label" class="property-label"><g:message code="transaction.amount.label" default="Amount" /></span>
					
						<span class="property-value" aria-labelledby="amount-label"><g:fieldValue bean="${transactionInstance}" field="amount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.creditor}">
				<li class="fieldcontain">
					<span id="creditor-label" class="property-label"><g:message code="transaction.creditor.label" default="Creditor" /></span>
					
						<span class="property-value" aria-labelledby="creditor-label"><g:fieldValue bean="${transactionInstance}" field="creditor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.debtor}">
				<li class="fieldcontain">
					<span id="debtor-label" class="property-label"><g:message code="transaction.debtor.label" default="Debtor" /></span>
					
						<span class="property-value" aria-labelledby="debtor-label"><g:fieldValue bean="${transactionInstance}" field="debtor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${transactionInstance?.transactionDate}">
				<li class="fieldcontain">
					<span id="transactionDate-label" class="property-label"><g:message code="transaction.transactionDate.label" default="Transaction Date" /></span>
					
						<span class="property-value" aria-labelledby="transactionDate-label"><g:formatDate date="${transactionInstance?.transactionDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${transactionInstance?.id}" />
					<g:link class="edit" action="edit" id="${transactionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
