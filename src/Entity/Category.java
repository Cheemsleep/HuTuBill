package Entity;

public class Category {
    /**
     * category表对应实体类
     */
    private int id;
    private String name;
    private int recordNumber;//消费记录数，没有出现在record表中是临时查出来的，为了防止忘记更新数据

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
