package com.saathi;

import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class VoiceService {

    public void speak(String text) {
        try {
            String cleanText = text.replace("\"", "").replace("\n", " ");
            System.out.println("🔊 Trying to speak: " + cleanText);

            ProcessBuilder pb = new ProcessBuilder("/usr/bin/say", cleanText);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            int exitCode = process.waitFor();
            System.out.println("🔊 Say command exit code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("⚠️ Saathi bol nahi paya: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
