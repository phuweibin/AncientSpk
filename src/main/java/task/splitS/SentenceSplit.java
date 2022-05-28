package task.splitS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SentenceSplit {
    public synchronized String split(String src) throws IOException {
        String cmd = "python T2S_processer.py " + src;
        Process processer = Runtime.getRuntime().exec(cmd);

        new Thread() {
            public void run() {
                BufferedReader err = new BufferedReader(new InputStreamReader(processer.getErrorStream()));
                try {
                    String line1 = null;
                    while ((line1 = err.readLine()) != null) {
                        System.out.println(line1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        err.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(processer.getInputStream()));
        String result = null;
        String line = null;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        in.close();
        processer.destroyForcibly();
        return result;
    }

}
