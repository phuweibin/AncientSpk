package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import task.tokenize.TraditionalTokenizer;
import task.translation.Translater;

import java.io.IOException;

@Controller
@RequestMapping("/index")
@ResponseBody()
public class TaskController {
    @RequestMapping("/process.do")
    public String process(String taskType, String content){
        String tgt = null;
        switch (taskType){
            case "translate":
                tgt = taskTranslate(content);
                break;
            case "tokenize":
                tgt = taskTokenize(content);
            default:
                tgt = "default result";
        }


        return content;
    }
    private String taskTranslate(String content){
        TraditionalTokenizer tokenizer = new TraditionalTokenizer();
        Translater translater = new Translater();
        try {
            String tokened = tokenizer.token(content);
            return translater.translate(tokened);
        } catch (IOException e) {
            e.printStackTrace();
            return "执行失败！";
        }
    }

    private String taskTokenize(String content){
        TraditionalTokenizer tokenizer = new TraditionalTokenizer();
        try {
            return tokenizer.token(content);
        } catch (IOException e) {
            e.printStackTrace();
            return "执行失败！";
        }
    }
}
