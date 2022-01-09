/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.mycompany.mhtabaap.BankLoanCalculatorService;
import com.mycompany.mhtabaap.CalculatorServiceResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Maximiliano Herrera
 */
@Path("/monthlypayment")
public class MonthlyPaymentCalculator {

    private static final BankLoanCalculatorService _bankLoanCalculatorService = BankLoanCalculatorService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{anualIR}/{months}/{amount}")
    public Response calculateMonthlyPayment(@PathParam("anualIR") double anualIR, @PathParam("months") int months, @PathParam("amount") double amount) {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(anualIR, months, amount);
        int statusCode = response.isSuccessful() ? 200 : 400;

        return Response.status(statusCode).entity(response).build();
    }
}