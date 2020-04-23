package com.langworthytech.simplebillingsystem.invoice;

import com.langworthytech.simplebillingsystem.invoice.dto.TaxCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
@RequestMapping("/calculations")
public class CalculationController {

    private static final Logger logger = LoggerFactory.getLogger(CalculationController.class);

    @PostMapping("/tax")
    public @ResponseBody
    TaxCalculation calculateTax(@RequestBody TaxCalculation taxCalculation) {

        BigDecimal subtotal = taxCalculation.getSubtotal().divide(BigDecimal.valueOf(100));

        BigDecimal taxRate = taxCalculation.getTaxRate();
        taxRate = taxRate.divide(BigDecimal.valueOf(100));

        BigDecimal taxAmount = subtotal.multiply(taxRate);

        BigDecimal roundedTaxAmount = taxAmount.setScale(2, RoundingMode.HALF_EVEN);

        taxCalculation.setTaxAmount(roundedTaxAmount);

        logger.info("Tax Rate: " + taxCalculation.getTaxRate());

        logger.info(taxCalculation.toString());



        return taxCalculation;
    }


}
