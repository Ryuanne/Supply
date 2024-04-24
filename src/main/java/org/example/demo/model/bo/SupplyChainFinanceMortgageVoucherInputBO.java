package org.example.demo.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplyChainFinanceMortgageVoucherInputBO {
  private BigInteger voucherId;

  private String toBank;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(voucherId);
    args.add(toBank);
    return args;
  }
}
