package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.services.CustomerService;
import guru.springframework.msscbeerservice.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@RestController
public class CustomerController
{

    private final CustomerService customerService;

    @GetMapping("customer/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId)
    {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping(path = "customer")
    public ResponseEntity saveNewCustomer(@RequestBody CustomerDto customerDto)
    {
        System.out.println("saveNewCustomer");
        return new ResponseEntity<>(customerService.saveNewCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("customer/{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId, @RequestBody @Validated CustomerDto customerDto)
    {
        System.out.println("updateCustomerById");
        return new ResponseEntity<>(customerService.updateCustomer(customerId, customerDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("customer/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId)
    {
        customerService.deleteCustomer(customerId);
    }
}
