package myapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {

    @GetMapping("/shopping")
    public ResponseEntity<?> getShopping() {
        return new ResponseEntity<String>("Get shopping", HttpStatus.OK);
    }

    @GetMapping("/orders")
//    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getOrders() {
        return new ResponseEntity<>("Get orders", HttpStatus.OK);
    }

    @GetMapping("/payments")
//    @PreAuthorize("hasRole('finance')")
    public ResponseEntity<?> getPayments() {
        return new ResponseEntity<>("Get payments", HttpStatus.OK);
    }
}
