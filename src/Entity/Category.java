package Entity;

public class Category {
    /**
     * category���Ӧʵ����
     */
    private int id;
    private String name;
    private int recordNumber;//���Ѽ�¼����û�г�����record��������ʱ������ģ�Ϊ�˷�ֹ���Ǹ�������

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
