package IO;

import java.util.Properties;
import java.util.Set;

public class PropertiesCache {
    private final Properties configProp = new Properties();
    
    private PropertiesCache() {
        // Private constructor to restrict new instances
        
        // InputStream in = this.getClass().getClassLoader()
        // .getResourceAsStream("app.properties");
        // InputStream in = Files.newOutputStream("", StandardCharsets.UTF_8);
        // System.out.println("Read all properties from file");
        // try {
        // configProp.load(in);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
    
    // Bill Pugh Solution for singleton pattern
    private static class LazyHolder {
        private static final PropertiesCache INSTANCE = new PropertiesCache();
    }
    
    public static PropertiesCache getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    public String getProperty(String key) {
        return configProp.getProperty(key);
    }
    
    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }
    
    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }
}