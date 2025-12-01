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
	public BlockVO selectBlockView(String df_num) throws Exception;
	
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
	public void insertMoveBlockList(@Param("moveBlock") BlockVO moveBlock, @Param("login_area") String login_area) throws Exception;
	
	// 블럭 외부 반출
	public int insertExpertBlock(BlockVO expertBlock) throws Exception;
	
	// 대여한 블럭 수 카운트
	public int selectRentalListCount(String searchArea) throws Exception;

	// 대여한 블럭 리스트
	public List<BlockVO> selectBlockRentalList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 블럭 반출 반납요청
	public int updateReturnRequest(int app_num) throws Exception;
	public int insertReturnRequestFormData(int app_num) throws Exception;
	
	// 블럭 반납
	public void updateReturnWaiting(int app_num) throws Exception;
	public void finalApprovalReturn(@Param("df_idNumber") String df_idNumber, @Param("app_rcv_area") String app_rcv_area, @Param("searchArea") String searchArea, @Param("app_num") int app_num) throws Exception;
	
	// 블럭 반납 - 구
	public String selectIdNumber(int app_num) throws Exception;
	public void updateReturnStatus(String df_idNumber) throws Exception;
	public void updateReturnRecipient(String df_idNumber) throws Exception;
	
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

	// 반출 승인 대기 수 카운트
	public int selectExpertApprovalCount(String searchArea) throws Exception;
	
	// 반출 승인 대기 리스트
	public List<BlockVO> selectExpertApprovalList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 이동 보고서 상세보기
	public BlockVO selectBlockApprovalView_df_idNumber(int app_num) throws Exception;
	public BlockVO selectBlockApprovalView(int app_num) throws Exception;
