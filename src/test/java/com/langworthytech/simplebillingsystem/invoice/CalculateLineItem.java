package com.langworthytech.simplebillingsystem.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class CalculateLineItem {

	@Autowired
	private InvoiceService invoiceService;
	
	@Test
	public void addItemTotal() {
		
		BigDecimal unitPrice = new BigDecimal(45.00);
		int quantity = 12;
		
		BigDecimal itemSubtotal = invoiceService.calculateSubtotal(unitPrice, quantity);
		
		assertEquals("540", itemSubtotal.toString());
	}
	
	@Test
	public void calculateSalesTax() {
		
		BigDecimal taxRate = new BigDecimal(7.225);
		BigDecimal itemTotal = new BigDecimal(540);
		
		BigDecimal taxAmount = invoiceService.calculateSalesTax(taxRate, itemTotal);
		System.out.println(taxAmount.toString());
		
		assertEquals("39.01", taxAmount.toString());
	}
	
	@Test
	public void addSalesTaxToItemTotal() {
		
		BigDecimal itemTotal = new BigDecimal(540);
		BigDecimal taxAmount = new BigDecimal(39.01);
		
		BigDecimal itemAmount = invoiceService.calculateTotal(taxAmount, itemTotal);
		System.out.println(itemAmount.toString());
		
		assertEquals("579.01", itemAmount.toString());
	}
}
