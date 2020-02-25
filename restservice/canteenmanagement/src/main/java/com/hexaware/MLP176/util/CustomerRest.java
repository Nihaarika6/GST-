package com.hexaware.MLP176.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP176.model.Customer;
import com.hexaware.MLP176.factory.CustomerFactory;
import javax.ws.rs.PathParam;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/customer")
public class CustomerRest {
  /**
   * Returns Customer details.
   * @return the Customer details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Customer[] listCustomer() {
    final Customer[] customer = CustomerFactory.showCustomer();
    return customer;
  }
  /**
   * Returns Customer details.
   * @param customerName to get name.
   * @return the Customer details
   */
  @GET
  @Path("/{customerName}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Customer customerName(@PathParam("customerName") final String customerName) {
    final Customer cusName = CustomerFactory.findByCustomerName(customerName);
    return cusName;
  }
   /**
   * Returns Customer details.
   * @param user to get name.
   * @param password to get name.
   * @return the Customer details
   */
  @GET
  @Path("{user}/{password}")
  @Produces(MediaType.APPLICATION_JSON)
  public final int validateCustomer(@PathParam("user") final String user, @PathParam("password") final String password) {
    final int cusName = CustomerFactory.validateCustomer(user, password);
    return cusName;
  }
}

