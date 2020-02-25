package com.hexaware.MLP176.model;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.text.ParseException;
//import mockit.integration.junit4.JMockit;
import java.util.ArrayList;

import com.hexaware.MLP176.factory.MenuFactory;

// import com.hexaware.MLP176.model.Menu;
// import com.hexaware.MLP176.model.MenuCat;
// import com.hexaware.MLP176.model.Vendor;

//package com.hexaware.MLP176.model;

import com.hexaware.MLP176.persistence.MenuDAO;

import org.junit.Before;
//import org.junit.runner.RunWith;

//import com.hexaware.MLP176.model.MenuCat;

import org.junit.Test;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;

/**
 * Test class for Menu.
 */
//@RunWith(JMockit.class)
public class MenuTest {
    /**
   * setup method.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
   public final void testEquals() {
    Menu m = new Menu();
    Menu m1 = null;
    Menu m2 = new Menu(1, MenuCat.VEG, "MASAL DOSA", 1, 60, 101, "***");
    Menu m3 = new Menu(1, MenuCat.VEG, "MASAL DOSA", 1, 60, 101, "***");
    assertFalse(m2.equals(m1));
    assertTrue(m2.equals(m3));
    assertEquals(m2.hashCode(), m3.hashCode());
    Vendor vendor = new Vendor();
    assertFalse(m2.equals(vendor));
    assertFalse(m2.equals(m));
  }
   /**
   * Tests the toString() methods of the Menu class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Menu m = new Menu(1, MenuCat.VEG, "MASAL DOSA", 1, 60, 101, "***");
    String result = String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s",
          m.getMenuId(), m.getMenuCat(), m.getMenuItem(), m.getMenuQuantity(),
          m.getMenuCost(), m.getMenuCalories(), m.getMenuReviews());
    assertEquals(result, m.toString());
  }
   /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testMenu() {
    Menu menu = new Menu();
    menu.setMenuId(1);
    assertEquals(1, menu.getMenuId());
    menu.setMenuCat(MenuCat.VEG);
    assertEquals(MenuCat.VEG, menu.getMenuCat());
    menu.setMenuItem("MASAL DOSA");
    assertEquals("MASAL DOSA", menu.getMenuItem());
    menu.setMenuQuantity(1);
    assertEquals(1, menu.getMenuQuantity());
    menu.setMenuCost(40);
    assertEquals(40, menu.getMenuCost());
    menu.setMenuCalories(90);
    assertEquals(90, menu.getMenuCalories());
    menu.setMenuReviews("***");
    assertEquals("***", menu.getMenuReviews());
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */

  @Test
  public final void testListAllSome(@Mocked final MenuDAO dao) {
    final Menu m2 = new Menu(101, MenuCat.VEG, "PODI DOSA", 1, 80, 105, "**");
    final Menu m3 = new Menu(102, MenuCat.NONVEG, "Biriyani", 1, 150, 110, "****");
    final ArrayList<Menu> mn = new ArrayList<Menu>();
    mn.add(m2);
    mn.add(m3);
    new Expectations() {
      {
        dao.show(); result = mn;
      }
    };
    new MockUp<MenuFactory>() {
      @Mock
      MenuDAO dao() {
        return dao;
      }
    };
    Menu[] mn1 = MenuFactory.showMenu();
    assertEquals(2, mn1.length);
    assertEquals(101, mn1[0].getMenuId());
    assertEquals(102, mn1[1].getMenuId());
    assertEquals(MenuCat.VEG, mn1[0].getMenuCat());
    assertEquals(MenuCat.NONVEG, mn1[1].getMenuCat());
    assertEquals("PODI DOSA", mn1[0].getMenuItem());
    assertEquals("Biriyani", mn1[1].getMenuItem());
    assertEquals(80, mn1[0].getMenuCost(), 0);
    assertEquals(150, mn1[1].getMenuCost(), 0);
  }
}
