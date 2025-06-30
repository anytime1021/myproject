package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.BlockVO;

@Mapper
@Repository("blockDAO")
public interface BlockDAO {
	public int selectBlockCount(String searchArea) throws Exception;
	
	public List<BlockVO> selectBlockList(@Param("searchArea") String searchArea, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
}
