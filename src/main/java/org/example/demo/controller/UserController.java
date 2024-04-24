package org.example.demo.controller;

import cn.hutool.core.lang.Dict;
import org.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Dict registerUser(@RequestParam String address, @RequestParam String userType) {
        return userService.registerUser(address, userType);
    }

    @PostMapping("/createVoucher")
    public Dict createVoucher(@RequestParam String address, @RequestParam BigInteger amount) {
        return userService.createVoucher(address, amount);
    }

    @PostMapping("/transferVoucher")
    public Dict transferVoucher(@RequestParam String address, @RequestParam BigInteger voucherId, @RequestParam String toAddress) {
        return userService.transferVoucher(address, voucherId, toAddress);
    }

    @PostMapping("/mortgageVoucher")
    public Dict mortgageVoucher(@RequestParam String address, @RequestParam BigInteger voucherId, @RequestParam String toBank) {
        return userService.mortgageVoucher(address, voucherId, toBank);
    }

    @PostMapping("/redeemVoucher")
    public Dict redeemVoucher(@RequestParam String address, @RequestParam BigInteger voucherId) {
        return userService.redeemVoucher(address, voucherId);
    }
}

