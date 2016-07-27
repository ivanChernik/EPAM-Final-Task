package by.epam.tc.hr_system.util.db;

import java.util.ResourceBundle;

public class DBResourceManager {
	private static final String PROPERTIES_FILE_PATH = "by.epam.tc.hr_system.util.db.db";
	private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle= ResourceBundle.getBundle(PROPERTIES_FILE_PATH);
    public static DBResourceManager getInstance(){
        return instance;
    }
    public String getValue(String key){
        return bundle.getString(key);
    }

}
