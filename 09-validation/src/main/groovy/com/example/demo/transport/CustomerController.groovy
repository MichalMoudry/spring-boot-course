package com.example.demo.transport

import com.example.demo.transport.model.Customer
import groovy.transform.CompileStatic
import jakarta.validation.Valid
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
@CompileStatic
final class CustomerController {
    @GetMapping('/')
    String showForm(Model model) {
        model.addAttribute('customer', new Customer())
        'customer-form'
    }

    @PostMapping('/processForm')
    String processForm(
            @Valid @ModelAttribute('customer') Customer customer,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            'customer-form'
        }
        else {
            'customer-confirmation'
        }
    }
}
