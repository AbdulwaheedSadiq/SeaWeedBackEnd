package com.seaweed.seaweed.dto.customReports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerDashboard {
    public int income;
    public int soldProducts;
    public int ownedProducts;
}
