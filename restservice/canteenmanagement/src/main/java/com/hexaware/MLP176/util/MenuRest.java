package com.hexaware.MLP176.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP176.model.Menu;
import com.hexaware.MLP176.factory.MenuFactory;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/menu")
public class MenuRest {
  /**
   * Returns Menu details.
   * @return the menu details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Menu[] listMenu() {
    final Menu[] menu = MenuFactory.showMenu();
    return menu;
  }
  /**
   * Returns Menu details.
   * @param menuId for reading menu details.
   * @return the menu details.
   */
  @GET
  @Path("/{menuId}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Menu listMenu(@PathParam("menuId") final int menuId) {
    final Menu menu = MenuFactory.showMenu(menuId);
    return menu;
  }
}
