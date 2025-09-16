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
	public BlockVO selectBlockView(String df_idNumber) throws Exception {
		return blockDAO.selectBlockView(df_idNumber);
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
	public void addMoveBlockList(BlockVO moveBlock, String login_area, String login_id) throws Exception {
		blockDAO.insertMoveBlockList(moveBlock, login_area, login_id);
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
	
	// 블럭 반납
	public void modStatusRecipient(int app_num) throws Exception {
		String df_idNumber = blockDAO.selectIdNumber(app_num);
		blockDAO.updateReturnStatus(df_idNumber);
		blockDAO.updateReturnRecipient(app_num);
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
	
	// 이동 보고서 상세보기
	@Override
	public BlockVO selectBlockApprovalView(int app_num) throws Exception {
//		String df_idNumber = blockDAO.selectBlockApprovalView_df_idNumber(app_num);
//		return blockDAO.selectBlockApprovalView(df_idNumber);
		return blockDAO.selectBlockApprovalView(app_num);
	}
	
	// 이동 승인
	@Override
	public int updateApproval(int app_num, String searchArea) throws Exception {
		return blockDAO.updateApproval(app_num, searchArea);
	}
	
	// 이동 거절
	@Override
	public int updateRejection(int app_num, String app_comment, String searchArea) throws Exception {
		return blockDAO.updateRejection(app_num, app_comment, searchArea);
	}
	
	// 블럭 스펙 업로드
	@Override
	public void insertBlockSpec(String df_idNumber, MultipartFile[] files, HttpServletRequest request) throws Exception {
		String uploadDir = request.getServletContext().getRealPath("/resources/img/");
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	
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

		            blockDAO.insertBlockSpec(df_idNumber, img);
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
	public List<BlockVO> selectBlockSpecView(String df_idNumber) throws Exception {
		return blockDAO.selectBlockSpecView(df_idNumber);
	}
}
