package com.seaweed.seaweed.dto.customReports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomersReportCards {
    public int purchasesAmount;
    public int boughtProducts;
    public int orderedProducts;
}
