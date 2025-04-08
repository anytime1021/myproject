package com.sboot.pro.argus.DTO;

public enum BoardType {
	workrate(1, "workrate_board"),
	workrate_total(2, "workrate_total_board"),
	sowDailyWorkLog_board(3, "sowDailyWorkLog_board"),
	sowMonthWorkLog_board(4, "sowMonthWorkLog_board"),
	performance(5, "perfomance_board");
	
	private final int token;
	private final String tableName;
	
	BoardType(int token, String tableName) {
		this.token = token;
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public static BoardType fromToken(int token) {
		for (BoardType type : values()) {
			if (type.token == token) return type;
		}
		throw new IllegalArgumentException("Invalid token: " + token);
	}
}
