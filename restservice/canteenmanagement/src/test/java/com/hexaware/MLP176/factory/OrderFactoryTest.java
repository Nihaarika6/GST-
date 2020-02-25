package com.hexaware.MLP176.factory;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hexaware.MLP176.model.Menu;
import com.hexaware.MLP176.model.MenuCat;
import com.hexaware.MLP176.model.OrderStatus;
import com.hexaware.MLP176.model.Orders;
import com.hexaware.MLP176.model.Wallet;
import com.hexaware.MLP176.model.WalletType;
import com.hexaware.MLP176.persistence.OrderDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
/**
 * Test class for order.
 */
@RunWith(JMockit.class)
public class OrderFactoryTest {
  /**
   * tests for constructor.
   */
  @Test
  public final void testConstructor() {
    assertNotNull(new OrderFactory());
    assertNotNull(OrderFactory.dao());
  }
  /**
   * tests that empty order list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllEmpty(@Mocked final OrderDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Orders>();
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    Orders[] o = OrderFactory.showOrder();
    assertEquals(0, o.length);
  }
   /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException for date format validation.
   */
  @Test
   public final void testListAllSome(@Mocked final OrderDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-03-18");
    Date odt = sdf.parse(dt);
    final Orders o2 = new Orders(1, 100, 4, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM,2,2);
    final Orders o3 = new Orders(2, 101, 5, 2, OrderStatus.REJECTED, "SPICY", 201.00000, odt, 5, WalletType.GOOGLE_PAY,3,3);

    final ArrayList<Orders> ven = new ArrayList<Orders>();
    new Expectations() {
      {
        ven.add(o2);
        ven.add(o3);
        dao.show(); result = ven;
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    // (1, 100, 4, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM);
    // (2, 101, 5, 2, OrderStatus.REJECTED, "SPICY", 201.00000, odt, 5, WalletType.GOOGLE_PAY);

    Orders[] ord1 = OrderFactory.showOrder();
    assertEquals(2, ord1.length);
    assertEquals(1, ord1[0].getOrderId());
    assertEquals(2, ord1[1].getOrderId());
    assertEquals(100, ord1[0].getCustomerId());
    assertEquals(101, ord1[1].getCustomerId());
    assertEquals(4, ord1[0].getVendorId());
    assertEquals(5, ord1[1].getVendorId());
    assertEquals(1, ord1[0].getMenuId());
    assertEquals(2, ord1[1].getMenuId());
    assertEquals(OrderStatus.ACCEPTED, ord1[0].getOrderStatus());
    assertEquals(OrderStatus.REJECTED, ord1[1].getOrderStatus());
    assertEquals("SWEET", ord1[0].getOrderComments());
    assertEquals("SPICY", ord1[1].getOrderComments());
    assertEquals(200.00000, ord1[0].getOrderTotalamount(), 0);
    assertEquals(201.00000, ord1[1].getOrderTotalamount(), 0);
    assertEquals(odt, ord1[0].getOrderDate());
    assertEquals(odt, ord1[1].getOrderDate());
    assertEquals(4, ord1[0].getOrderQuantity());
    assertEquals(5, ord1[1].getOrderQuantity());
    assertEquals(WalletType.PAYTM, ord1[0].getWalletType());
    assertEquals(WalletType.GOOGLE_PAY, ord1[1].getWalletType());
    // assertEquals("9999977654", ord1[0].getOrderNumber());
    // assertEquals("9988877654", ord1[1].getOrderNumber());
    // assertEquals("XYZ@GMAIL.COM", ord1[0].getOrderEmail());
    // assertEquals("RKP@GMAIL.COM", ord1[1].getOrderEmail());
    // assertEquals("512", ord1[0].getOrderPassword());
    // assertEquals("507", ord1[1].getOrderPassword());
  }
     /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testAcceptOrReject(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-03-18");
    final Date odt = sdf.parse(dt);
    final Orders order1 = new Orders(1, 100, 4, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM,4.7,6.9);
    new Expectations() {
        {
          dao.findByOrderId(1); result = order1;
          dao.findByOrderId(2); result = null;
          dao.acceptOrReject("ACCEPTED", 1);
          dao.acceptOrReject("REJECTED", 1);
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    String result1 = OrderFactory.acceptOrRejectOrder(1, 4, "ACCEPTED");
    assertEquals(result1, "Order Accepted Successfully...");
    String result2 = OrderFactory.acceptOrRejectOrder(1, 4, "REJECTED");
    assertEquals(result2, "Order Cancelled Successfully and amount refunded to PAYTM");
    String result3 = OrderFactory.acceptOrRejectOrder(1, 5, "REJECTED");
    assertEquals(result3, "You are Unauthorised Vendor for this User");
    String result4 = OrderFactory.acceptOrRejectOrder(2, 4, "REJECTED");
    assertEquals(result4, "Invalid OrderId...");
  }
   /**
  * @param dao for mocking cancleorder Mock Test.
  * @throws ParseException for converting date to string.
   */
  @Test
  public final void testcancelOrder(@Mocked final OrderDAO dao) throws ParseException {
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String dt = new String("2020-03-18");
    final Date odt = sdf.parse(dt);
    final Orders order2 = new Orders(12, 4, 100, 1, OrderStatus.PENDING, "SWEET", 200.00000, odt, 4, WalletType.PAYTM,4.7,6.9);
    final Orders order3 = new Orders(22, 6, 102, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM,4.5,5.2);
    //final Orders order4 = new Orders(10, 5, 101, 1, OrderStatus.PENDING, "SWEET", 2000.00000, odt, 5, WalletType.PAYTM);
    //final Orders order3 = new Orders(2, 4, 100, 1, OrderStatus.ACCEPTED, "SWEET", 200.00000, odt, 4, WalletType.PAYTM);
    new Expectations() {
        {
          dao.findByOrderId(12); result = order2;
          dao.findByOrderId(22); result = order3;
          //dao.findByOrderId(1001); result = null;
          // //dao.acceptOrReject("CANCELLED", 22);
          dao.acceptOrReject("REJECTED", 12);
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    String result5 = OrderFactory.cancelOrder(12, 4, "YES");
    assertEquals(result5, "Order Cancelled Successfully and amount refunded to PAYTM");
    String result6 = OrderFactory.cancelOrder(22, 6, "YES");
    assertEquals(result6, "You cannot cancel this order...");
    // String result7 = OrderFactory.cancelOrder(1001, 1, "YES");
    // assertEquals(result7, "Invalid OrderId...");
    String result8 = OrderFactory.cancelOrder(12, 7, "YES");
    assertEquals(result8, "You are Unauthorised");
  }
/**
  * @param dao for mocking PlaceOrder Mock Test.
  * @throws ParseException for converting date to string.
   */
  @Test
  public final void testplaceOrder(@Mocked final OrderDAO dao) throws ParseException {
    final Menu m2 = new Menu(101, MenuCat.VEG, "PODI DOSA", 1, 80, 105, "**");
    //final Menu m3 = new Menu(102, MenuCat.NONVEG, "Biryani", 1, 150, 110, "****");
    // final Menu m2 = new Menu(10, "Biryani", 120.0, "Non-Veg", MenStatus.AVAILABLE, 4);
    // final Menu m3 = new Menu(20, "Curd Rice", 60.0, "Veg", MenStatus.AVAILABLE, 5);
    //final Wallet w1 = new Wallet(1, WalletType.CREDIT_CARD, 1500.45, 100);
   // final Wallet w2 = new Wallet(2, WalletType.CREDIT_CARD, 2000.65, 200);
    final Wallet w3 = new Wallet(3, WalletType.PAYTM, 2500.45, 100);
    //final Wallet w4 = new Wallet(4, WalletType.PAYTM, 3000.65, 200);
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String ord1 = new String("2020-03-18");
    final Date orDate1 = sdf.parse(ord1);
    final String ord2 = new String("2020-01-18");
    final Date orDate2 = sdf.parse(ord2);
    final Orders order1 = new Orders();
    order1.setCustomerId(100);
    order1.setMenuId(10);
    order1.setVendorId(1000);
    order1.setOrderQuantity(1);
    order1.setWalletType(WalletType.PAYTM);
    order1.setOrderDate(orDate1);
    order1.setOrderComments("Spicy");

    final Orders order2 = new Orders();
    order2.setCustomerId(100);
    order2.setMenuId(10);
    order2.setVendorId(1000);
    order2.setOrderQuantity(2300);
    order2.setWalletType(WalletType.PAYTM);
    order2.setOrderDate(orDate1);
    order2.setOrderComments("Spicy");

    final Orders order3 = new Orders();
    order3.setCustomerId(100);
    order3.setMenuId(10);
    order3.setVendorId(1000);
    order3.setOrderQuantity(2);
    order3.setWalletType(WalletType.PAYTM);
    order3.setOrderDate(orDate2);
    order3.setOrderComments("Spicy");
    new Expectations() {
        {
          dao.findByMenuId(10); result = m2;
        //   dao.findByMenuId(20); result = m3;
          dao.getWalletInfo(WalletType.PAYTM, 100); result = w3;
        //   dao.getWallentInfo("NETBANKING", 100); result = w1;
        //   dao.getWallentInfo("PAYTM", 200); result = w4;
        //   dao.getWallentInfo("NETBANKING", 200); result = w2;
          dao.placeOrder(order1);
          dao.placeOrder(order2);
          dao.placeOrder(order3);
        }
      };
    new MockUp<OrderFactory>() {
        @Mock
        OrderDAO dao() {
          return dao;
        }
      };
    String result1 = OrderFactory.placeOrder(order1);
    assertEquals(result1, "Order Placed Successfully...");
    String result2 = OrderFactory.placeOrder(order2);
    assertEquals(result2, "Insufficient Funds...");
    String result3 = OrderFactory.placeOrder(order3);
    assertEquals(result3, "Please Enter valid Date");
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void showCustomerHistory(@Mocked final OrderDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-03-18");
    Date odt = sdf.parse(dt);
    final Orders v2 = new Orders(2, 1, 4, 3, OrderStatus.ACCEPTED, "SPICY", 456.00000, odt, 1, WalletType.PAYTM,6.2,5.2);
    final Orders v3 = new Orders(3, 2, 1, 5, OrderStatus.ACCEPTED, "SPICY", 56.00000, odt, 4, WalletType.PAYTM,5.4,6.8);
    new Expectations() {
      {
        dao.customerHistory(1); result = v2;
        dao.customerHistory(5); result = v3;
        //dao.customerHistory(123); result = null;
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    Orders[] vendor1 = OrderFactory.showCustomerHistory(1);
    assertNotNull(vendor1);
    Orders[] vendor2 = OrderFactory.showCustomerHistory(5);
    assertNotNull(vendor2);
    // Orders[] vendor3 = OrderFactory.showCustomerHistory(3);
    // assertNull(vendor3);
  }
   /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void showVendorHistory(@Mocked final OrderDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-03-18");
    Date odt = sdf.parse(dt);
    final Orders v2 = new Orders(2, 1, 4, 3, OrderStatus.ACCEPTED, "SPICY", 456.00000, odt, 1, WalletType.PAYTM,5.2,6.3);
    final Orders v3 = new Orders(3, 2, 1, 5, OrderStatus.ACCEPTED, "SPICY", 56.00000, odt, 4, WalletType.PAYTM,4.5,5.5);
    new Expectations() {
      {
        dao.vendorHistory(1); result = v2;
        dao.vendorHistory(5); result = v3;
        //dao.customerHistory(3); result = null;
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    Orders[] vendor1 = OrderFactory.showVendorHistory(1);
    assertNotNull(vendor1);
    Orders[] vendor2 = OrderFactory.showVendorHistory(5);
    assertNotNull(vendor2);
    //Orders[] vendor3 = OrderFactory.showCustomerHistory(3);
    //assertNull(vendor3);
  }
/**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void showpendingCustomerOrders(@Mocked final OrderDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-03-18");
    Date odt = sdf.parse(dt);
    final Orders v2 = new Orders(2, 1, 4, 3, OrderStatus.ACCEPTED, "SPICY", 456.00000, odt, 1, WalletType.PAYTM,1.2,11.2);
    final Orders v3 = new Orders(3, 2, 1, 5, OrderStatus.ACCEPTED, "SPICY", 56.00000, odt, 4, WalletType.PAYTM,1.2,9.7);
    new Expectations() {
      {
        dao.pendingCustomer(1); result = v2;
        dao.pendingCustomer(5); result = v3;
        //dao.customerHistory(3); result = null;
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    Orders[] vendor1 = OrderFactory.showpendingCustomerOrders(1);
    assertNotNull(vendor1);
    Orders[] vendor2 = OrderFactory.showpendingCustomerOrders(5);
    assertNotNull(vendor2);
    //Orders[] vendor3 = OrderFactory.showCustomerHistory(3);
    //assertNull(vendor3);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void showpendingVendorOrders(@Mocked final OrderDAO dao) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String dt = new String("2020-03-18");
    Date odt = sdf.parse(dt);
    final Orders v2 = new Orders(2, 1, 4, 3, OrderStatus.ACCEPTED, "SPICY", 456.00000, odt, 1, WalletType.PAYTM,4.5,2.3);
    final Orders v3 = new Orders(3, 2, 1, 5, OrderStatus.ACCEPTED, "SPICY", 56.00000, odt, 4, WalletType.PAYTM,1.2,12.3);
    new Expectations() {
      {
        dao.pendingVendor(1); result = v2;
        dao.pendingVendor(5); result = v3;
        //dao.customerHistory(3); result = null;
      }
    };
    new MockUp<OrderFactory>() {
      @Mock
      OrderDAO dao() {
        return dao;
      }
    };
    Orders[] vendor1 = OrderFactory.showpendingVendorOrders(1);
    assertNotNull(vendor1);
    Orders[] vendor2 = OrderFactory.showpendingVendorOrders(5);
    assertNotNull(vendor2);
    //Orders[] vendor3 = OrderFactory.showCustomerHistory(3);
    //assertNull(vendor3);
  }
}
