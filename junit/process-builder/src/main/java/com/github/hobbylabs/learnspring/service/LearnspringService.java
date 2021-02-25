package com.github.hobbylabs.learnspring.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class LearnspringService {

    public int exec() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("ls", "-l");
        Process p = pb.start();
        int ret = p.waitFor();
        System.out.println("Return code is " + ret);

        System.out.println("= Print getInputStream() ==============================================");
        printInputStream(p.getInputStream());
        System.out.println("= Print getErrorStream() ==============================================");
        printInputStream(p.getErrorStream());

        return ret;
    }

    /**
     * Output a stream.
     * @param is Input stream. This might be InputStream or ErrorStream.
     * @throws IOException IOException.
     */
    public static void printInputStream(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            for (;;) {
                String line = br.readLine();
                if (line == null) break;
                System.out.println(line);
            }
        } finally {
            br.close();
        }
    }
}
