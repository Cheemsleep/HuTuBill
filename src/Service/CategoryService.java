package Service;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

import java.util.Collections;
import java.util.List;

/**
 * Category业务类
 * 1. list()
 * 查询出所有的Category，然后根据每种分类，再把分类对应的消费记录总数查出来，并且设置在对应分类实例的recordNumer上。
 * 最后再根据recordNumer进行倒排序，让消费频率高的分类放在前面。
 *
 * 2. add(String name)
 * 增加一种分类
 *
 * 3. update(int id, String name)
 * 更新分类
 *
 * 4. delete(int id)
 * 删除分类
 */

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO recordDAO = new RecordDAO();

    public List<Category> list() {
        List<Category> cs = categoryDAO.list();
        for (Category c : cs) {
            List<Record> rs = recordDAO.list(c.getId());
            c.setRecordNumber(rs.size());
        }
        Collections.sort(cs,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber()); //lambda表达式

        return cs;
    }

    public void add(String name) {
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);
    }
    public void update(int id, String name) {
        Category c = new Category();
        c.setName(name);
        c.setId(id);
        categoryDAO.update(c);
    }

    public void delete(int id) {
        categoryDAO.delete(id);
    }

}
