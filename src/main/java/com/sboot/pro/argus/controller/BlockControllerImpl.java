package com.sboot.pro.argus.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.sboot.pro.argus.dao.BlockDAO;
import com.sboot.pro.argus.service.BlockService;
import com.sboot.pro.argus.vo.BlockVO;
import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller("blockManagementController")
public class BlockControllerImpl implements BlockController {
	
	@Autowired
	BlockService blockService;
	
	@Autowired
	BlockDAO blockDAO;
	
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
		if (totalCount == 0) {
			totalCount = 1;
		}
		
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
		
		BlockVO blockView = blockService.selectBlockView(df_idNumber);
		mav.addObject("searchArea", searchArea);
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
	
	// 블럭 추가 폼 일련번호 체크 - ajax
	@ResponseBody
	@GetMapping("/blockManagement/checkDuplicateIdNumber.do")
	public String checkDuplicateIdNumber(@RequestParam("df_idNumber") String df_idNumber) throws Exception {
		boolean exists = blockService.isExistIdNumber(df_idNumber);
		return exists ? "duplicate" : "available";
	}
	
	// 블럭 추가
	@Override
	@PostMapping("/blockManagement/addBlock.do")
	public ModelAndView addBlock(@ModelAttribute("addBlockForm") BlockVO addBlockForm, HttpServletRequest request) throws Exception {
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		MultipartFile uploadFile = addBlockForm.getDf_picture();
		
		if (!uploadFile.isEmpty()) {
				String uploadDir = request.getServletContext().getRealPath("/resources/img/");
				File dir = new File(uploadDir);
				if (!dir.exists()) dir.mkdirs();
				
		        String originalName = addBlockForm.getDf_picture().getOriginalFilename();
		        String ext = originalName.substring(originalName.lastIndexOf(".") + 1);
		        String savedName = addBlockForm.getDf_idNumber() + "." + ext;
		        
				try (InputStream inputStream = uploadFile.getInputStream()) {
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
				
				addBlockForm.setDf_pictureName(savedName);
			}
		} else {
			addBlockForm.setDf_pictureName("A-NoImage.jpg");
		}
		
		if (addBlockForm.getDf_manufacture().isEmpty()) {
			addBlockForm.setDf_manufacture(null);
		}
		addBlockForm.setDf_usage(addBlockForm.getDf_idNumber().substring(6,8));
		blockService.addBlock(addBlockForm, searchArea);
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockList.do");
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
			if (blockView.getDf_pictureName().isEmpty() || blockView.getDf_pictureName() == null) {
				blockView.setDf_pictureName("A-NoImage.jpg");
			}
			int imageNameIndex = blockView.getDf_pictureName().indexOf("A");
			String imageName = blockView.getDf_pictureName().substring(imageNameIndex);
			mav.addObject("imageName", imageName);
			mav.addObject("blockView", blockView);
			mav.addObject("searchArea", searchArea);
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

		        String originalName = modBlockForm.getDf_picture().getOriginalFilename();
		        String ext = originalName.substring(originalName.lastIndexOf(".") + 1);
		        String savedName = modBlockForm.getDf_idNumber() + "." + ext;

		        try (InputStream inputStream = uploadFile.getInputStream()) {
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
		}
		modBlockForm.setDf_usage(modBlockForm.getDf_idNumber().substring(6,8));
		blockService.modBlock(modBlockForm);
		return mav;
	}
	
	// 블럭 삭제
	@Override
	@GetMapping("/blockManagement/removeBlock.do")
	public ModelAndView removeBlock(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockApproval.do");
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
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String timeNow = now.format(formatter);

		mav.addObject("timeNow", timeNow);
		mav.addObject("searchArea", searchArea);
		mav.addObject("blockInformation", blockInformation);
		return mav;
	}
	
	// 블럭 대여 (이동)
	@Override
	@PostMapping("/blockManagement/moveBlock.do")
	public ModelAndView moveBlock(@ModelAttribute("moveBlock") BlockVO moveBlock, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockApproval.do");
		LoginVO login = (LoginVO) request.getAttribute("login");
		if(moveBlock.getApp_rcv_create_at().equals("") || moveBlock.getApp_rcv_create_at().isEmpty()) {
			moveBlock.setApp_rcv_create_at(null);
		}
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
		int totalCount = blockService.getRentalListCount(searchArea);
		if (totalCount == 0) {
			totalCount = 1;
		}
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> rentalList = blockService.selectBlockRentalList(searchArea, paging.getOffset(), limit);
		mav.addObject("paging", paging);
		mav.addObject("rentalList", rentalList);
		return mav;
	}
	
