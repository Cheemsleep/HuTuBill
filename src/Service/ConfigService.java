package Service;

import DAO.ConfigDAO;
import Entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "500";

    /**
     * 初始化 init()
     * 因为设置信息里有两个数据，一个是预算，一个是Mysql路径。 这两个信息，无论如何都应该是存在数据库中的。 所以会调用init把他们俩初始化。
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
     * 首先根据key去查找，如果不存在，就使用value的值插入一条数据。
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
     * 更新键对应的值
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
     * 根据键获取相应的值
     * @return
     */
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }

}
