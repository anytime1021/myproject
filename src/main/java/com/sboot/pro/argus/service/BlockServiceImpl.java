package com.sboot.pro.argus.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sboot.pro.argus.dao.BlockDAO;
import com.sboot.pro.argus.vo.BlockVO;
import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.http.HttpServletRequest;

@Service("blockService")
@Transactional(propagation = Propagation.REQUIRED)
public class BlockServiceImpl implements BlockService {
	
	@Autowired
	private BlockDAO blockDAO;
	
	// 블럭 수 카운트
	@Override
	public int getBlockCount(String searchArea) throws Exception {
		return blockDAO.selectBlockCount(searchArea);
	}
	
	// 블럭 리스트
	@Override
	public List<BlockVO> selectBlockList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectBlockList(searchArea, offset, limit);
	}
	
	// 블럭 상세보기
	@Override
	public BlockVO selectBlockView(String df_num) throws Exception {
		return blockDAO.selectBlockView(df_num);
	}
	
	// 블럭 추가 폼 일련번호 체크
	@Override
	public boolean isExistIdNumber(String df_idNumber) throws Exception {
		return blockDAO.isExistIdNumber(df_idNumber) > 0;
	}
	
	// 블럭 추가
	@Override
	public void addBlock(BlockVO addBlockForm, String searchArea) throws Exception {
		blockDAO.insertBlock(addBlockForm, searchArea);
	}
	
	// 블럭 수정
	@Override
	public void modBlock(BlockVO modBlockForm) throws Exception {
		blockDAO.insertUpdateLog(modBlockForm.getDf_num());
		blockDAO.updateBlock(modBlockForm);
	}
	
	// 블럭 삭제
	@Override
	public void removeBlock(String df_idNumber) throws Exception {
		blockDAO.deleteBlock(df_idNumber);
	}
	
	// 블럭 대여
	@Override
	public void modItemStatus(String df_idNumber, String df_moveStatus) throws Exception {
		blockDAO.updateItemStatus(df_idNumber, df_moveStatus);
	}
	
	@Override
	public void addMoveBlockList(BlockVO moveBlock, String login_area) throws Exception {
		blockDAO.insertMoveBlockList(moveBlock, login_area);
	}
	
	// 블럭 외부 반출
	public int addExpertBlock(BlockVO expertBlock) throws Exception {
		return blockDAO.insertExpertBlock(expertBlock);
	}
	
	// 대여한 블럭 수 카운트
	@Override
	public int getRentalListCount(String searchArea) throws Exception {
		return blockDAO.selectRentalListCount(searchArea);
	}
	
	// 대여한 블럭 리스트
	@Override
	public List<BlockVO> selectBlockRentalList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectBlockRentalList(searchArea, offset, limit);
	}
	
	// 블럭 반출 반납요청
	@Override
	public int modReturnRequest(int app_num) throws Exception {
		return blockDAO.updateReturnRequest(app_num);
	}
	
	@Override
	public int addReturnRequestFormData(int app_num) throws Exception {
		return blockDAO.insertReturnRequestFormData(app_num);
	}
	
	// 블럭 반납
	public void modStatusRecipient(int app_num) throws Exception {
		String df_idNumber = blockDAO.selectIdNumber(app_num);
		blockDAO.updateReturnStatus(df_idNumber);
		blockDAO.updateReturnRecipient(df_idNumber);
	}
	
	// 블럭 이동 기록 수 카운트
	@Override
	public int getMoveListCount(String searchArea) throws Exception {
		return blockDAO.selectBlockMoveListCount(searchArea);
	}
	
	// 블럭 이동 기록
	@Override
	public List<BlockVO> selectBlockMoveList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectBlockMoveList(searchArea, offset, limit);
	}
	
	// 블럭 검색
	@Override
	public List<BlockVO> selectSearchList(String searchArea, String searchType, String searchQuery, int offset, int limit, String token) throws Exception {
		if (token.equals("blockList")) {
			return blockDAO.selectSearchList(searchArea, searchType, searchQuery, offset, limit);
		} else if(token.equals("blockMoveList")) {
			List<String> idNumber = blockDAO.selectIdNumberSearch(searchArea, searchType, searchQuery);
			if (idNumber == null || idNumber.isEmpty()) {
				idNumber = new ArrayList<>();
				idNumber.add("1");
			}
			return blockDAO.selectSearchMoveList(idNumber, offset, limit);
		} else if(token.equals("blockRentalList")) {
			List<String> idNumber = blockDAO.selectRentalListId(searchArea);
			return blockDAO.selectSearchRentalList(idNumber, searchType, searchQuery, offset, limit);
		} else if(token.equals("blockTotalList")) {
			return blockDAO.selectSearchTotalList(searchArea, searchType, searchQuery, offset, limit);
		}
		return new ArrayList<BlockVO>();
	}
	
	// 블럭 검색 수 카운트
	@Override
	public int getSearchListCount(String searchArea, String searchType, String searchQuery, String token) throws Exception {
		if ("blockRentalList".equals(token)) {
			List<String> idNumber = blockDAO.selectRentalListId(searchArea);
			if (idNumber == null || idNumber.isEmpty()) {
				idNumber = new ArrayList<>();
				idNumber.add("1");
			}
			return blockDAO.selectListCount(searchArea, searchType, searchQuery, token, idNumber);
		} else if ("blockMoveList".equals(token)) {
			return blockDAO.selectMoveListCount(searchArea, searchType, searchQuery);
		} else if ("blockTotalList".equals(token)) {
			return blockDAO.selectListCount(searchArea, searchType, searchQuery, token, null);
		}
		return blockDAO.selectListCount(searchArea, searchType, searchQuery, token, null);
	}
	
	// 전체 블럭 보기 수 카운트
	@Override
	public int getBlockTotalCount(String searchArea) throws Exception {
			return blockDAO.selectBlockTotalCount(searchArea);
	}
	
	// 전체 블럭 리스트
	@Override
	public List<BlockVO> selectBlockTotalList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectBlockTotalList(searchArea, offset, limit);
	}
	
	// 승인 대기 수 카운트
	@Override
	public int getApprovalCount(String searchArea) throws Exception {
		return blockDAO.selectApprovalCount(searchArea);
	}
	
	// 승인 대기 리스트
	@Override
	public List<BlockVO> selectApprovalList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectApprovalList(searchArea, offset, limit);
	}
	
	// 반출 승인 대기 수 카운트
	@Override
	public int getExpertApprovalCount(String searchArea) throws Exception {
		return blockDAO.selectExpertApprovalCount(searchArea);
	}
	
	// 반출 승인 대기 리스트
	@Override
	public List<BlockVO> selectExpertApprovalList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectExpertApprovalList(searchArea, offset, limit);
	}
	
	// 이동 보고서 상세보기
	@Override
	public BlockVO selectBlockApprovalView(int app_num) throws Exception {
//		String df_idNumber = blockDAO.selectBlockApprovalView_df_idNumber(app_num);
//		return blockDAO.selectBlockApprovalView(df_idNumber);
		return blockDAO.selectBlockApprovalView(app_num);
	}
	
	// 반출 이동 보고서 상세보기
	@Override
	public BlockVO selectExpertBlockApprovalView(int app_num) throws Exception {
		int expSign_num = blockDAO.selectExpSign_num(app_num);
		return blockDAO.selectExpertBlockApprovalView(app_num, expSign_num);
	}
	
	// 이동 승인
	@Override
	public int updateApproval(int app_num, String app_isError, String searchArea) throws Exception {
		return blockDAO.updateApproval(app_num, app_isError, searchArea);
	}
	
	// 반출 이동 승인
	@Override
	public int updateExpertApproval(int app_num, String app_isError, String searchArea) throws Exception {
		return blockDAO.updateExpertApproval(app_num, app_isError, searchArea);
	}
	
	// 반출 이동 승인 (return, final, transMethod 입력(추가))
	@Override
	public int updateFinalExpertApproval(int app_num, String app_hnd_transMethod, String app_isError) throws Exception {
		return blockDAO.updateFinalExpertApproval(app_num, app_hnd_transMethod, app_isError);
	}
	
	// 반출 이동 사인 업로드
	@Override
	public int expertSignUpload(String expSign_name, String app_rcv_area) throws Exception {
		return blockDAO.insertExpertSignUpload(expSign_name, app_rcv_area);
	}
	
	@Override
	public int updateExpertSign(int app_num, String expSign_name, String app_rcv_area) throws Exception {
		return blockDAO.updateExpertSign(app_num, expSign_name, app_rcv_area);
	}
	
	// 이동 거절
	@Override
	public int updateRejection(int app_num, String searchArea) throws Exception {
		return blockDAO.updateRejection(app_num, searchArea);
	}
	
	// 반출 거절
	@Override
	public int updateExpertRejection(int app_num, String app_isError, int token, String app_type) throws Exception {
		int result1 = blockDAO.updateExpertRejection(app_num, app_isError, token, app_type);
		int count = blockDAO.countExpertRejection(app_num);
		int result2 = blockDAO.rejectionRollbackData(app_num, count);
		return result1;
	}
	
	// 블럭 스펙 업로드
	@Override
	public void insertBlockSpec(String df_idNumber, MultipartFile[] files, HttpServletRequest request) throws Exception {
		String uploadDir = request.getServletContext().getRealPath("/resources/img/bSpec");
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		for (MultipartFile file : files) {
		    if (!file.isEmpty()) {
		        try (InputStream inputStream = file.getInputStream()) {
		            BufferedImage originalImage = ImageIO.read(inputStream);
		            if (originalImage == null) continue;

		            int originalWidth = originalImage.getWidth();
		            int originalHeight = originalImage.getHeight();
		            int targetHeight = 300;
		            int targetWidth = (originalWidth * targetHeight) / originalHeight;

		            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		            Graphics2D g2d = resizedImage.createGraphics();
		            g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		            g2d.dispose();

		            // 파일명 중복 방지 + 확장자 유지
		            String originalName = file.getOriginalFilename();
		            String ext = originalName.substring(originalName.lastIndexOf(".") + 1);
		            String savedName = df_idNumber + "_" + System.currentTimeMillis() + "." + ext;

		            File outputFile = new File(uploadDir, savedName);
		            ImageIO.write(resizedImage, ext, outputFile);

		            BlockVO img = new BlockVO();
		            img.setDf_idNumber(df_idNumber);
		            img.setFile_name(savedName);
		            img.setFile_path(outputFile.getAbsolutePath());

		            blockDAO.insertBlockSpec(df_idNumber, img, searchArea);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
//		for (MultipartFile file : files) {
//			if (!file.isEmpty()) {
//				try { 
//					String filePath = uploadDir + file.getOriginalFilename(); 
//					file.transferTo(new File(filePath)); BlockVO img = new BlockVO(); 
//					img.setDf_idNumber(df_idNumber); 
//					img.setFile_name(file.getOriginalFilename());
//					img.setFile_path(filePath); 
//					blockDAO.insertBlockSpec(df_idNumber, img); 
//				}
//			catch (Exception e) { 
//				e.printStackTrace(); 
//			} 
//		} 
	}
	
	// 블럭 스펙 보기
	@Override
	public List<BlockVO> selectBlockSpecView(String df_num) throws Exception {
		return blockDAO.selectBlockSpecView(df_num);
	}
	
	// 블럭 스펙 삭제
	@Override
	public void removeBlockSpec(String df_idNumber) throws Exception {
		blockDAO.deleteBlockSpec(df_idNumber);
	}
	
	// 블럭 점검 게시판 수 카운트
	@Override
	public int inspectionListCount(String searchArea) throws Exception {
		return blockDAO.inspectionListCount(searchArea);
	}
	
	// 블럭 점검 리스트
	@Override
	public List<BlockVO> selectInspectionList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectInspectionList(searchArea, offset, limit);
	}
	
	// 블럭 점검 폼
	@Override
	public List<BlockVO> inspectionList(String searchArea) throws Exception {
		return blockDAO.inspectionList(searchArea);
	}
	
	// 블럭 점검 추가 (정보저장)
	@Override
	public int addInspectionBoard(String bib_title, String searchArea) throws Exception {
		return blockDAO.insertInspectionBoard(bib_title, searchArea);
	}
	
	@Override
	public int addInspectionList(List<BlockVO> blockInspectionList) throws Exception {
		int bib_num = blockDAO.selectBib_num(blockInspectionList.get(0).getLogin_area());
		
		for (BlockVO vo : blockInspectionList) {
			vo.setBib_num(bib_num);
		}
		
		return blockDAO.insertInspectionList(blockInspectionList);
	}
	
	// 블럭 점검 보기
	@Override
	public List<BlockVO> blockInspectionView(int bib_num) throws Exception {
		return blockDAO.selectBlockInspectionView(bib_num);
	}
	
	// 블럭 점검 삭제
	@Override
	public int removeInspectionView(int bib_num) throws Exception {
		return blockDAO.deleteInspectionView(bib_num);
	}
	
	// 블럭 점검 이력 보기
	@Override
	public int inspectionHistoryCount(String df_idNumber) throws Exception {
		return blockDAO.inspectionHistoryCount(df_idNumber);
	}
	
	// 블럭 제작 요청 게시판
	@Override
	public int createBlockListCount(String searchArea) throws Exception {
		return blockDAO.createBlockListCount(searchArea);
	}
	
	@Override
	public List<BlockVO> selectCreateBlockList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectCreateBlockList(searchArea, offset, limit);
	}
	
	@Override
	public List<BlockVO> selectInspectionHistory(String df_idNumber, int offset, int limit) throws Exception {
		return blockDAO.selectInspectionHistory(df_idNumber, offset, limit);
	}
	
	// 블럭 제작 (정보 저장)
	@Override
	@Transactional
	public int addCreateBlock(BlockVO createBlockForm, MultipartFile[] cbd_drawings, String searchArea, HttpServletRequest request) throws Exception {
		
		blockDAO.insertCreateBlockBoard(createBlockForm, searchArea);
		blockDAO.insertCreateBlockInformation(createBlockForm, searchArea);
		
		String uploadDir = request.getServletContext().getRealPath("/resources/img/bCreate");
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		List<BlockVO> imgList = new ArrayList<>();
		for (MultipartFile cbd_drawing : cbd_drawings) {
		    if (!cbd_drawing.isEmpty()) {
		        try (InputStream inputStream = cbd_drawing.getInputStream()) {
		            BufferedImage originalImage = ImageIO.read(inputStream);
		            if (originalImage == null) continue;

		            int originalWidth = originalImage.getWidth();
		            int originalHeight = originalImage.getHeight();
		            int targetHeight = 300;
		            int targetWidth = (originalWidth * targetHeight) / originalHeight;

		            BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
		            Graphics2D g2d = resizedImage.createGraphics();
		            g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
		            g2d.dispose();

		            // 파일명 중복 방지 + 확장자 유지
		            String originalName = cbd_drawing.getOriginalFilename();
		            String ext = originalName.substring(originalName.lastIndexOf(".") + 1);
		            String savedName = "blockNumber" + createBlockForm.getCreateBlock_num() + "_" + System.currentTimeMillis() + "." + ext;

		            File outputFile = new File(uploadDir, savedName);
		            ImageIO.write(resizedImage, ext, outputFile);

		            BlockVO img = new BlockVO();
		            img.setCbd_drawing(savedName);
		            img.setCreateBlock_num(createBlockForm.getCreateBlock_num());
		            imgList.add(img);
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		}
		return blockDAO.insertCreateBlockDrawing(imgList);
	}
	
	// 블럭 제작 상세보기
	@Override
	public BlockVO selectCreateBlockView(int createBlockBoard_num) throws Exception {
		return blockDAO.selectCreateBlockView(createBlockBoard_num);
	}
	
	// 블럭 제작 도면 보기
	@Override
	public List<BlockVO> selectDrawingView(int createBlock_num) throws Exception {
		return blockDAO.selectDrawingView(createBlock_num);
	}
	
	// 블럭 제작 승인
	@Override
	public int modCreateBlockApproval(int createBlock_num, String technical_team_comment) throws Exception {
		return blockDAO.updateCreateBlockApproval(createBlock_num, technical_team_comment);
	}
	
	// 블럭 제작 거절
	@Override
	public int modCreateBlockRejection(int createBlock_num, String technical_team_comment) throws Exception {
		return blockDAO.updateCreateBlockRejection(createBlock_num, technical_team_comment);
	}
}
