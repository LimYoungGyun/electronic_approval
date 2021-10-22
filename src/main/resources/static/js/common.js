/**
 * 
 */
 
 
 
 
 // "-" 처리 함수
function apostrophe(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{7})+(?!\d))/g, '$1-');
}

// "-" 제거 처리 함수
function unapostrophe(str) {
	str = String(str);
	return str.replace(/[^\d]+/g, '');
} 

// "," 처리 함수
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

// "," 제거 처리 함수
function uncomma(str) {
	str = String(str);
	return str.replace(/[^\d]+/g, '');
}

// 문자만 입력 (특수문자 제외)
function onlyString(str) {
	str = String(str);
	return str.replace(/[^ㄱ-힣a-zA-Z]/gi,'');
}

// 숫자만 입력
function onlyNumber(str) {
	str = String(str);
	return str.replace(/[^0-9]/gi,'');
}