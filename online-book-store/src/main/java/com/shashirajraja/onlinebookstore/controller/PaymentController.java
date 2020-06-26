package com.shashirajraja.onlinebookstore.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shashirajraja.onlinebookstore.entity.CurrentSession;
import com.shashirajraja.onlinebookstore.entity.Customer;
import com.shashirajraja.onlinebookstore.entity.PurchaseDetail;
import com.shashirajraja.onlinebookstore.entity.PurchaseHistory;
import com.shashirajraja.onlinebookstore.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private CurrentSession currentSession;
	
	@GetMapping("customers/cart/pay")
	public String showPaymentForm() {
		
		return "customer-payment-form";
	}
	
	@PostMapping("customers/payment/success")
	public String paymentSuccess(@Param("upi") String upi,@Param("otp") String otp, Model theModel) {
		Customer customer = currentSession.getUser().getCustomer();
		//load the purchase history
		paymentService.getPurchaseHistories(customer);
		//create purchase History
		String transId = paymentService.createTransaction(customer);
		theModel.addAttribute("message", "Payment Successful with transaction Id: "+ transId);
		theModel.addAttribute("purchaseHistory", paymentService.getPurchaseHistory(customer, transId));
		return "customer-transaction-detail";
	}
	
	@GetMapping("customers/transactions")
	public String showTransactions(Model theModel) {
		Customer customer = currentSession.getUser().getCustomer();
		//load the purchaseHistories
		Set<PurchaseHistory> purchaseHistories = paymentService.getPurchaseHistories(customer);
		theModel.addAttribute("purchaseHistories", purchaseHistories);
		theModel.addAttribute("purchaseHistory", new PurchaseHistory());
		return "customer-transactions";
	}
	
	@PostMapping("customers/transactions/detail")
	public String getTransactionDetail(@ModelAttribute("transId") String transId, Model theModel) {
		theModel.addAttribute("message", "Details for Transaction Id: "+ transId);
		Customer customer = currentSession.getUser().getCustomer();
		theModel.addAttribute("purchaseHistory", paymentService.getPurchaseHistory(customer, transId));
		return "customer-transaction-detail";
	}
}
