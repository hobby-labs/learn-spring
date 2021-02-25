package com.github.hobbylabs.learnspring.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

interface ProcessStarter {
    Process start() throws IOException;
}

class ProcessStarterProvider implements ProcessStarter {
    List<String> commands;
    Process process;

    public ProcessStarter apply(String... commands) throws IOException {
        this.commands = Arrays.asList(commands);

        ProcessBuilder builder = new ProcessBuilder(this.commands);
        this.process = builder.start();

        return this;
    }
    public Process start() {
        return this.process;
    }
}

@Service
public class LearnspringService2 {
    ProcessStarterProvider processStarterProvider;

    public LearnspringService2() {
        this(new ProcessStarterProvider());
    }
    public LearnspringService2(ProcessStarterProvider processStarterProvider) {
        this.processStarterProvider = processStarterProvider;]
    }
    public int exec() throws IOException, InterruptedException {
        return this.exec(this.processStarterProvider);
    }

    public int exec(ProcessStarterProvider provider) throws IOException, InterruptedException {
        provider.apply("ls", "-l");
        Process p = provider.start();

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
