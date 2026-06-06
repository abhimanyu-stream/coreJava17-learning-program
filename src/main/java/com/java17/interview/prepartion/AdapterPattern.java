package com.java17.interview.prepartion;

public class AdapterPattern {
}
/**
 * Make incompatible classes work together.
 *
 * Example
 *
 * Old payment gateway:
 *
 * oldPay()
 *
 * New app expects:
 *
 * pay()
 *
 * Adapter bridges.
 *
 * Code
 * interface Payment {
 *     void pay();
 * }
 *
 * class OldPaymentGateway {
 *     void oldPay() {
 *         System.out.println("Old payment");
 *     }
 * }
 *
 * class PaymentAdapter implements Payment {
 *
 *     private OldPaymentGateway oldGateway =
 *             new OldPaymentGateway();
 *
 *     public void pay() {
 *         oldGateway.oldPay();
 *     }
 * }
 */