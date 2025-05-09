package com.sboot.pro.argus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class WorkDateInterceptor implements HandlerInterceptor {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 현재 날짜와 시간 가져오기
        LocalDateTime now = LocalDateTime.now();

        // 현재 날짜를 "yyyy-MM-dd HH:mm:ss" 형식으로 변환 후 Timestamp로 변환
        Timestamp workDate = Timestamp.valueOf(now.format(FORMATTER));

        // 현재 날짜에서 "해당 월의 1일 00:00:00" 설정
        LocalDateTime firstDayOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        Timestamp workDateTotal = Timestamp.valueOf(firstDayOfMonth.format(FORMATTER));

        // 요청 속성으로 저장 (Controller에서 꺼내서 사용 가능)
        request.setAttribute("work_date", workDate);
        request.setAttribute("work_date_total", workDateTotal);

        return true;
    }
}


