package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import task.splitS.SentenceSplit;
import task.tokenize.TraditionalTokenizer;
import task.tradition2simple.T2SProcesser;
import task.translation.Translater;

import java.io.IOException;

@Controller
@RequestMapping("/index")
@ResponseBody()
public class TaskController {
    @RequestMapping("/process.do")
    public String process(String taskType, String content) {
        String tgt = null;
        try {
            switch (taskType) {
                case "translate":
                    tgt = taskTranslate(content);
                    break;
                case "tokenize":
                    tgt = taskTokenize(content);
                case "splitS":
                    tgt = taskSentenceS(content);
                case "T2S":
                    tgt = t2s(content);
                default:
                    tgt = "非法任务！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            tgt = "执行失败！";
        }
        return tgt;
    }

    private String taskTranslate(String content) throws IOException {
        TraditionalTokenizer tokenizer = new TraditionalTokenizer();
        Translater translater = new Translater();
        String tokened = tokenizer.token(content);
        return translater.translate(tokened);

    }

    private String taskTokenize(String content) throws IOException {
        TraditionalTokenizer tokenizer = new TraditionalTokenizer();

        return tokenizer.token(content);

    }

    private String taskSentenceS(String content) throws IOException {
        SentenceSplit spliter = new SentenceSplit();
        return spliter.split(content);
    }

    private String t2s(String content) throws IOException {
        T2SProcesser t2SProcesser = new T2SProcesser();
        return t2SProcesser.transform(content);
    }
}
