// SPDX-License-Identifier: MIT

pragma solidity ^0.4.25;

 

contract SupplyChainFinance {

    // 用户结构，包括企业和银行

    struct User {

        string userType; // 用户类型，例如"bank"或"enterprise"

        bool isRegistered; // 用户是否已注册

    }

   

    // 凭证结构

    struct Voucher {

        uint amount; // 凭证金额

        address issuer; // 发行者地址

        address currentHolder; // 当前持有者地址

        bool isMortgaged; // 是否已抵押

    }

 

    // 用户地址到用户信息的映射

    mapping(address => User) public users;

   

    // 凭证ID到凭证信息的映射

    mapping(uint => Voucher) public vouchers;

   

    // 凭证的序列号

    uint private nextVoucherId = 1;

   

    // 事件定义

    event VoucherCreated(uint voucherId);

    event VoucherTransferred(uint voucherId, address to);

    event VoucherMortgaged(uint voucherId, address toBank);

    event VoucherRedeemed(uint voucherId, address byEnterprise);

 

    // 用户注册

    function registerUser(string userType) public {

        require(!users[msg.sender].isRegistered, "User already registered.");

        users[msg.sender] = User(userType, true);

    }

 

    // 创建凭证

    function createVoucher(uint amount) public {

        require(users[msg.sender].isRegistered, "User not registered.");

        require(keccak256(bytes(users[msg.sender].userType)) == keccak256("enterprise"), "Only enterprises can issue vouchers.");

 

        uint voucherId = nextVoucherId++;

        vouchers[voucherId] = Voucher(amount, msg.sender, msg.sender, false);

        emit VoucherCreated(voucherId);

    }

 

    // 转让凭证

    function transferVoucher(uint voucherId, address to) public {

        require(vouchers[voucherId].issuer == msg.sender, "Only issuer can transfer the voucher.");

        require(!vouchers[voucherId].isMortgaged, "Voucher is mortgaged.");

 

        vouchers[voucherId].currentHolder = to;

        emit VoucherTransferred(voucherId, to);

    }

 

    // 抵押凭证

    function mortgageVoucher(uint voucherId, address toBank) public {

        require(vouchers[voucherId].currentHolder == msg.sender, "Only holder can mortgage the voucher.");

        require(keccak256(bytes(users[toBank].userType)) == keccak256("bank"), "Mortgagee must be a bank.");

 

        vouchers[voucherId].isMortgaged = true;

        emit VoucherMortgaged(voucherId, toBank);

    }

 

    // 赎回凭证

    function redeemVoucher(uint voucherId) public {

        require(vouchers[voucherId].issuer == msg.sender, "Only issuer can redeem the voucher.");

        require(vouchers[voucherId].isMortgaged, "Voucher not mortgaged.");

 

        vouchers[voucherId].isMortgaged = false;

        emit VoucherRedeemed(voucherId, msg.sender);

    }

}

 