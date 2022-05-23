package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index")
@ResponseBody()
public class TaskController {
    @RequestMapping("/process.do")
    public String process(String taskType, String content){
        switch (taskType){
            case "translate":
                content = translate(content);
                break;
            default:
                content = "default result";
        }


        return content;
    }
    private String translate(String content){
        return "translate result";
    }
}
