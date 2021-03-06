package com.hexaware.MLP176.factory;

import com.hexaware.MLP176.persistence.OrderDAO;
import com.hexaware.MLP176.persistence.DbConnection;
import java.util.Date;
//import java.sql.Date;
import java.util.List;
import com.hexaware.MLP176.model.Menu;
import com.hexaware.MLP176.model.Orders;
import com.hexaware.MLP176.model.Wallet;
import com.hexaware.MLP176.model.WalletType;
import com.hexaware.MLP176.model.OrderStatus;
/**
 * MenuFactory class used to fetch menu data from database.
 * @author hexware
 */
public class OrderFactory {
  /**
   *  Protected constructor.
   */
  protected OrderFactory() {

  }
  /**
   * Call the data base connection.
   * @return the connection object.
   */
  public static OrderDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(OrderDAO.class);
  }
  /**
   * Call the data base connection.
   * @return the connection object.
   * @param menId for pending orders.
   */
  public static Menu showcom(final int menId) {
    Menu com = dao().findByMenuId(menId);
    return com;
  }
   /**
   * Call the data base connection.
   * @return the array of menu object.
   */
  public static Orders[] showOrder() {
    List<Orders> order = dao().show();
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Customer Orders History.
   * @param custId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showCustomerHistory(final int custId) {
    List<Orders> order = dao().customerHistory(custId);
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Vendor Orders History.
   * @param vendId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showVendorHistory(final int vendId) {
    List<Orders> order = dao().vendorHistory(vendId);
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Customer Pending Orders.
   * @param custId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showpendingCustomerOrders(final int custId) {
    List<Orders> order = dao().pendingCustomer(custId);
    return order.toArray(new Orders[order.size()]);
  }
    /**
   * Show Customer Pending Orders.
   * @param vendId for pending orders.
   * @return the array of order object.
   */
  public static Orders[] showpendingVendorOrders(final int vendId) {
    List<Orders> order = dao().pendingVendor(vendId);
    return order.toArray(new Orders[order.size()]);
  }
  /**
   * Show Vendor Orders History.
   * @param orderId for accept or reject.
   * @param vendorId for accept or reject.
   * @param status for changing status.
   * @return the array of order object.
   */
  public static String acceptOrRejectOrder(final int orderId, final int vendorId, final String status) {
    Orders order = dao().findByOrderId(orderId);
    String result = "";
    if (order != null) {
      if (order.getVendorId() == vendorId) {
        if (status.equals("ACCEPTED")) {
          dao().acceptOrReject(status, orderId);
          result = "Order Accepted Successfully...";
        }
        if (status.equals("REJECTED")) {
          dao().acceptOrReject(status, orderId);
          WalletType type = order.getWalletType();
          int custId = order.getCustomerId();
          double billAmount = order.getOrderTotalamount();
          dao().refundAmount(billAmount, type, custId);
          result = ("Order Cancelled Successfully and amount refunded to " + type);
        }
      } else {
        result = "You are Unauthorised Vendor for this User";
      }
    } else {
      result = "Invalid OrderId...";
    }
    return result;
  }
   /**
   * Show Vendor Orders History.
   * @param orderId for accept or reject.
   * @param custId for accept or reject.
   * @param status for changing status.
   * @return the array of order object.
   */
  public static String cancelOrder(final int orderId, final int custId, final String status) {
    Orders order = dao().findByOrderId(orderId);
    OrderStatus ostatus = order.getOrderStatus();
    String result = "";
    if (order != null) {
      if (ostatus == OrderStatus.PENDING) {
        if (order.getCustomerId() == custId) {
          if (status.equals("YES")) {
            String st = "REJECTED";
            dao().acceptOrReject(st, orderId);
            double billAmount = order.getOrderTotalamount();
            WalletType type = order.getWalletType();
            billAmount = billAmount - (billAmount / 10);
            dao().refundAmount(billAmount, type, custId);
            result = "Order Cancelled Successfully and amount refunded to " + type;
          }
        } else {
          result = "You are Unauthorised";
        }
      } else {
        result = "You cannot cancel this order...";
      }
    } else {
      result = "Invalid OrderId...";
    }
    return result;
  }
  /**
   * Place order for customer.
   * @param order for accepting order details.
   * @return order status.
   */
  public static String placeOrder(final Orders order) {
    Menu menu = dao().findByMenuId(order.getMenuId());
    Wallet wallet = dao().getWalletInfo(order.getWalletType(), order.getCustomerId());
    System.out.println(wallet.getWalletAmount());
    double walAmount = wallet.getWalletAmount();
    double price = menu.getMenuCost();
    double totalAmount = price * order.getOrderQuantity();
    double cgst = totalAmount*.025;
    double sgst = totalAmount*.025;
    double finalamnt = totalAmount+cgst+sgst; 
    Date today = new Date();
    long diffTime = order.getOrderDate().getTime() - today.getTime();
    long diffdays = diffTime / (60 * 60 * 1000 * 24);
    // System.out.println("Diff Time "+ diffDays);
    if (walAmount < finalamnt) {
      return "Insufficient Funds...";
    } else if (diffdays < 0) {
      return "Please Enter valid Date";
    } else {
      double diff = walAmount - finalamnt;
      System.out.println("Price is  " + menu.getMenuCost());
      order.setOrderStatus(OrderStatus.PENDING);
      order.setOrderTotalamount(finalamnt);
      order.setVenCgst(cgst);
      order.setVenSgst(sgst);
      dao().placeOrder(order);
      dao().updateBalance(diff, order.getWalletType(), order.getCustomerId());
     
      //if (totalAmount > 500) {
        //double tax = totalAmount + 0.2 * (totalAmount);
        //System.out.println(tax);
        // System.out.println("Commm");
        // Menu m = OrderFactory.showcom(6);
        // System.out.println(m);
      //*  }
     // System.out.println("HI");
      //dao().tax(cgst,sgst,order.getOrderId());
      System.out.println("total amount  "+ finalamnt);
      System.out.println("cgst amount  "+ cgst);
      System.out.println("sgst amount  "+ sgst);

      return "Order Placed Successfully...";
    }
    
  }
}
