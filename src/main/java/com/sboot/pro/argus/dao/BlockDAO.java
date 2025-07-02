package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.BlockVO;

@Mapper
@Repository("blockDAO")
public interface BlockDAO {
	
	// 블럭 수 카운트
	public int selectBlockCount(String searchArea) throws Exception;
	
	// 블럭 리스트
	public List<BlockVO> selectBlockList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 블럭 상세보기
	public BlockVO selectBlockView(String df_idNumber) throws Exception;
	
	// 블럭 추가 폼 일련번호 체크
	public int isExistIdNumber(String df_idNumber) throws Exception;
	
	// 블럭 추가
	public void insertBlock(@Param("addBlockForm") BlockVO addBlockForm, @Param("searchArea") String searchArea) throws Exception;

	// 블럭 수정
	public void insertUpdateLog(int df_num) throws Exception;
	public void updateBlock(BlockVO modBlockForm) throws Exception;
	
	// 블럭 삭제
	public void deleteBlock(String df_idNumber) throws Exception;
}
