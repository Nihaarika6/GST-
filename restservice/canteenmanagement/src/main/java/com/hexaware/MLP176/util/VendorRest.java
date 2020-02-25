package com.hexaware.MLP176.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP176.model.Vendor;
import com.hexaware.MLP176.factory.VendorFactory;
import javax.ws.rs.PathParam;

/**
 * This class provides a REST interface for the Vendor entity.
 */
@Path("/vendor")
public class VendorRest {
  /**
   * Returns Vendor details.
   * @return the Vendor details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Vendor[] listVendor() {
    final Vendor[] vendor = VendorFactory.showVendor();
    return vendor;
  }
  /**
   * Returns Vendor details.
   * @param name to get name.
   * @return the Vendor details
   */
  @GET
  @Path("{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Vendor vendorName(@PathParam("name") final String name) {
    final Vendor venName = VendorFactory.findByVendorName(name);
    return venName;
  }
   /**
   * Returns Vendor details.
   * @param user to get name.
   * @param password to get name.
   * @return the Vendor details
   */
  @GET
  @Path("/{user}/{password}")
  @Produces(MediaType.APPLICATION_JSON)
  public final int vendorID(@PathParam("user") final String user, @PathParam("password") final String password) {
    final int venName = VendorFactory.validateVendor(user, password);
    return venName;
  }
}
