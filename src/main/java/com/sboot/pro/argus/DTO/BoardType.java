package com.sboot.pro.argus.DTO;

public enum BoardType {
	workrate(1, "workrate_board"),
	sowDailyWorkLog_board(2, "sowDailyWorkLog_board"),
	results_board(3, "results_board"),
	reportmixed_board(4, "reportmixed_board");
	
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
