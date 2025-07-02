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
}
