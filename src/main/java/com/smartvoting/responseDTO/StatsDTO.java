package com.smartvoting.responseDTO;

import lombok.Data;

import java.util.List;

@Data
public class StatsDTO {
    double mean;
    double median;
    List<Integer> mode;
}
