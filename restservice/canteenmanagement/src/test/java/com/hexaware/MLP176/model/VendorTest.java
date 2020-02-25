package com.hexaware.MLP176.model;

import com.hexaware.MLP176.persistence.VendorDAO;
import com.hexaware.MLP176.factory.VendorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import com.hexaware.MLP176.model.Vendor;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
//import static org.junit.Assert.assertNull;
//import java.util.Date;

// import com.hexaware.MLP176.factory.VendorFactory;
// import com.hexaware.MLP176.persistence.VendorDAO;

//import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.junit.Test;
import org.junit.Before;
//import org.junit.runner.RunWith;
//import java.util.ArrayList;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
//import mockit.integration.junit4.JMockit;
// import java.util.ArrayList;
/**
 * Test class for Vendor.
 */
//@RunWith(JMockit.class)
public class VendorTest {
        /**
   * setup method.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests for equals.
   */
  @Test
  public final void testEquals() {
    Vendor v = new Vendor();
    Vendor v1 = null;
    Vendor v2 = new Vendor(1, "ABC", "abc", "9876543210", "ABC@GMAIL.COM", "200");
    Vendor v3 = new Vendor(1, "ABC", "abc", "9876543210", "ABC@GMAIL.COM", "200");
    assertFalse(v2.equals(v1));
    assertTrue(v2.equals(v3));
    assertEquals(v2.hashCode(), v3.hashCode());
    Orders order = new Orders();
    assertFalse(v2.equals(order));
    assertFalse(v2.equals(v));
  }
  /**
   * Tests the toString() methods of the Vendor class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //String dt = new String("2020-03-18");
    //Date odt = sdf.parse(dt);
    Vendor vendor1 = new Vendor(1, "ABC", "abc", "9876543210", "ABC@GMAIL.COM", "200");
    String result = String.format("%-15s %-15s %-15s %-15s %-15s %-15s",
        vendor1.getVendorId(), vendor1.getVendorName(), vendor1.getVendorUsername(),
        vendor1.getVendorNumber(), vendor1.getVendorEmail(), vendor1.getVendorPassword());
    assertEquals(result, vendor1.toString());
  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testVendor() {
    Vendor v = new Vendor();
    assertNotNull(v);
    Vendor vendor = new Vendor(1, "ABC", "abc", "9876543210", "ABC@GMAIL.COM", "200");
    assertEquals(1, vendor.getVendorId());
    assertEquals("ABC", vendor.getVendorName());
    assertEquals("abc", vendor.getVendorUsername());
    assertEquals("9876543210", vendor.getVendorNumber());
    assertEquals("ABC@GMAIL.COM", vendor.getVendorEmail());
    assertEquals("200", vendor.getVendorPassword());

    v.setVendorId(1);
    v.setVendorName("ABC");
    v.setVendorUsername("abc");
    v.setVendorNumber("9876543210");
    v.setVendorEmail("ABC@GMAIL.COM");
    v.setVendorPassword("200");

    assertEquals(1, v.getVendorId());
    assertEquals("ABC", v.getVendorName());
    assertEquals("abc", v.getVendorUsername());
    assertEquals("9876543210", v.getVendorNumber());
    assertEquals("ABC@GMAIL.COM", v.getVendorEmail());
    assertEquals("200", v.getVendorPassword());
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */

  @Test
   public final void testListAllEmpty(@Mocked final VendorDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Vendor>();
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
      VendorDAO dao() {
        return dao;
      }
    };
    Vendor[] v = VendorFactory.showVendor();
    assertEquals(0, v.length);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */

  @Test
  public final void testListAllSome(@Mocked final VendorDAO dao) {
    final Vendor v2 = new Vendor(101, "XYZ", "xyz", "9999977654", "XYZ@GMAIL.COM", "512");
    final Vendor v3 = new Vendor(102, "RKP", "rkp", "9988877654", "RKP@GMAIL.COM", "507");
    final ArrayList<Vendor> ven = new ArrayList<Vendor>();
    ven.add(v2);
    ven.add(v3);
    new Expectations() {
      {
        dao.show(); result = ven;
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
      VendorDAO dao() {
        return dao;
      }
    };
    Vendor[] ven1 = VendorFactory.showVendor();
    assertEquals(2, ven1.length);
    assertEquals(101, ven1[0].getVendorId());
    assertEquals(102, ven1[1].getVendorId());
    assertEquals("XYZ", ven1[0].getVendorName());
    assertEquals("RKP", ven1[1].getVendorName());
    assertEquals("xyz", ven1[0].getVendorUsername());
    assertEquals("rkp", ven1[1].getVendorUsername());
    assertEquals("9999977654", ven1[0].getVendorNumber());
    assertEquals("9988877654", ven1[1].getVendorNumber());
    assertEquals("XYZ@GMAIL.COM", ven1[0].getVendorEmail());
    assertEquals("RKP@GMAIL.COM", ven1[1].getVendorEmail());
    assertEquals("512", ven1[0].getVendorPassword());
    assertEquals("507", ven1[1].getVendorPassword());
  }
}

