/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{anualIR}/{months}/{amount}")
    public Response calculateMonthlyPayment(@PathParam("anualIR") double anualIR, @PathParam("months") int months, @PathParam("amount") double amount) throws Exception {
        //TODO: check how to return bad request
        if (anualIR <= 0)
            throw new Exception("Invalid argument: anualIR should be greater than 0");
        if (months <= 0)
            throw new Exception("Invalid argument: months should be greater than 0");
        String output = "anualIR " + anualIR + ", months: " + months + ", amount: " + amount;
        double monthlyIR = anualIR / 100 / 12;

        double discountFactor = (Math.pow(1 + monthlyIR, months) - 1) / (monthlyIR * Math.pow(1 + monthlyIR, months));
        double monthlyPayment = amount / discountFactor;

        return Response.status(200).entity(monthlyPayment).build();
    }
}