	// 블럭 반납
	@Override
	@PostMapping("/blockManagement/returnBlock.do")
	public ModelAndView retrunBlock(@RequestParam("app_num_Str") String app_num_Str, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockRentalList.do");
		int app_num = Integer.parseInt(app_num_Str);
		System.out.println(app_num);
		blockService.modStatusRecipient(app_num);
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
		if (totalCount == 0) {
			totalCount = 1;
		}
		
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
	public ModelAndView searchList(@RequestParam(value="page", defaultValue="1") int page, @RequestParam("searchType") String searchType, @RequestParam("searchQuery") String searchQuery, @RequestParam("token") String token, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getSearchListCount(searchArea, searchType, searchQuery, token);
		if (totalCount == 0) {
			totalCount = 1;
		}
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> searchList = blockService.selectSearchList(searchArea, searchType, searchQuery, paging.getOffset(), limit, token);

		mav.addObject("token", token);
		mav.addObject("searchType", searchType);
		mav.addObject("searchQuery", searchQuery);
		mav.addObject("searchList", searchList);
		mav.addObject("paging", paging);
		
		if(token.equals("blockMoveList")) {
			mav.setViewName("/blockManagement/searchMoveList");
		} else {
			mav.setViewName("/blockManagement/searchList");
		}
		
		return mav;
	}
	
	// 전체 블럭 보기
	@Override
	@GetMapping("/blockManagement/blockTotalList.do")
	public ModelAndView blockTotalList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getBlockTotalCount(searchArea);
		if (totalCount == 0) {
			totalCount = 1;
		}
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> blockTotalList = blockService.selectBlockTotalList(searchArea, paging.getOffset(), limit);
		mav.addObject("paging", paging);
		mav.addObject("blockTotalList", blockTotalList);
		
		return mav;
	}
	
	// 승인 대기 리스트
	@Override
	@GetMapping("/blockManagement/blockApproval.do")
	public ModelAndView blockApproval(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockApproval");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.getApprovalCount(searchArea);
		if (totalCount == 0) {
			totalCount = 1;
		}
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> ApprovalList = blockService.selectApprovalList(searchArea, paging.getOffset(), limit);
		mav.addObject("paging", paging);
		mav.addObject("ApprovalList", ApprovalList);
		return mav;
	}
	
	// 이동 보고서 상세보기
	@Override
	@PostMapping("/blockManagement/blockApprovalView.do")
	public ModelAndView blockApprovalView(@RequestParam("app_num_Str") String app_num_Str, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockApprovalView");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		int app_num = Integer.parseInt(app_num_Str);
		BlockVO ApprovalView = blockService.selectBlockApprovalView(app_num);
		BlockVO ApprovalDivision = blockDAO.ApprovalDivision(app_num);
		mav.addObject("searchArea", searchArea);
		mav.addObject("ApprovalView", ApprovalView);
		mav.addObject("ApprovalDivision", ApprovalDivision);
		return mav;
	}
	
	// 이동 승인
	@Override
	@GetMapping("/blockManagement/updateApproval.do")
	public ModelAndView updateApproval(@RequestParam("app_num") int app_num, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockApproval.do");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();

		int result = blockService.updateApproval(app_num, searchArea);
		int tnf = blockDAO.tnfCheck(app_num);
		if (tnf == 1) {
			BlockVO approval = blockDAO.selectBlockApprovalView_df_idNumber(app_num);
			blockDAO.finalApproval(approval.getDf_idNumber(), approval.getApp_rcv_area(), searchArea, app_num);
		}
		return mav;
	}
	
	// 이동 거절
	@Override
	@GetMapping("/blockManagement/updateRejection.do")
	public ModelAndView updateRejection(@RequestParam("app_num_Str") String app_num_Str, @RequestParam("app_comment") String app_comment, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockApproval.do");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		int app_num = Integer.parseInt(app_num_Str);

		int result = blockService.updateRejection(app_num, app_comment, searchArea);
		blockDAO.finalRejection(app_num);
		return mav;
	}
	
