package com.sboot.pro.argus.vo;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("resultsVO")
@Data
public class ResultsVO extends WorkingDailyBaseVO {
	private BigDecimal results_profits;
}
