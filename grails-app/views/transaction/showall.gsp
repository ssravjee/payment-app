<%@ page import="com.Account" %>
<%@ page import="com.Transaction" %>

<!doctype html>
<html>
<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'payment.page.lable', default: 'Account')}" />
<title>See transactions</title>
</head>
<body>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${accountInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${accountInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>

<div>
<h2>Transaction History</h2>
<br/>
<g:form action="transactions">
<b>Person:</b>
		<g:select optionKey="id" optionValue="name"
        name="selectedAccount" from="${accountInstanceList}" />

<b><g:submitButton value="Show All" name="submit"/></b>
</g:form>
</div>
</body>
</html>