package task.tokenize;

import java.io.*;

//古文分词器，调用jiayan程序
public class TraditionalTokenizer {

    public synchronized String token(String src) throws IOException {
        Process processer = Runtime.getRuntime().exec("python C:\\Users\\Administrator\\Desktop\\毕业论文\\ancientspeak\\src\\test\\java\\ProcessTest\\test.py");

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
            if(result == "end"){
                in.close();
            }
        }
        processer.destroyForcibly();
        return result;
    }

}
