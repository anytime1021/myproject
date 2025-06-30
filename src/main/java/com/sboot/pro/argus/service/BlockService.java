package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.vo.BlockVO;

public interface BlockService {
	public int getBlockCount(String searchArea) throws Exception;
	
	public List<BlockVO> selectBlockList(String searchArea, int offset, int limit) throws Exception;
}
