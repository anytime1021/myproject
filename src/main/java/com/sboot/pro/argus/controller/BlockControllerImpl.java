package com.sboot.pro.argus.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.PagingDTO;
import com.sboot.pro.argus.service.BlockService;
import com.sboot.pro.argus.vo.BlockVO;
import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller("blockManagementController")
public class BlockControllerImpl implements BlockController {
	
	@Autowired
	BlockService blockService;
	
	// 블럭 리스트
	@Override
	@GetMapping("/blockManagement/blockList.do")
	public ModelAndView blockList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockList");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();

		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getBlockCount(searchArea);
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
				
		List<BlockVO> blockList = blockService.selectBlockList(searchArea, paging.getOffset(), limit);
		
		mav.addObject("paging", paging);
		mav.addObject("blockList", blockList);
		return mav;
	}
	
	// 블럭 상세보기
	@Override
	@PostMapping("/blockManagement/blockView.do")
	public ModelAndView blockView(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockView");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
//		String compare_block = df_idNumber.substring(2,4);
//		String compare_login = "";
//		if (searchArea.equals("서산")) {
//			compare_login = "SS";
//		} else if (searchArea.equals("울산")) {
//			compare_login = "US";
//		} else if (searchArea.equals("창원")) {
//			compare_login = "CW";
//		} else if (searchArea.equals("마산")) {
//			compare_login = "MS";
//		} else if (searchArea.equals("여수")) {
//			compare_login = "YS";
//		} else if (searchArea.equals("본사")) {
//			compare_login = "본사";
//		}
//		
//		System.out.println(compare_block);
//		System.out.println(compare_login);
//		
//		if (compare_block.equals(compare_login) || compare_login.equals("본사") ) {
//			BlockVO blockView = blockService.selectBlockView(df_idNumber);
//			mav.addObject("blockView", blockView);
//			return mav;
//		}
		BlockVO blockView = blockService.selectBlockView(df_idNumber);
		mav.addObject("blockView", blockView);
		return mav;
	}
	
	// 블럭 추가 폼
	@Override
	@GetMapping("/blockManagement/addBlockForm.do")
	public ModelAndView addBlockForm(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/addBlockForm");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		mav.addObject("searchArea", searchArea);
		return mav;
	}
	
	// 블럭 추가 폼 일련번호 체크
	@ResponseBody
	@GetMapping("/blockManagement/checkDuplicateIdNumber.do")
	public String checkDuplicateIdNumber(@RequestParam("idNumber") String idNumber) throws Exception {
		boolean exists = blockService.isExistIdNumber(idNumber);
		return exists ? "duplicate" : "available";
	}
	
	// 블럭 추가
	@Override
	@PostMapping("/blockManagement/addBlock.do")
	public ModelAndView addBlock(@ModelAttribute("addBlockForm") BlockVO addblockForm, HttpServletRequest request) throws Exception {
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		MultipartFile uploadFile = addblockForm.getDf_picture();
		
		if (!uploadFile.isEmpty()) {
			String uploadDir = request.getServletContext().getRealPath("/resources/img/");
			File dir = new File(uploadDir);
			if (!dir.exists()) dir.mkdirs();
			
			String originalName = uploadFile.getOriginalFilename();
			String savedName = System.currentTimeMillis() + "_" + originalName;
			
			InputStream inputStream = uploadFile.getInputStream();
			BufferedImage originalImage = ImageIO.read(inputStream);
			
			int originalWidth = originalImage.getWidth();
			int originalHeight = originalImage.getHeight();
			
			int targetHeight = 300;
			int targetWidth = (originalWidth * targetHeight) / originalHeight;
			

			BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
			g2d.dispose();
			
			File outputFile = new File(uploadDir + File.separator + savedName);
			ImageIO.write(resizedImage, "jpg", outputFile);
			
			addblockForm.setDf_pictureName(savedName);
		}
		blockService.addBlock(addblockForm, searchArea);
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/addBlockForm.do");
		return mav;
	}
	
	// 블럭 수정 폼
	@Override
	@GetMapping("/blockManagement/modBlockForm.do")
	public ModelAndView modBlockForm(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/modBlockForm");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		String compare_block = df_idNumber.substring(2,4);
		String compare_login = "";
		if (searchArea.equals("서산")) {
			compare_login = "SS";
		} else if (searchArea.equals("울산")) {
			compare_login = "US";
		} else if (searchArea.equals("창원")) {
			compare_login = "CW";
		} else if (searchArea.equals("마산")) {
			compare_login = "MS";
		} else if (searchArea.equals("여수")) {
			compare_login = "YS";
		} else if (searchArea.equals("본사")) {
			compare_login = "본사";
		}
		
		if (compare_block.equals(compare_login) || compare_login.equals("본사") ) {
			BlockVO blockView = blockService.selectBlockView(df_idNumber);
			int imageNameIndex = blockView.getDf_pictureName().indexOf("A");
			String imageName = blockView.getDf_pictureName().substring(imageNameIndex);
			mav.addObject("imageName", imageName);
			mav.addObject("blockView", blockView);
			return mav;
		}

		return new ModelAndView("/argus/main2");
	}
	
	// 블럭 수정
	@Override
	@PostMapping("/blockManagement/modBlock.do")
	public ModelAndView modBlock(@ModelAttribute("modBlock") BlockVO modBlockForm, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockList.do");
		
		if (!modBlockForm.getDf_picture().isEmpty()) {
			
			MultipartFile uploadFile = modBlockForm.getDf_picture();
			
			if (!uploadFile.isEmpty()) {
				String uploadDir = request.getServletContext().getRealPath("/resources/img/");
				File dir = new File(uploadDir);
				if (!dir.exists()) dir.mkdirs();
				
				String originalName = uploadFile.getOriginalFilename();
				String savedName = System.currentTimeMillis() + "_" + originalName;
				
				InputStream inputStream = uploadFile.getInputStream();
				BufferedImage originalImage = ImageIO.read(inputStream);
				
				int originalWidth = originalImage.getWidth();
				int originalHeight = originalImage.getHeight();
				
				int targetHeight = 300;
				int targetWidth = (originalWidth * targetHeight) / originalHeight;
				
	
				BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D g2d = resizedImage.createGraphics();
				g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
				g2d.dispose();
				
				File outputFile = new File(uploadDir + File.separator + savedName);
				ImageIO.write(resizedImage, "jpg", outputFile);
				
				modBlockForm.setDf_pictureName(savedName);
			}
		}
		blockService.modBlock(modBlockForm);
		return mav;
	}
	
	// 블럭 삭제
	@Override
	@GetMapping("/blockManagement/removeBlock.do")
	public ModelAndView removeBlock(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockList.do");
		blockService.removeBlock(df_idNumber);
		return mav;
	}
	
	// 블럭 대여 등록 폼
	@Override
	@GetMapping("/blockManagement/moveBlockForm.do")
	public ModelAndView moveBlockForm(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/moveBlockForm");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		BlockVO blockInformation = blockService.selectBlockView(df_idNumber);
		mav.addObject("searchArea", searchArea);
		mav.addObject("blockInformation", blockInformation);
		return mav;
	}
	
	// 블럭 대여
	@Override
	@PostMapping("/blockManagement/moveBlock.do")
	public ModelAndView moveBlock(@ModelAttribute("moveBlock") BlockVO moveBlock, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockMoveList.do");
		LoginVO login = (LoginVO) request.getAttribute("login");
		blockService.modItemStatus(moveBlock.getDf_idNumber(), moveBlock.getMoveList_recipient_area());
		blockService.addMoveBlockList(moveBlock, login.getLogin_area(), login.getLogin_id());
		return mav;
	}
	
	// 대여한 블럭 리스트
	@Override
	@GetMapping("/blockManagement/blockRentalList.do")
	public ModelAndView blockRentalList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockRentalList");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getMoveListCount(searchArea);
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> rentalList = blockService.selectBlockRentalList(searchArea, paging.getOffset(), limit);
		mav.addObject("paging", paging);
		mav.addObject("rentalList", rentalList);
		return mav;
	}
	
	// 블럭 반납
	@Override
	@PostMapping("/blockManagement/returnBlock.do")
	public ModelAndView retrunBlock(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockRentalList.do");
		blockService.modStatusRecipient(df_idNumber);
		return mav;
	}
	
	// 블럭 이동 기록
	@Override
	@GetMapping("/blockManagement/blockMoveList.do")
	public ModelAndView blockMoveList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockMoveList");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getMoveListCount(searchArea);
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> blockMoveList = blockService.selectBlockMoveList(searchArea, paging.getOffset(), limit);
		mav.addObject("searchArea", searchArea);
		mav.addObject("paging", paging);
		mav.addObject("blockMoveList", blockMoveList);
		return mav;
	}
	
	// 블럭 검색
	@Override
	@GetMapping("/blockManagement/searchList.do")
	public ModelAndView searchList(@RequestParam(value="page", defaultValue="1") int page, @RequestParam("searchType") String searchType, @RequestParam("searchQuery") String searchQuery, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/searchList");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getSearchListCount(searchArea, searchType, searchQuery);
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> searchList = blockService.selectSearchList(searchArea, searchType, searchQuery, paging.getOffset(), limit);
		
		mav.addObject("searchType", searchType);
		mav.addObject("searchQuery", searchQuery);
		mav.addObject("searchList", searchList);
		mav.addObject("paging", paging);
		return mav;
	}
	
	@GetMapping("/blockManagement/test.do")
	public ModelAndView test(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/test");
		return mav;
	}
}
