package com.urise.webapp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;



    private Config() {
        try (InputStream is = Files.newInputStream(PROPS.toPath())) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File((String) props.get("storage.dir"));
            dbUrl = (String) props.get("db.url");
            dbUser = (String) props.get("db.user");
            dbPassword = (String) props.get("db.password");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
