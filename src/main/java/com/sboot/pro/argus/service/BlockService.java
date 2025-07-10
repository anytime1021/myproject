package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.vo.BlockVO;

public interface BlockService {
	
	// 블럭 수 카운트
	public int getBlockCount(String searchArea) throws Exception;
	
	// 블럭 리스트
	public List<BlockVO> selectBlockList(String searchArea, int offset, int limit) throws Exception;
	
	// 블럭 상세보기
	public BlockVO selectBlockView(String df_idNumber) throws Exception;
	
	// 블럭 추가 폼 일련번호 체크
	public boolean isExistIdNumber(String idNumber) throws Exception;
	
	// 블럭 추가
	public void addBlock(BlockVO addBlockForm, String searchArea) throws Exception;
	
	// 블럭 수정
	public void modBlock(BlockVO modBlockForm) throws Exception;
	
	// 블럭 삭제
	public void removeBlock(String df_idNumber) throws Exception;
	
	// 블럭 대여
	public void modItemStatus(String df_idNumber, String df_moveStatus) throws Exception;
	public void addMoveBlockList(BlockVO moveBlock, String login_area, String login_id) throws Exception;
	
	// 대여한 블럭 리스트
	public List<BlockVO> selectBlockRentalList(String searchArea) throws Exception;
	
	// 블럭 반납
	public void modStatusRecipient(String df_idNumber) throws Exception;
	
	// 블럭 이동 기록 수 카운트
	public int getMoveListCount(String searchArea) throws Exception;
	
	// 블럭 이동 기록
	public List<BlockVO> selectBlockMoveList(String searchArea) throws Exception;

	// 블럭 검색
	public List<BlockVO> selectSearchList(String searchType, String searchQuery) throws Exception;
	
	// 블럭 검색 수 카운트
	public int getSearchListCount(String searchType, String searchQuery) throws Exception;
}
