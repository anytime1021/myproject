package com.sboot.pro.argus.vo;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("resultsVO")
@Data
public class ResultsVO extends WorkingDailyBaseVO {
	private BigDecimal results_dailyprofits;
	private BigDecimal results_sum;
	private BigDecimal results_achievement;
	private String note;
	
	private String results_dailyprofits_comma;
	private String results_sum_comma;
	public BigDecimal getResults_dailyprofits() {
		return results_dailyprofits;
	}
	public void setResults_dailyprofits(BigDecimal results_dailyprofits) {
		this.results_dailyprofits = results_dailyprofits;
	}
	public BigDecimal getResults_sum() {
		return results_sum;
	}
	public void setResults_sum(BigDecimal results_sum) {
		this.results_sum = results_sum;
	}
	public BigDecimal getResults_achievement() {
		return results_achievement;
	}
	public void setResults_achievement(BigDecimal results_achievement) {
		this.results_achievement = results_achievement;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getResults_dailyprofits_comma() {
		return results_dailyprofits_comma;
	}
	public void setResults_dailyprofits_comma(String results_dailyprofits_comma) {
		this.results_dailyprofits_comma = results_dailyprofits_comma;
	}
	public String getResults_sum_comma() {
		return results_sum_comma;
	}
	public void setResults_sum_comma(String results_sum_comma) {
		this.results_sum_comma = results_sum_comma;
	}
	
	
	@Override
	public String toString() {
		return "ResultsVO [results_dailyprofits=" + results_dailyprofits + ", results_sum=" + results_sum
				+ ", results_achievement=" + results_achievement + ", note=" + note + ", results_dailyprofits_comma="
				+ results_dailyprofits_comma + ", results_sum_comma=" + results_sum_comma + "]";
	}
}
