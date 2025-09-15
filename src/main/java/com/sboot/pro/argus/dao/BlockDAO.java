package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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
	
	// 대여한 블럭 수 카운트
	public int selectRentalListCount(String searchArea) throws Exception;

	// 대여한 블럭 리스트
	public List<BlockVO> selectBlockRentalList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 블럭 반납
	public String selectIdNumber(int app_num) throws Exception;
	public void updateReturnStatus(String df_idNumber) throws Exception;
	public void updateReturnRecipient(int app_num) throws Exception;
	
	// 블럭 이동 기록 수 카운트
	public int selectBlockMoveListCount(String searchArea) throws Exception;
	
	// 블럭 이동 기록
	public List<BlockVO> selectBlockMoveList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 블럭 검색 + 검색 수 카운트
	public List<BlockVO> selectSearchList(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	public int selectListCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("token") String token, @Param("idNumber") List<String> idNumber) throws Exception;
	public int selectMoveListCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	public int selectListTotalCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	
	public List<String> selectIdNumberSearch(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	public List<BlockVO> selectSearchMoveList(@Param("idNumber") List<String> idNumber, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	public List<String> selectRentalListId(@Param("searchArea") String searchArea) throws Exception;
	public List<BlockVO> selectSearchRentalList(@Param("idNumber") List<String> idNumber, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	public List<BlockVO> selectSearchTotalList(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 전체 블럭 보기 수 카운트
	public int selectBlockTotalCount(String searchArea) throws Exception;
	
	// 전체 블럭 리스트
	public List<BlockVO> selectBlockTotalList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 승인 대기 수 카운트
	public int selectApprovalCount(String searchArea) throws Exception;
	
	// 승인 대기 리스트
	public List<BlockVO> selectApprovalList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 이동 보고서 상세보기
	public BlockVO selectBlockApprovalView(int app_num) throws Exception;
	public BlockVO ApprovalDivision(int app_num) throws Exception;
	
	// 이동 승인
	public int updateApproval(@Param("app_num") int app_num, @Param("app_comment") String app_comment, @Param("searchArea") String searchArea) throws Exception;
	public int tnfCheck(int app_num) throws Exception;
	public void finalApproval(int app_num) throws Exception;
	
	// 이동 거절
	public int updateRejection(@Param("app_num") int app_num, @Param("app_comment") String app_comment, @Param("searchArea") String searchArea) throws Exception;
	public void finalRejection(int app_num) throws Exception;
	
	// 블럭 스펙 추가
	public void insertBlockSpec(@Param("df_idNumber") String df_idNumber, @Param("img") BlockVO img) throws Exception;
	
	// 블럭 스펙 보기
	public List<BlockVO> selectBlockSpecView(String df_idNumber) throws Exception;
}
