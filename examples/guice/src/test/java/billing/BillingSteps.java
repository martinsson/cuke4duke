package billing;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import com.google.inject.Inject;

import cuke4duke.annotation.EN.*;

/**
 * @author Henning Jensen
 */
public class BillingSteps {

    @Inject
    private BillingService billingService;

    private Transaction transaction;

    private CreateTransactionResponse response;

    @Given("^I have a transaction$")
    public void iHaveATransaction() {
		if(transaction != null) {
			throw new RuntimeException("Wait what? The instance is shared across scenarios?");
		}
        transaction = new Transaction("12345678", new BigDecimal("50.00"));
    }

    @When("^I send the transaction to billing$")
    public void iSendTheTransactionToBilling() {
        response = billingService.sendTransactionToBilling(transaction);
    }

    @Then("^the response should be OK$")
    public void theResponseShouldBeOK() {
        assertTrue(response.isOK());
    }

}
