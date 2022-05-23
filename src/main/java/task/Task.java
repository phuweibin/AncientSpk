package task;

public enum Task {

    TRANSLATION("translate"),
    TOKENIZE("tokenize"),
    SPLITS("splitS"),
    T2S("t2s");

    private final String taskCode;
    private Task(String taskCode){
        this.taskCode = taskCode;
    }

    public String getTaskCode(){
        return this.taskCode;
    }
}
