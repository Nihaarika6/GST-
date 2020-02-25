package com.hexaware.MLP176.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// import com.hexaware.MLP176.model.Menu;
import com.hexaware.MLP176.factory.OrderFactory;
import com.hexaware.MLP176.model.Orders;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/Orders")
public class OrderRest {
  /**
   * Returns Order details.
   * @return the Order details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Orders[] listOrders() {
    final Orders[] order = OrderFactory.showOrder();
    return order;
  }
  /**
   * Returns Orders details.
   * @param custid to get name.
   * @return the Order details
   */
  @GET
  @Path("/{custid}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Orders[] showCustomerHis(@PathParam("custid") final int custid) {
    final Orders[] order = OrderFactory.showCustomerHistory(custid);
    if (order == null) {
      throw new NotFoundException("No Such customerId : " + custid);
    }
    return order;
  }
  /**
   * Returns Vendor details.
   * @param vendorId to get name.
   * @return the Vendor details
   */
  @GET
  @Path("/Vendor/{vendorId}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Orders[] showvendor(@PathParam("vendorId") final int vendorId) {
    final Orders[] venid = OrderFactory.showVendorHistory(vendorId);
    if (venid == null) {
      throw new NotFoundException("No Such vendorId : " + vendorId);
    }
    return venid;
  }
 /**
   * Returns Order details.
   * @return the Order details.
   * @param id for pending vendors.
   */
  @GET
  @Path("/pendingven/{id}")
  @Produces(MediaType.APPLICATION_JSON)
   public final Orders[] orderTestById(@PathParam("id") final int id) {
    final Orders[] order = OrderFactory.showpendingVendorOrders(id);
    if (order == null) {
      throw new NotFoundException("No such Vendor ID: " + id);
    }
    return order;
  }
  /**
   * Returns Order details.
   * @param orderId for cancel order.
   * @param custId for customer id.
   * @param status for status.
   * @return the cancelled order details
   */
  @POST
  @Path("CancelOrder/{orderId}/{custId}/{status}")
  @Produces(MediaType.APPLICATION_JSON)
  public final String cancelOrder(@PathParam("orderId") final int orderId, @PathParam("custId") final int custId,
                                  @PathParam("status") final String status) {
    String result = OrderFactory.cancelOrder(orderId, custId, status);
    return result;
  }
  /**
   * Returns Order details.
   * @return the Order details.
   * @param id for pending customers
   */
  @GET
  @Path("/pendingcus/{id}")
  @Produces(MediaType.APPLICATION_JSON)
   public final Orders[] orderListById(@PathParam("id") final int id) {
    final Orders[] order = OrderFactory.showpendingCustomerOrders(id);
    if (order == null) {
      throw new NotFoundException("No such Customer ID: " + id);
    }
    return order;
  }
  /**
   * Approve or Deny order.
   * @return the Order details.
   * @param orderId for place order object.
   * @param vendorId for vendor id.
   * @param status for status
   */
  @POST
  @Path("/acceptOrRejectOrder/{orderId}/{vendorId}/{status}")
  @Produces(MediaType.APPLICATION_JSON)
   public final String acceptOrRejectOrder(@PathParam("orderId") final int orderId,
        @PathParam("vendorId") final int vendorId, @PathParam("status") final String status) {
    String result = OrderFactory.acceptOrRejectOrder(orderId, vendorId, status);
    return result;
  }
   /**
   * Returns Menu details.
   * @param order for cancel order.
   * @return the cancelled order details
   */
  @POST
  @Path("/placeOrder")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String placeorder(final Orders order) {
    String result = OrderFactory.placeOrder(order);
    return result;
  }
}



