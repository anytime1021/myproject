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
	
	// 블럭 대여
	public void updateItemStatus(@Param("df_idNumber") String df_idNumber, @Param("df_moveStatus") String df_moveStatus) throws Exception;
	public void insertMoveBlockList(@Param("moveBlock") BlockVO moveBlock, @Param("login_area") String login_area, @Param("login_id") String login_id) throws Exception;
	
	// 대여한 블럭 리스트
	public List<String> selectIdNumber(String searchArea) throws Exception;
	public List<BlockVO> selectBlockRentalList(@Param("idNumber") List<String> idNumber, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 블럭 반납
	public void updateReturnStatus(String df_idNumber) throws Exception;
	public void updateReturnRecipient(String df_idNumber) throws Exception;
	
	// 블럭 이동 기록 수 카운트
	public int selectMoveListCount(String searchArea) throws Exception;
	
	// 블럭 이동 기록
	public List<BlockVO> selectBlockMoveList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 블럭 검색 + 검색 수 카운트
	public List<BlockVO> selectSearchList(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	public int selectListCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("token") String token) throws Exception;

	public List<String> selectIdNumberSearch(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	public List<BlockVO> selectSearchMoveList(@Param("searchArea") String searchArea, @Param("idNumber") List<String> idNumber, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	public List<BlockVO> selectSearchRentalList(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	
}
