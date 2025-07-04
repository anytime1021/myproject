package com.sboot.pro.argus.service;

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
	
	// 블럭 이동 기록
	@Override
	public List<BlockVO> selectBlockMoveList(String searchArea) throws Exception {
		return blockDAO.selectBlockMoveList(searchArea);
	}
}
