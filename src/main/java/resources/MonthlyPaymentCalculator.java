/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.mycompany.mhtabaap.BankLoanCalculatorService;
import com.mycompany.mhtabaap.CalculationCustomException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (anualIR <= 0)
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid argument: anualIR should be greater than 0").build();
        if (months <= 0)
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid argument: months should be greater than 0").build();
        if (amount <= 0)
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid argument: amount should be greater than 0").build();

        try {
            double monthlyPayment = _bankLoanCalculatorService.calculateMonthlyPayment(anualIR, months, amount);
            return Response.status(200).entity(monthlyPayment).build();
        } catch (CalculationCustomException ex) {
            Logger.getLogger(MonthlyPaymentCalculator.class.getName()).log(Level.WARNING, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            Logger.getLogger(MonthlyPaymentCalculator.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
