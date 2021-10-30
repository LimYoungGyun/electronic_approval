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

import com.electronicapproval.commute.bo.CommuteBO;
import com.electronicapproval.commute.model.CommuteInfoView;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping("/commute")
public class CommuteController {
	
	@Autowired
	private CommuteBO commuteBO;

	@RequestMapping("/commute_list_view")
	public String commute_list_view(
			@RequestParam(value="searchDate", required=false) String searchDate
			, Model model) {
		List<CommuteInfoView> commuteInfoViewList = new ArrayList<CommuteInfoView>();
//		if (searchDate != null) {
		if (!StringUtils.isNullOrEmpty(searchDate)) {
			String startDate = searchDate;
			String[] arrDate = startDate.split("-");
			int test1 = Integer.parseInt(arrDate[2]) + 1;
			startDate = startDate + " 05:00:00";
			String endDate = arrDate[0] + "-" + arrDate[1] + "-" + Integer.toString(test1) + " 05:00:00";
			
			commuteInfoViewList = commuteBO.getCommuteList(startDate, endDate);
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
			
			commuteInfoViewList = commuteBO.getCommuteList(startDate, endDate);
		}


		model.addAttribute("searchDate", searchDate);
		model.addAttribute("commuteInfoViewList", commuteInfoViewList);
		model.addAttribute("viewName", "commute/commute_list");
		
		return "template/layout";
	}
}
