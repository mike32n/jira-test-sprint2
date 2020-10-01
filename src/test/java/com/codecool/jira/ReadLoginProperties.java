package com.codecool.jira;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadLoginProperties {
    public static void main(String[] args) throws IOException {
        Properties prop = readLoginPropertiesFile("src/main/resources/login.properties");
        System.out.println("username: " + prop.getProperty("username"));
        System.out.println("password: " + prop.getProperty("password"));
    }

    public static Properties readLoginPropertiesFile(String fileName) throws IOException {
        InputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }

    public static String getUsername() throws IOException {
        return readLoginPropertiesFile("src/main/resources/login.properties").getProperty("username");
    }

    public static String getPassword() throws IOException {
        return readLoginPropertiesFile("src/main/resources/login.properties").getProperty("password");
    }

}
