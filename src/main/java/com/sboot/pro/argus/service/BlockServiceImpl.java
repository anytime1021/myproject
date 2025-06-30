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
	
	@Override
	public int getBlockCount(String searchArea) throws Exception {
		return blockDAO.selectBlockCount(searchArea);
	}
	
	@Override
	public List<BlockVO> selectBlockList(String searchArea, int offset, int limit) throws Exception {
		return blockDAO.selectBlockList(searchArea, offset, limit);
	}
}
