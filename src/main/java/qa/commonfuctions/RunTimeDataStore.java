package qa.commonfuctions;

import qa.utils.ReportUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;

public class RunTimeDataStore {

    public class TabsName {

        // Global store for shared data across tests, threadsafe
        private static final ConcurrentHashMap<String, String> globalStore = new ConcurrentHashMap<>();

        // Per-thread data (used if data should be unique to each test thread)
        private static final ThreadLocal<ConcurrentHashMap<String, String>> threadStore =
                ThreadLocal.withInitial(ConcurrentHashMap::new);

        // Save data in global context
        public static void putGlobal(String key, String value) {
            globalStore.put(key, value);
        }

        public static String getGlobal(String key) {
            return globalStore.get(key);
        }

        public static void clearGlobal() {
            globalStore.clear();
        }

        // Save data in thread-local (test-specific) context
        public static void put(String key, String value) {
            threadStore.get().put(key, value);
        }

        public static String get(String key) {
            return threadStore.get().get(key);
        }

        public static void clear() {
            threadStore.get().clear();
        }
    }

    public class ST1_Watchlists_Details {

        // Global HashMap accessible from anywhere
        public static final HashMap<String, String> map = new HashMap<>();

        // Method to get value by key
        public static String getValue(String key) {
            return map.get(key);
        }

        // Optional: Method to add/update key-value
        public static void setValue(String key, String value) {
            map.put(key, value);
        }

        // Optional: Check if key exists
        public static boolean containsKey(String key) {
            return map.containsKey(key);
        }

        // Optional: Check if key exists
        public static String valueBasedOnParticularKeyText(String keyword) {
            String value = "";
            // Find and print value where key contains keyword
            for (String key : map.keySet()) {
                if (key.contains(keyword)) {
                    value = map.get(key);
                    System.out.println("Key: " + key + " → Value: " + value);
                    // Optional: break if you only want the first match
                    break;
                }
            }
            return value;
        }

        public static void update_All_Watchlist_Data (String configFilePath) throws IOException {

            ReportUtil.report(true, "INFO", "-- Function -- Starting -- update_All_Watchlist_Data function ", "");

            String inputPath = configFilePath;
            String wtlist_value = "";
            String wtlist_Key = "";

            try (FileInputStream fileInputStream = new FileInputStream(inputPath)) {

                // load properties file in Properties
                Properties watchlist_Data  = new Properties();
                watchlist_Data.load(fileInputStream);

                // Process each key
                for (String key : watchlist_Data.stringPropertyNames()) {
                    wtlist_value = watchlist_Data.getProperty(key);
                    wtlist_Key = key;  // Example: Add "new_" prefix
                    ST1_Watchlists_Details.setValue(wtlist_Key,wtlist_value);
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error for update_All_Watchlist_Data : " + e.getMessage());
                ReportUtil.report(false, "FAIL", "update_All_Watchlist_Data: ", "Error: " + e.getMessage());
            }

            ReportUtil.report(true, "INFO", "-- Function -- Ending -- update_All_Watchlist_Data function ", "");

        }
        }

    public class FAndO_Stocks_Details {

        // Global HashMap accessible from anywhere
        public static final HashMap<String, String> map = new HashMap<>();

        // Method to get value by key
        public static String getValue(String key) {
            return map.get(key);
        }

        // Optional: Method to add/update key-value
        public static void setValue(String key, String value) {
            map.put(key, value);
        }

        // Optional: Check if key exists
        public static boolean containsKey(String key) {
            return map.containsKey(key);
        }

        // Optional: Check if key exists
        public static String valueBasedOnParticularKeyText(String keyword) {
            String value = "";
            // Find and print value where key contains keyword
            for (String key : map.keySet()) {
                if (key.contains(keyword)) {
                    value = map.get(key);
                    System.out.println("Key: " + key + " → Value: " + value);
                    // Optional: break if you only want the first match
                    break;
                }
            }
            return value;
        }

        public static void update_All_FAndO_Stocks_Data (String configFilePath) throws IOException {

            ReportUtil.report(true, "INFO", "-- Function -- Starting -- update_All_FAndO_Stocks_Data function ", "");

            String inputPath = configFilePath;
            String wtlist_value = "";
            String wtlist_Key = "";

            try (FileInputStream fileInputStream = new FileInputStream(inputPath)) {

                // load properties file in Properties
                Properties FAndO_Stocks_Data  = new Properties();
                FAndO_Stocks_Data.load(fileInputStream);

                // Process each key
                for (String key : FAndO_Stocks_Data.stringPropertyNames()) {
                    wtlist_value = FAndO_Stocks_Data.getProperty(key);
                    wtlist_Key = key;  // Example: Add "new_" prefix
                    FAndO_Stocks_Details.setValue(wtlist_Key,wtlist_value);
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error for update_All_FAndO_Stocks_Data : " + e.getMessage());
                ReportUtil.report(false, "FAIL", "update_All_FAndO_Stocks_Data: ", "Error: " + e.getMessage());
            }

            ReportUtil.report(true, "INFO", "-- Function -- Ending -- update_All_FAndO_Stocks_Data function ", "");

        }
    }

    }
