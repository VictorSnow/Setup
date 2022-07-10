package com.victorup.setup;

import android.app.Application;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Util {
    public static void bootScript() {
        Executor backgroundExecutor = Executors.newSingleThreadExecutor();

        backgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder pb = new ProcessBuilder();
                pb.command("sh");
                pb.directory(new File("/data/"));
                pb.redirectErrorStream(true);

                try {
                    Process p = pb.start();
                    p.getOutputStream().write("su".getBytes());
                    p.getOutputStream().write("\n".getBytes());
                    p.getOutputStream().write("cd /data/".getBytes());
                    p.getOutputStream().write("\n".getBytes());
                    p.getOutputStream().write("/data/setup.sh".getBytes());
                    p.getOutputStream().write("\n".getBytes());
                    p.getOutputStream().close();

                    StringBuilder output = new StringBuilder();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(p.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        output.append(line + "\n");
                    }

                    int exitVal = p.waitFor();
                    Log.d("Util bootScript", "Output " + exitVal + "  " + output);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();

                    Log.d("Util bootScript", "Error" + e.toString());
                }
            }
        });
    }
}
