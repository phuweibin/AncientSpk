package ProcessTest;

import java.io.*;
import java.util.Scanner;

public class TestInputStream {
    public static void main(String [] args) throws IOException, InterruptedException {
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
//        new Thread() {
//            public void run() {
//                BufferedReader in = new BufferedReader(new InputStreamReader(processer.getInputStream()));
//                try {
//                    String result = null;
//                    while((result = in.readLine()) != null){
//                        System.out.println(result);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                finally{
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();


        BufferedReader in = new BufferedReader(new InputStreamReader(processer.getInputStream()));

        Scanner sc= new Scanner(System.in);
        String input = null;
        BufferedWriter out = null;
            input = sc.next();
            out = new BufferedWriter(new OutputStreamWriter(processer.getOutputStream()));
            out.write(input);
            out.close();

        String result = null;
        while((result = in.readLine()) != null){
            System.out.println(result);
            if(result == "end"){
                in.close();
            }
        }
        processer.destroyForcibly();
    }
}
