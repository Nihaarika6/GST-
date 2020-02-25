<<<<<<< HEAD
package com.hexaware.MLP176.integration.test;
=======
package com.hexaware.MLP176.model;
>>>>>>> 9bcbcea88f7d2f0d288e635b19d430234fb6c376
import java.util.Objects;
/**
 * wallet class used to display menu information.
 * @author hexware
 */
public class Wallet {
/**
 * walletId to store walletId.
 */
  private int walletId;
  /**
 * walletType to store wallet category.
 */
  private WalletType walletType;
  /**
 * walletAmount to store food name.
 */
  private Double walletAmount;
  /**
 * walletId to store walletId.
 */
  private int customerId;
  /**
   * Default Constructor.
   */
  public Wallet() {

  }
/**
 * @param argWalletId to initialize food id.
 * @param argWalletType to initialize food price.
 * @param argWalletAmount to initialize food Detail.
 * @param argCustomerId to initialize food Detail.
 * used to get details through constructor.
 */
  public Wallet(final int argWalletId, final WalletType argWalletType, final Double argWalletAmount, final int argCustomerId) {
    this.walletId = argWalletId;
    this.walletType = argWalletType;
    this.walletAmount = argWalletAmount;
    this.customerId = argCustomerId;
  }

  @Override
    public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Wallet wallet = (Wallet) obj;
<<<<<<< HEAD
    if (Objects.equals(walletId, wallet.walletId)
        && Objects.equals(walletType, wallet.walletType)
        && Objects.equals(walletAmount, wallet.walletAmount)
        && Objects.equals(customerId, wallet.customerId)) {
=======
    if (Objects.equals(walletId, wallet.walletId)) {
>>>>>>> 9bcbcea88f7d2f0d288e635b19d430234fb6c376
      return true;
    }
    return false;
  }
  @Override
    public final int hashCode() {
    return Objects.hash(walletId);
  }
  @Override
  public final String toString() {
    return String.format("%-15s %-15s %-15s %-15s", walletId, walletType, walletAmount,
     customerId);
  }
    /**
     * @return this food ID.
     */
  public final int getWalletId() {
    return walletId;
  }
    /**
     * @param argWalletId gets the food id.
     */
  public final void setWalletId(final int argWalletId) {
    this.walletId = argWalletId;
  }
    /**
     * @return this menu category.
     */
  public final WalletType getWalletType() {
    return walletType;
  }
    /**
     * @param argWalletType gets the menu category1.
     */
  public final void setWalletType(final WalletType argWalletType) {
    this.walletType = argWalletType;
  }
    /**
     * @return this menu item.
     */
  public final Double getWalletAmount() {
    return walletAmount;
  }
    /**
     * @param argWalletAmount gets the menu item.
     */
  public final void setWalletAmount(final Double argWalletAmount) {
    this.walletAmount = argWalletAmount;
  }
   /**
     * @return this menu item.
     */
  public final int getCustomerId() {
    return customerId;
  }
    /**
     * @param argCustomerId gets the menu item.
     */
  public final void setCustomerId(final int argCustomerId) {
    this.customerId = argCustomerId;
  }
}
