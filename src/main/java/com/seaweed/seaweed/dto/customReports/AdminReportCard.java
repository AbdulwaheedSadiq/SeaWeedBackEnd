package com.seaweed.seaweed.dto.customReports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminReportCard {
    public Integer income;
    public Integer soldProducts;
    public Integer ownedProducts;
    public long systemUsers;
    public Integer generatedRevenue;
    public long registeredProducts;
}
