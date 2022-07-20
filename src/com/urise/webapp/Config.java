package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    //protected static final File PROPS = new File(getHomeDir(), "config\\resumes.properties");
    protected static final String PROPS = "/resumes.properties";
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final SqlStorage storage;

    private Config() {
        //try (InputStream is = Files.newInputStream(PROPS.toPath())) {
        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File((String) props.get("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS);
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public SqlStorage getStorage() {
        return storage;
    }

//    private static File getHomeDir() {
//        String prop = System.getProperty("homeDir");
//        File homeDir = new File(prop == null ? "." : prop);
//        if (!homeDir.isDirectory()) {
//            throw new IllegalStateException(homeDir + " is not directory");
//        }
//        return homeDir;
//    }
}
