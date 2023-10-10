package Service;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

import java.util.Collections;
import java.util.List;

/**
 * Categoryҵ����
 * 1. list()
 * ��ѯ�����е�Category��Ȼ�����ÿ�ַ��࣬�ٰѷ����Ӧ�����Ѽ�¼��������������������ڶ�Ӧ����ʵ����recordNumer�ϡ�
 * ����ٸ���recordNumer���е�����������Ƶ�ʸߵķ������ǰ�档
 *
 * 2. add(String name)
 * ����һ�ַ���
 *
 * 3. update(int id, String name)
 * ���·���
 *
 * 4. delete(int id)
 * ɾ������
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
        Collections.sort(cs,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber()); //lambda���ʽ

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
