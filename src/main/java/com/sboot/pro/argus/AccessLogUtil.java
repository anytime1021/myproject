package com.sboot.pro.argus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class AccessLogUtil {

    private static final String LOG_PATH = "접속기록.txt"; // 같은 폴더에 저장됨

    public static void write(String userId, String ipAddress) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_PATH, true))) {
            String log = String.format("[%s] 접속 - 사용자: %s, IP: %s%n",
                    LocalDateTime.now(), userId, ipAddress);
            writer.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
