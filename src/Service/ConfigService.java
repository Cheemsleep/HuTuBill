package Service;

import DAO.ConfigDAO;
import Entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    /**
     * ��ʼ�� init()
     * ��Ϊ������Ϣ�����������ݣ�һ����Ԥ�㣬һ����Mysql·���� ��������Ϣ��������ζ�Ӧ���Ǵ������ݿ��еġ� ���Ի����init����������ʼ����
     */
    static ConfigDAO dao = new ConfigDAO();
    static{
        init();
    }

    public static void init() {
        init(budget, default_budget);
        init(mysqlPath, "");
    }

    /**
     * ���ȸ���keyȥ���ң���������ڣ���ʹ��value��ֵ����һ�����ݡ�
     * @param key
     * @param value
     */
    private static void init(String key, String value){
        Config config = dao.getByKey(key);
        if (config == null) {
            Config config1 = new Config();
            config1.setKey(key);
            config1.setValue(value);
            dao.add(config1);
        }
    }

    public String get(String key) {
        Config config = dao.getByKey(key);
        return config.getValue();
    }

    /**
     * update(String key, String value)
     * ���¼���Ӧ��ֵ
     * @param key
     * @param value
     */

    public void update(String key, String value) {
        Config config = dao.getByKey(key);
        config.setValue(value);
        dao.update(config);
    }

    /**
     * get(String key)
     * ���ݼ���ȡ��Ӧ��ֵ
     * @return
     */
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }

}