//	public BlockVO ApprovalDivision(int app_num) throws Exception;
	
	// 반출 이동 보고서 상세보기
	public BlockVO selectExpertBlockApprovalView_df_idNumber(int app_num) throws Exception;
	public int selectExpSign_num(int app_num) throws Exception;
	public BlockVO selectExpertBlockApprovalView(@Param("app_num") int app_num, @Param("expSign_num") int expSign_num) throws Exception;
	
	// 이동 승인
	public int updateApproval(@Param("app_num") int app_num, @Param("app_isError") String app_isError, @Param("searchArea") String searchArea) throws Exception;
	public int tnfCheck(int app_num) throws Exception;
	public void finalApproval(@Param("df_idNumber") String df_idNumber, @Param("app_rcv_area") String app_rcv_area, @Param("searchArea") String searchArea, @Param("app_num") int app_num) throws Exception;
	
	// 반출 이동 승인
	public int updateExpertApproval(@Param("app_num") int app_num, @Param("app_isError") String app_isError, @Param("searchArea") String searchArea) throws Exception;
	public int tnfExpertCheck(int app_num) throws Exception;
	public void finalExpertApproval(@Param("df_idNumber") String df_idNumber, @Param("app_rcv_area") String app_rcv_area, @Param("searchArea") String searchArea, @Param("app_num") int app_num) throws Exception;

	// 반출 이동 승인 (return, final, transMethod 입력(추가))
	public int updateFinalExpertApproval(@Param("app_num") int app_num, @Param("app_hnd_transMethod") String app_hnd_transMethod, @Param("app_isError") String app_isError) throws Exception;

	// 반출 이동 사인 업로드
	public int distinctCheckExpertSign(@Param("expSign_name") String expSign_name, @Param("app_rcv_area") String app_rcv_area) throws Exception;
	public int insertExpertSignUpload(@Param("expSign_name") String expSign_name, @Param("app_rcv_area") String app_rcv_area) throws Exception;
	public int updateExpertSign(@Param("app_num") int app_num, @Param("expSign_name") String expSign_name, @Param("app_rcv_area") String app_rcv_area) throws Exception;
	
	public int checkFileName(String expSign_name) throws Exception;
	// 이동 거절
	public int updateRejection(@Param("app_num") int app_num, @Param("searchArea") String searchArea) throws Exception;
	public void finalRejection(int app_num) throws Exception;
	
	// 반출 거절
	public int updateExpertRejection(@Param("app_num") int app_num, @Param("app_isError") String app_isError, @Param("token") int token, @Param("app_type") String app_type) throws Exception;
	public int countExpertRejection(int app_num) throws Exception;
	public int rejectionRollbackData(@Param("app_num") int app_num, @Param("count") int count) throws Exception;
	//	public void finalRejection(int app_num) throws Exception;
	
	// 블럭 스펙 추가
	public void insertBlockSpec(@Param("df_idNumber") String df_idNumber, @Param("img") BlockVO img, @Param("searchArea") String searchArea) throws Exception;
	
	// 블럭 스펙 보기
	public List<BlockVO> selectBlockSpecView(String df_idNumber) throws Exception;

	// 블럭 스펙 삭제
	public void deleteBlockSpec(String df_idNumber) throws Exception;
	
	// 블럭 점검 게시판 수 카운트
	public int inspectionListCount(String searchArea) throws Exception;
	
	// 블럭 점검 리스트
	public List<BlockVO> selectInspectionList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 블럭 점검 폼
	public List<BlockVO> inspectionList(String searchArea) throws Exception;
	
	// 블럭 점검 추가 (정보저장)
	public int insertInspectionBoard(@Param("bib_title") String bib_title, @Param("searchArea") String searchArea) throws Exception;
	public int selectBib_num(String searchArea) throws Exception;
	public int insertInspectionList(@Param("blockInspectionList") List<BlockVO> blockInspectionList) throws Exception;

	// 블럭 점검 보기
	public List<BlockVO> selectBlockInspectionView(int bib_num) throws Exception;
	public String selectInspectionTitle(int bib_num) throws Exception;
	
	// 블럭 점검 삭제
	public int deleteInspectionView(int bib_num) throws Exception;
	
	// 블럭 점검 이력 보기
	public int inspectionHistoryCount(String df_idNumber) throws Exception;
	public List<BlockVO> selectInspectionHistory(@Param("df_idNumber") String df_idNumber, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 블럭 제작 요청 게시판
	public int createBlockListCount(String searchArea) throws Exception;
	public List<BlockVO> selectCreateBlockList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 블럭 제작 (정보 저장)
	public void insertCreateBlockBoard(@Param("createBlockForm") BlockVO createBlockForm, @Param("searchArea") String searchArea) throws Exception;
	public void insertCreateBlockInformation(@Param("createBlockForm") BlockVO createBlockForm, @Param("searchArea") String searchArea) throws Exception;
	public int insertCreateBlockDrawing(@Param("imgList") List<BlockVO> imgList) throws Exception;

	// 블럭 제작 상세보기
	public BlockVO selectCreateBlockView(int createBlockBoard_num) throws Exception;
	
	// 블럭 제작 도면 보기
	public List<BlockVO> selectDrawingView(int createBlock_num) throws Exception;
	
	// 블럭 제작 승인
	public int updateCreateBlockApproval(@Param("createBlock_num") int createBlock_num, @Param("technical_team_comment") String technical_team_comment) throws Exception;

	// 블럭 제작 거절
	public int updateCreateBlockRejection(@Param("createBlock_num") int createBlock_num, @Param("technical_team_comment") String technical_team_comment) throws Exception;

	
	// 2025-11-24 이후 기능 구현 - 검색, 삭제 등 (시간 여건상 바로 DAO 직행, 필요시 Service 경유)

	// 승인 대기 리스트 검색기능
	public int searchApprovalCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	public List<BlockVO> searchApproval(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery,
			@Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	public int searchExpertApprovalCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	public List<BlockVO> searchExpertApproval(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery,
			@Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 점검 기록 검색
	public int searchInspectionListCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, @Param("startDate") String startDate, @Param("endDate") String endDate) throws Exception;
	public List<BlockVO> searchInspectionList(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery, 
			@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("offset") int offset, @Param("limit") int limit) throws Exception;

	// 블럭 제작 요청 검색
	public int selectCreateListCount(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery) throws Exception;
	public List<BlockVO> searchCreateBlockList(@Param("searchArea") String searchArea, @Param("searchType") String searchType, @Param("searchQuery") String searchQuery,
			@Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 삭제통합
	public int deleteMoveList(int primaryKey) throws Exception;
	public int deleteApprovalList(int primaryKey) throws Exception;
	public int deleteExpertApprovalList(int primaryKey) throws Exception;
	public int deleteInspectionList(int primaryKey) throws Exception;
	public int deleteCreateBlock(int primaryKey) throws Exception;
	
}
