import com.Account

class BootStrap {

    def init = { servletContext ->
    	// Check whether the test data already exists.
    	if(!Account.count()) {
    	new Account(name: "CITI Bank", balance: 200.00, emailAddress: "myaccount@citibank.com").save(failOnError: true)
    	new Account(name: "ICICI Bank", balance: 200.00, emailAddress: "myaccount@icicibank.com").save(failOnError: true)
    	new Account(name: "HSBC Bank", balance: 200.00, emailAddress: "myaccount@hsbcbank.com").save(failOnError: true)
    	new Account(name: "Santander Bank", balance: 200.00, emailAddress: "myaccount@Santanderbank.com").save(failOnError: true)
    	new Account(name: "Hong Kong Bank", balance: 200.00, emailAddress: "myaccount@hongkongbank.com").save(failOnError: true)
	    }
    }
    def destroy = {
    }
}
