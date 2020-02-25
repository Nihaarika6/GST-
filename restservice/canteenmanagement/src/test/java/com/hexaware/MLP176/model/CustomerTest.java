package com.hexaware.MLP176.model;

import com.hexaware.MLP176.persistence.CustomerDAO;
import com.hexaware.MLP176.factory.CustomerFactory;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.text.ParseException;
//import mockit.integration.junit4.JMockit;
import java.util.ArrayList;

// import com.hexaware.MLP176.factory.CustomerFactory;
// import com.hexaware.MLP176.persistence.CustomerDAO;

import org.junit.Before;
//import org.junit.runner.RunWith;
import org.junit.Test;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
/**
 * Test class for Customer.
 */
//@RunWith(JMockit.class)
public class CustomerTest {
    /**
   * setup method.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests the equals/hashcode methods of the customer
    class.
   */
  @Test
   public final void testEquals() {
    Customer c = new Customer();
    Customer c1 = null;
    Customer c2 = new Customer(100, "HARI", "HARI@GMAIL.COM", "9999988888", "T.NAGER,CHENNAI", "200", "harig");
    Customer c3 = new Customer(100, "HARI", "HARI@GMAIL.COM", "9999988888", "T.NAGER,CHENNAI", "200", "harig");
    assertFalse(c2.equals(c1));
    assertTrue(c2.equals(c3));
    assertEquals(c2.hashCode(), c3.hashCode());
    Vendor customer = new Vendor();
    assertFalse(c2.equals(customer));
    assertFalse(c.equals(c2));
  }
   /**
   * Tests the toString() methods of the Customer class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Customer c = new Customer(100, "HARI", "HARI@GMAIL.COM", "9999988888", "T.NAGER,CHENNAI", "200", "harig");
    String result = String.format("%15s %15s %15s %15s %15s %15s %15s",
          c.getCustomerId(), c.getCustomerName(), c.getCustomerEmail(), c.getCustomerNumber(),
          c.getCustomerAddress(), c.getCustomerPassword(), c.getCustomerUsername());
    assertEquals(result, c.toString());
  }
   /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testCustomer() {
    Customer customer = new Customer();
    customer.setCustomerId(100);
    assertEquals(100, customer.getCustomerId());
    customer.setCustomerName("HARI");
    assertEquals("HARI", customer.getCustomerName());
    customer.setCustomerEmail("HARI@GMAIL.COM");
    assertEquals("HARI@GMAIL.COM", customer.getCustomerEmail());
    customer.setCustomerNumber("9999988888");
    assertEquals("9999988888", customer.getCustomerNumber());
    customer.setCustomerAddress("T.NAGER,CHENNAI");
    assertEquals("T.NAGER,CHENNAI", customer.getCustomerAddress());
    customer.setCustomerPassword("200");
    assertEquals("200", customer.getCustomerPassword());
    customer.setCustomerUsername("harig");
    assertEquals("harig", customer.getCustomerUsername());
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */

  @Test
   public final void testListAllEmpty(@Mocked final CustomerDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Customer>();
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
      CustomerDAO dao() {
        return dao;
      }
    };
    Customer[] c = CustomerFactory.showCustomer();
    assertEquals(0, c.length);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */

  @Test
  public final void testListAllSome(@Mocked final CustomerDAO dao) {
    final Customer c2 = new Customer(1, "XYZ", "XYZ@GMAIL.COM",  "9999977654", "Chennai", "512", "xyz");
    final Customer c3 = new Customer(12, "WEZ", "WEZ@GMAIL.COM",  "9900077654", "Coimbatore", "502", "wez");
    final ArrayList<Customer> cus = new ArrayList<Customer>();
    cus.add(c2);
    cus.add(c3);
    new Expectations() {
      {
        dao.show(); result = cus;
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
      CustomerDAO dao() {
        return dao;
      }
    };
    Customer[] cus1 = CustomerFactory.showCustomer();
    assertEquals(2, cus1.length);
    assertEquals(1, cus1[0].getCustomerId());
    assertEquals(12, cus1[1].getCustomerId());
    assertEquals("XYZ", cus1[0].getCustomerName());
    assertEquals("WEZ", cus1[1].getCustomerName());
    assertEquals("XYZ@GMAIL.COM", cus1[0].getCustomerEmail());
    assertEquals("WEZ@GMAIL.COM", cus1[1].getCustomerEmail());
    assertEquals("9999977654", cus1[0].getCustomerNumber());
    assertEquals("9900077654", cus1[1].getCustomerNumber());
    assertEquals("Chennai", cus1[0].getCustomerAddress());
    assertEquals("Coimbatore", cus1[1].getCustomerAddress());
    assertEquals("512", cus1[0].getCustomerPassword());
    assertEquals("502", cus1[1].getCustomerPassword());
    assertEquals("xyz", cus1[0].getCustomerUsername());
    assertEquals("wez", cus1[1].getCustomerUsername());
  }
}
