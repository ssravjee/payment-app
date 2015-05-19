<%@ page import="com.Account" %>
<!doctype html>
<html>
<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'payment.page.lable', default: 'Account')}" />
<title>Pay Some Person</title>
</head>
<body>
		<a href="#list-account" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<!-- 
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				-->
			</ul>
		</div>
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
	<h2>Pay some Person</h2>
	<br/>
	</div>
<g:form action="fundsTransfer" method="post">

<table>
<tr>
	<td>From:</td>
	<td>
		<g:select optionKey="id" optionValue="name"
        name="fromAccount" from="${accountInstanceList}" />
    </td>
</tr>
<tr>        
	<td>To: </td>
	<td><g:select optionKey="id" optionValue="name"
          name="toAccount" from="${accountInstanceList}" />
    </td>
</tr>          

<tr>
	<td>Amount:</td> 
	<td><g:textField name="transferAmount" value="" /></td>
</tr>
<tr>
	<td><g:submitButton value="Pay" name="submit"/>
	</td>
</tr>
</table>
</g:form>

</body>
</html>