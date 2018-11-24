package ch.hackathon.recipe.billscanning.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class IBMProperties {
    public static void main(String[] args) {
        IBMProperties app = new IBMProperties();
        app.printThemAll();
        System.out.println(app.getPropertie("apikey"));
    }

    private void printThemAll() {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "IBM_Cloud.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("Key : " + key + ", Value : " + value);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getPropertie(String key) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "IBM_Cloud.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                throw new IllegalArgumentException("Sorry, unable to find " + filename);
            }

            prop.load(input);
            return prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Property File Not Found");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
