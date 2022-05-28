package task.translation;

import java.io.*;

public class Translater {

    public synchronized String translate(String src) throws IOException {
        String cmd = " fairseq-interactive data-bin/5_10.tokenized.gu-bai --path checkpoints/5_10_tokenized/checkpoint_best.pt";
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
                }
                finally{
                    try {
                        err.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(processer.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(processer.getInputStream()));
        out.write(src);
        out.close();
        String result = null;
        String line = null;
        while((line = in.readLine()) != null){
            result += line;
        }

        in.close();
        processer.destroyForcibly();
        return result;
    }

}
