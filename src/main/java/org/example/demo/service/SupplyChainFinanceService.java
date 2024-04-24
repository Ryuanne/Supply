package org.example.demo.service;

import java.lang.Exception;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import cn.hutool.core.lang.Dict;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo.model.bo.SupplyChainFinanceCreateVoucherInputBO;
import org.example.demo.model.bo.SupplyChainFinanceMortgageVoucherInputBO;
import org.example.demo.model.bo.SupplyChainFinanceRedeemVoucherInputBO;
import org.example.demo.model.bo.SupplyChainFinanceRegisterUserInputBO;
import org.example.demo.model.bo.SupplyChainFinanceTransferVoucherInputBO;
import org.example.demo.model.bo.SupplyChainFinanceUsersInputBO;
import org.example.demo.model.bo.SupplyChainFinanceVouchersInputBO;
import org.example.demo.utils.WeBASEUtils;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class SupplyChainFinanceService {
  public static final String ABI = org.example.demo.utils.IOUtil.readResourceAsString("abi/SupplyChainFinance.abi");

  public static final String BINARY = org.example.demo.utils.IOUtil.readResourceAsString("bin/ecc/SupplyChainFinance.bin");

  public static final String SM_BINARY = org.example.demo.utils.IOUtil.readResourceAsString("bin/sm/SupplyChainFinance.bin");

  @Value("${system.contract.supplyChainFinanceAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse users(SupplyChainFinanceUsersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "users", input.toArgs());
  }

  public TransactionResponse transferVoucher(SupplyChainFinanceTransferVoucherInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "transferVoucher", input.toArgs());
  }

  public CallResponse vouchers(SupplyChainFinanceVouchersInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "vouchers", input.toArgs());
  }

  public TransactionResponse mortgageVoucher(SupplyChainFinanceMortgageVoucherInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "mortgageVoucher", input.toArgs());
  }

  public TransactionResponse createVoucher(SupplyChainFinanceCreateVoucherInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "createVoucher", input.toArgs());
  }

  public TransactionResponse registerUser(SupplyChainFinanceRegisterUserInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "registerUser", input.toArgs());
  }

  public TransactionResponse redeemVoucher(SupplyChainFinanceRedeemVoucherInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "redeemVoucher", input.toArgs());
  }
}

