package com.sboot.pro.argus.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sboot.pro.argus.vo.BlockVO;

import jakarta.servlet.http.HttpServletRequest;

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
	public void addMoveBlockList(BlockVO moveBlock, String login_area) throws Exception;
	
	// 블럭 외부 반출
	public int addExpertBlock(BlockVO expertBlock) throws Exception;
	
	// 대여한 블럭 수 카운트
	public int getRentalListCount(String searchArea) throws Exception;
	
	// 대여한 블럭 리스트
	public List<BlockVO> selectBlockRentalList(String searchArea, int offset, int limit) throws Exception;
	
	// 블럭 반출 반납요청
	public int modReturnRequest(int app_num) throws Exception;
	public int addReturnRequestFormData(int app_num) throws Exception;
	
	// 블럭 반납
	public void modStatusRecipient(int app_num) throws Exception;
	
	// 블럭 이동 기록 수 카운트
	public int getMoveListCount(String searchArea) throws Exception;
	
	// 블럭 이동 기록
	public List<BlockVO> selectBlockMoveList(String searchArea, int offset, int limit) throws Exception;

	// 블럭 검색
	public List<BlockVO> selectSearchList(String searchArea, String searchType, String searchQuery, int offset, int limit, String token) throws Exception;
	
	// 블럭 검색 수 카운트
	public int getSearchListCount(String searchArea, String searchType, String searchQuery, String token) throws Exception;

	// 전체 블럭 보기 수 카운트
	public int getBlockTotalCount(String searchArea) throws Exception;
	
	// 전체 블럭 리스트
	public List<BlockVO> selectBlockTotalList(String searchArea, int offset, int limit) throws Exception;

	// 승인 대기 수 카운트
	public int getApprovalCount(String searchArea) throws Exception;
	
	// 승인 대기 리스트
	public List<BlockVO> selectApprovalList(String searchArea, int offset, int limit) throws Exception;

	// 반출 승인 대기 수 카운트
	public int getExpertApprovalCount(String searchArea) throws Exception;
	
	// 반출 승인 대기 리스트
	public List<BlockVO> selectExpertApprovalList(String searchArea, int offset, int limit) throws Exception;
	
	// 이동 보고서 상세보기
	public BlockVO selectBlockApprovalView(int app_num) throws Exception;
	
	// 반출 이동 보고서 상세보기
	public BlockVO selectExpertBlockApprovalView(int app_num) throws Exception;
	
	// 이동 승인
	public int updateApproval(int app_num, String app_isError, String searchArea) throws Exception;
	
	// 반출 이동 승인
	public int updateExpertApproval(int app_num, String app_isError, String searchArea) throws Exception;
	
	// 반출 이동 승인 (return, final, transMethod 입력(추가))
	public int updateFinalExpertApproval(int app_num, String app_hnd_transMethod, String app_isError) throws Exception;
	
	// 반출 이동 사인 업로드
	public int expertSignUpload(String expSign_name, String app_rcv_area) throws Exception ;
	public int updateExpertSign(int app_num, String expSign_name, String app_rcv_area) throws Exception;

	// 이동 거절
	public int updateRejection(int app_num, String searchArea) throws Exception;
	
	// 반출 거절
	public int updateExpertRejection(int app_num, String searchArea) throws Exception; 
	
	// 블럭 스펙 추가
	public void insertBlockSpec(String df_idNumber, MultipartFile[] files, HttpServletRequest request) throws Exception;

	// 블럭 스펙 보기
	public List<BlockVO> selectBlockSpecView(String df_idNumber) throws Exception;
	
	// 블럭 스펙 삭제
	public void removeBlockSpec(String df_idNumber) throws Exception;
	
	// 블럭 점검 게시판 수 카운트
	public int inspectionListCount(String searchArea) throws Exception;
	
	// 블럭 점검 게시판
	public List<BlockVO> selectInspectionList(String searchArea, int offset, int limit) throws Exception;

	// 블럭 점검 폼
	public List<BlockVO> inspectionList(String searchArea) throws Exception;
	
	// 블럭 점검 추가 (정보저장)
	public int addInspectionBoard(String bib_title, String searchArea) throws Exception;
	public int addInspectionList(List<BlockVO> blockInspectionList) throws Exception;
	
	// 블럭 점검 보기
	public List<BlockVO> blockInspectionView(int bib_num) throws Exception;
	
	// 블럭 점검 삭제
	public int removeInspectionView(int bib_num) throws Exception;
	
	// 블럭 점검 이력 보기
	public int inspectionHistoryCount(String df_idNumber) throws Exception;
	public List<BlockVO> selectInspectionHistory(String df_idNumber, int offset, int limit) throws Exception;
}
