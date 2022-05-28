package task.tokenize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//古文分词器，调用jiayan程序
public class TraditionalTokenizer {

    public synchronized String token(String src) throws IOException {
        String cmd = "python jiayan_tokenizer.py " + src;
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
