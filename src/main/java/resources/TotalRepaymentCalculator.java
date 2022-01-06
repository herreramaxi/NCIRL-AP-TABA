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
@Path("/totalrepayment")
public class TotalRepaymentCalculator {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{monthlyPayment}/{months}")
    public Response calculateTotalRepayment(@PathParam("monthlyPayment") double monthlyPayment, @PathParam("months") int months) throws Exception {
        //TODO: check how to return bad request
        if (monthlyPayment <= 0)
            throw new Exception("Invalid argument: monthlyPayment should be greater than 0");
        if (months <= 0)
            throw new Exception("Invalid argument: months should be greater than 0");

        double totalRepayment = monthlyPayment * months;

        return Response.status(200).entity(totalRepayment).build();
    }
}
