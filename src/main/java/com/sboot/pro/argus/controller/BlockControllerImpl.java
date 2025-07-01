package com.sboot.pro.argus.controller;

import java.awt.Graphics2D;
import java.awt.Image;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.service.BlockService;
import com.sboot.pro.argus.vo.BlockVO;
import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
		int offset = (page-1) * limit ;
		
		int totalCount = blockService.getBlockCount(searchArea);
		
		List<BlockVO> blockList = blockService.selectBlockList(searchArea, offset, limit);
		
		int totalPage = (int) Math.ceil((double) totalCount / limit);
		
		mav.addObject("blockList", blockList);
		mav.addObject("currentPage", page);
		mav.addObject("totalPage", totalPage);
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
		return mav;
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
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
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
}
