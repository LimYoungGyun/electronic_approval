package com.electronicapproval.commute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.electronicapproval.common.PageMaker;
import com.electronicapproval.common.Paging;
import com.electronicapproval.commute.bo.CommuteBO;
import com.electronicapproval.commute.model.CommuteInfoView;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("/commute")
public class CommuteController {
	
	@Autowired
	private CommuteBO commuteBO;
	
	/**
	 * 리스트 관리 리스트 화면으로 이동.
	 * @param page
	 * @param searchDate
	 * @param model
	 * @return
	 */
	@RequestMapping("/commute_list_view")
	public String commute_list_view(
			@RequestParam(value="page", required=false) Integer page
			, @RequestParam(value="searchDate", required=false) String searchDate
			, Model model) {
		
		Paging paging = new Paging();
		if (page == null) {
			page = 0;
		}
		PageMaker pageMaker = new PageMaker();
		
		List<CommuteInfoView> commuteInfoViewList = new ArrayList<CommuteInfoView>();
		if (!StringUtils.isNullOrEmpty(searchDate)) {
			String startDate = searchDate;
			String[] arrDate = startDate.split("-");
			int test1 = Integer.parseInt(arrDate[2]) + 1;
			startDate = startDate + " 05:00:00";
			String endDate = arrDate[0] + "-" + arrDate[1] + "-" + Integer.toString(test1) + " 05:00:00";
			
			// paging
			int commuteCount = commuteBO.getCommuteListCount(startDate, endDate);
			
			paging = settingPage(page, commuteCount);
			pageMaker = settingPageMaker(paging);
			model.addAttribute("paging", paging);
			model.addAttribute("pageMaker", pageMaker);
			
			commuteInfoViewList = commuteBO.getCommuteListPage(startDate, endDate, paging.getStartRow(), paging.getEndRow());
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 05:00:00");
			// 오늘 날짜 구하기
			LocalDateTime today = LocalDateTime.now();
			String startDate = today.format(formatter);
			model.addAttribute("startDate", startDate);
			// 다음날 날짜 구하기
			LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
			String endDate = tomorrow.format(formatter);
			model.addAttribute("endDate", endDate);
			
			// paging
			int commuteCount = commuteBO.getCommuteListCount(startDate, endDate);
			
			paging = settingPage(page, commuteCount);
			pageMaker = settingPageMaker(paging);
			model.addAttribute("paging", paging);
			model.addAttribute("pageMaker", pageMaker);
			
			commuteInfoViewList = commuteBO.getCommuteListPage(startDate, endDate, paging.getStartRow(), paging.getEndRow());
		}
		
		model.addAttribute("searchDate", searchDate);
		model.addAttribute("commuteInfoViewList", commuteInfoViewList);
		model.addAttribute("viewName", "commute/commute_list");
		
		return "template/layout";
	}
	
	/**
	 * Paging GET SET
	 * @param page
	 * @param commuteCount
	 * @return
	 */
	private Paging settingPage(int page, int commuteCount) {
		Paging paging = new Paging();
		
		paging.setPage(page);
		paging.setPageSize(10); // 한 페이지의 게시글 수 // 날짜로 페이지로 구분 - 한페이지에 보여지는 게시물 수는 직원 수
		paging.setTotalArticle(commuteCount); // 전체 게시글 수
		paging.setTotalPage(paging.getTotalArticle()); // 총 페이제 수
		paging.setStartRow(paging.getPage());
		paging.setEndRow(paging.getPage());
		
		return paging;
	}
	
	/**
	 * PageMaker GET SET
	 * @param paging
	 * @return
	 */
	private PageMaker settingPageMaker(Paging paging) {
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setPaging(paging);
		
		return pageMaker;
	}
}