	// 블럭 스펙 추가 폼
	@Override
	@GetMapping("/blockManagement/addBlockSpecForm.do")
	public ModelAndView addBlockSpecForm(@RequestParam("df_idNumber") String df_idNumber) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/addBlockSpecForm");
		mav.addObject("df_idNumber", df_idNumber);
		return mav;
	}
	
	// 블럭 스펙 추가
	@Override
	@PostMapping("/blockManagement/addBlockSpec.do")
	public ModelAndView addBlockSpec(@RequestParam("df_idNumber") String df_idNumber, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("forward:/blockManagement/blockView.do");
		blockService.insertBlockSpec(df_idNumber, files, request);
		return mav;
	}
	
	// 블럭 스펙 보기
	@Override
	@GetMapping("/blockManagement/blockSpecView.do")
	public ModelAndView blockSpecView(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockSpecView");

		List<BlockVO> blockSpecView = new ArrayList<BlockVO>();
		blockSpecView = blockService.selectBlockSpecView(df_idNumber);
		mav.addObject("blockSpecView", blockSpecView);
		mav.addObject("df_idNumber", df_idNumber);
		return mav;
	}

	// 블럭 스펙 삭제
	@Override
	@GetMapping("/blockManagement/removeBlockSpec.do")
	public ModelAndView removeBlockSpec(@RequestParam("df_idNumber") String df_idNumber) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockView.do");
		blockService.removeBlockSpec(df_idNumber);
		return mav;
	}
	
	// 블럭 점검 게시판
	@Override
	@GetMapping("/blockManagement/blockInspectionBoard.do")
	public ModelAndView blockInspectionList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockInspectionBoard");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.inspectionBoardCount(searchArea);
		if (totalCount == 0) {
			totalCount = 1;
		}
		
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);

		List<BlockVO> inspectionBoard = blockService.selectInspectionBoard(searchArea, paging.getOffset(), limit);
		mav.addObject("inspectionBoard", inspectionBoard);
		mav.addObject("paging", paging);
		mav.addObject("searchArea", searchArea);
		return mav;
	}
	
	// 블럭 점검 폼
	@Override
	@GetMapping("/blockManagement/addInspectionForm.do")
	public ModelAndView addInspectionForm(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/addInspectionForm");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<BlockVO> inspectionList = blockService.inspectionList(searchArea);
		mav.addObject("inspectionList", inspectionList);
		mav.addObject("searchArea", searchArea);
		return mav;
	}

	// 블럭 점검 추가 (정보저장)
	@Override
	@PostMapping("/blockManagement/addInspection.do")
	public ModelAndView addInspection(@RequestParam("bib_title") String bib_title,
			@RequestParam(value = "df_idNumber", required = false) String[] df_idNumberArray,
			@RequestParam(value = "bil_status", required = false) String[] bil_statusArray,
			HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockInspectionBoard.do");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<BlockVO> inspectionList = new ArrayList<>();
		for (int i = 0; i < df_idNumberArray.length; i++) {
			BlockVO inspectionStatus = new BlockVO();
			inspectionStatus.setDf_idNumber(df_idNumberArray[i]);
			inspectionStatus.setBil_status(bil_statusArray[i]);
			inspectionStatus.setLogin_area(searchArea);
			inspectionList.add(inspectionStatus);
		}
		
		int result_title = blockService.addInspectionBoard(bib_title, searchArea);
		int result_inspectionList = blockService.addInspectionList(inspectionList);
		return mav;
	}
	
	// 블럭 점검 보기
	@Override
	@GetMapping("/blockManagement/blockInspectionView.do")
	public ModelAndView blockInspectionView(@RequestParam("bib_num") int bib_num) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockInspectionView");
		List<BlockVO> blockInspectionView = blockService.blockInspectionView(bib_num);
		String inspectionTitle = blockDAO.selectInspectionTitle(bib_num);
		mav.addObject("blockInspectionView", blockInspectionView);
		mav.addObject("inspectionTitle", inspectionTitle);
		mav.addObject("bib_num", blockInspectionView.get(0).getBib_num());
		return mav;
	}
	
	// 블럭 점검 삭제
	@Override
	@GetMapping("/blockManagement/removeInspectionView.do")
	public ModelAndView removeInspectionView(@RequestParam("bib_num") int bib_num) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/blockManagement/blockInspectionBoard.do");
		int result = blockService.removeInspectionView(bib_num);
		return mav;
	}
	
	// 블럭 점검 이력 보기
	@Override
	@GetMapping("/blockManagement/inspectionHistory.do")
	public ModelAndView inspectionHistory(@RequestParam(value="page", defaultValue = "1") int page, @RequestParam("df_idNumber") String df_idNumber) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/inspectionHistory");
	
		int limit = 20;
		int currentPage = page;
		int pageBlockSize = 5;
		int totalCount = blockService.inspectionHistoryCount(df_idNumber);
		if(totalCount == 0) {
			totalCount = 1;
		}
		PagingDTO paging = new PagingDTO(totalCount, currentPage, limit, pageBlockSize);
		
		List<BlockVO> inspectionHistory = blockService.selectInspectionHistory(df_idNumber, paging.getOffset(), limit);
		
		mav.addObject("inspectionHistory", inspectionHistory);
		mav.addObject("paging", paging);
		return mav;
	}
	
	@GetMapping("/blockManagement/test.do")
	public ModelAndView test(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/test");
		return mav;
	}
	
}
