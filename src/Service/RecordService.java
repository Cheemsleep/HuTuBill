package Service;

import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordService {
    RecordDAO recordDao = new RecordDAO();
    public void add(int spend, Category c, String comment,Date date){
        Record r = new Record();
        r.setSpend(spend);
        r.setCid(c.getId());
        r.setComment(comment);
        r.setDate(date);
        recordDao.add(r);
    }

    public void update(Record record) {
        recordDao.update(record);
    }

    public void delete(int id) {
        recordDao.delete(id);
    }
    //通过分类来获取记录
    public List<Record> list(Category category) {
        List<Record> rs = recordDao.list();
        List<Record> rs1 = new ArrayList<>();
        for (Record r : rs) {
            if (r.getCid() == category.getId()) {
                rs1.add(r);
            }
        }
        return rs1;
    }
}
