package Entity;

public class Config {
    //与配置信息表config对应
    private int id;//主键
    private String key;//键
    private String value;//值

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
