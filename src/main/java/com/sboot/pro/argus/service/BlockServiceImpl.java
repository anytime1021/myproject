package com.sboot.pro.argus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.dao.BlockDAO;
import com.sboot.pro.argus.vo.BlockVO;

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
	public boolean isExistIdNumber(String idNumber) throws Exception {
		return blockDAO.isExistIdNumber(idNumber) > 0;
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
	
	// 대여한 블럭 리스트
	@Override
	public List<BlockVO> selectBlockRentalList(String searchArea, int offset, int limit) throws Exception {
		List<String> idNumber = blockDAO.selectIdNumber(searchArea);
		if (idNumber == null || idNumber.isEmpty()) {
			idNumber = new ArrayList<>();
			idNumber.add("1");
		}
		return blockDAO.selectBlockRentalList(idNumber, offset, limit);
	}
	
	// 블럭 반납
	public void modStatusRecipient(String df_idNumber) throws Exception {
		blockDAO.updateReturnStatus(df_idNumber);
		blockDAO.updateReturnRecipient(df_idNumber);
	}
	
	// 블럭 이동 기록 수 카운트
	@Override
	public int getMoveListCount(String searchArea) throws Exception {
		return blockDAO.selectMoveListCount(searchArea);
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
			return blockDAO.selectSearchMoveList(searchArea, idNumber, offset, limit);
		} else if(token.equals("blockRentalList")) {
			return blockDAO.selectSearchRentalList(searchArea, searchType, searchQuery, offset, limit);
		}
		return new ArrayList<BlockVO>();
	}
	
	// 블럭 검색 수 카운트
	@Override
	public int getSearchListCount(String searchArea, String searchType, String searchQuery, String token) throws Exception {
		return blockDAO.selectListCount(searchArea, searchType, searchQuery, token);
	}
}
