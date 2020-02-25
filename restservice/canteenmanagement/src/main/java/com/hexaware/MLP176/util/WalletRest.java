package com.hexaware.MLP176.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP176.model.Wallet;
import com.hexaware.MLP176.factory.WalletFactory;
import javax.ws.rs.PathParam;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/wallet")
public class WalletRest {
  /**
   * Returns Menu details.
   * @return the menu details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Wallet[] listWallet() {
    final Wallet[] wallet = WalletFactory.showWallet();
    return wallet;
  }
/**
   * Returns Wallet details.
   * @return the wallet details.
   * @param id for wallet.
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Wallet[] findByWalletId(@PathParam("id") final int id) {
    final Wallet[] wallet = WalletFactory.showWallet(id);
    return wallet;
  }
}
