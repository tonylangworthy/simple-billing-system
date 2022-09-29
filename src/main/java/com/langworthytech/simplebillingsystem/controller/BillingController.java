package com.langworthytech.simplebillingsystem.controller;

import com.langworthytech.simplebillingsystem.billing.BillingCycleFormModel;
import com.langworthytech.simplebillingsystem.security.AuthenticationFacade;
import com.langworthytech.simplebillingsystem.security.IAuthenticationFacade;
import com.langworthytech.simplebillingsystem.service.BillingService;
import com.langworthytech.simplebillingsystem.service.IBillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/billing")
public class BillingController {

    private static final Logger logger = LoggerFactory.getLogger(BillingController.class);

    private IAuthenticationFacade authenticationFacade;

    private IBillingService billingService;

    public BillingController(AuthenticationFacade authenticationFacade, BillingService billingService) {
        this.authenticationFacade = authenticationFacade;
        this.billingService = billingService;
    }

    @GetMapping("/intervals")
    public String showAllIntervals(Model model) {
//        Iterable<BillingCycle> intervals = billingService.showAllIntervals();
//
        model.addAttribute("intervals", null);

        return "interval_list";
    }

    @GetMapping("/intervals/create")
    public String showIntervalForm(Model model) {
        model.addAttribute("interval", new BillingCycleFormModel());

        return "interval_form";
    }

    @PostMapping("/intervals")
    public String createInterval(
            @ModelAttribute("interval") BillingCycleFormModel billingCycleFormModel,
             BindingResult bindingResult,
             RedirectAttributes redirectAttributes
    ) {
//        BillingCycle billingCycle = billingService.createInterval(
//                new BillingCycle(billingCycleFormModel.getIntervalName(), billingCycleFormModel.getIntervalCount())
//        );

        return "interval_form";
    }


}
