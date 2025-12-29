package qa.testCases;

import qa.base.BaseTest;
import qa.commonfuctions.Constants;
import qa.commonfuctions.DateTimeFunctions;
import qa.commonfuctions.FileAndFolderFunctions;
import qa.commonfuctions.NewTabsSetUp;
import qa.pages.AlertPage;
import qa.pages.LoginPage;
import qa.pages.WatchlistPage;
import qa.utils.ReportUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static qa.utils.ReportUtil.finalizeReport;

public class Test_Case_Stratergy_2 extends BaseTest {

    LoginPage loginPage = new LoginPage();
    NewTabsSetUp newTabsSetUp = new NewTabsSetUp();

    AlertPage alertPage = new AlertPage();

    WatchlistPage watchlistPage = new WatchlistPage();

    // Buy_Trades_From_Retested_iFVG_Strategy
    public void Buy_Trades_From_Retested_iFVG_Strategy() {

        ReportUtil.report( true, "INFO", "-- Test case 1 -- Starting -- Buy_Trades_From_Retested_iFVG_Strategy",  "");

        try {

            Step_1_Checking_ST2_CONDITION_1_Alerts();

            Step_2_Checking_ST2_CONDITION_2_Alerts();

            Step_3_Checking_ST2_CONDITION_3_Alerts();

            Step_4_Checking_ST2_CONDITION_4_Alerts();

            Step_5_Checking_ST2_CONDITION_5_Alerts();


        }catch (InterruptedException e) {

            System.out.println("Test case 1 Buy_Trades_From_Retested_iFVG_Strategy: " + e.getMessage());
            ReportUtil.report( false, "FAIL", "Test case 1 Buy_Trades_From_Retested_iFVG_Strategy, ",  e.getMessage());
        }

        ReportUtil.report( true, "INFO", "-- Test case 1 -- Ending -- Buy_Trades_From_Retested_iFVG_Strategy",  "");

    }

    public void Step_1_Checking_ST2_CONDITION_1_Alerts() throws InterruptedException {

        ReportUtil.report(true, "INFO", "-- Step 1 -- Starting -- Step_1_Checking_ST2_CONDITION_1_Alerts", "");

        // <editor-fold desc="Variables">
        String Alerts_Stock_Names = "";
        String latest_Alert_TimeStamp = "";

        String Current_Watchlist_Details = "";
        String Current_Watchlist_Name = "";
        String Current_Watchlist_Url = "";
        String Comments = "";

//        String ST2_Cndt_2_Watchlist_Name = prop.getProperty("ST2_Cndt_2_Watchlist_Name");
//        String ST2_Cndt_2_Watchlist_Url = prop.getProperty("ST2_Cndt_2_Watchlist_Url");

        // </editor-fold>

        try {

            // If new alert displayed for strategy : ST2_CONDITION_1 then add it to watchlist of strategy : ST2_Cndt2
            if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_Strategies(Constants.ST2_CONDITION_1_Step_1,
                    Constants.TAB_ALERTPAGE_NAME_ST_2_FIRST_CONDITION,false)) {

                Alerts_Stock_Names = Constants.LATEST_ALERT_STOCK_NAMES;
                latest_Alert_TimeStamp = Constants.LATEST_ALERT_TIMESTAMP;
                String[] stocks;

                // Update Stock Alert to textfile
                FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST, Alerts_Stock_Names);

                // <editor-fold desc=" Step 1 - Sub 1">

                ReportUtil.report(true, "INFO", "Step 1 - Sub 1-- Adding stocks from alert ST2_CONDITION_1 to ST2_Cndt2 watchlist ", "");
                //Add Stocks to watchlist
                if (Alerts_Stock_Names.contains(",")) {
                    stocks = Alerts_Stock_Names.split(",");
                } else {
                    stocks = new String[]{Alerts_Stock_Names};
                }

                Current_Watchlist_Details = watchlistPage.get_Watchlist_Url(Constants.ST2_CNDT_1_TIME, latest_Alert_TimeStamp);
                Current_Watchlist_Name = Current_Watchlist_Details.split(",")[0];
                Current_Watchlist_Url = Current_Watchlist_Details.split(",")[1];

                /// Upload Stocks using Auto_it App and textfile
                watchlistPage.upload_Stock_List_TextFile_Using_Auto_It(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST,
                        Constants.TAB_DEFAULT_WATCHLIST_PAGE,
                        Current_Watchlist_Name, Current_Watchlist_Url, Alerts_Stock_Names);

//                // add Stocks from "ST2_CONDITION_1 to watchlist of ST2_Cndt2
//                watchlistPage.add_Stocks_To_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE,
//                        Current_Watchlist_Name, Current_Watchlist_Url, stocks);

                // Update Stock Alerts to  output textfile for end of the day validation
                Comments = Constants.ST2_CONDITION_1_Step_1 + System.lineSeparator() + Constants.ACTION_STOCKS_ADDED ;
                FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT2_WATCHLIST_UPDATES_FROM_CNDT_1,
                        Comments, Current_Watchlist_Name,
                        Current_Watchlist_Url, Alerts_Stock_Names);
                // </editor-fold>

            }

        } catch (IOException e) {

            System.out.println("Step 1 Step_1_Checking_ST2_CONDITION_1_Alerts: " + e.getMessage());
            ReportUtil.report(false, "FAIL", "Step 1 Step_1_Checking_ST2_CONDITION_1_Alerts, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Step 1 -- Ending -- Step_1_Checking_ST2_CONDITION_1_Alerts", "");
    }

    public void Step_2_Checking_ST2_CONDITION_2_Alerts() throws InterruptedException {

        ReportUtil.report(true, "INFO", "-- Step 2 -- Starting -- Step_2_Checking_ST2_CONDITION_2_Alerts", "");

        // <editor-fold desc="Variables">
        String Alerts_Stock_Names = "";
        String latest_Alert_TimeStamp = "";

        String Comments = "";

        String ST2_Cndt_3_Watchlist_Name = prop.getProperty("ST2_Cndt_3_Watchlist_Name");
        String ST2_Cndt_3_Watchlist_Url = prop.getProperty("ST2_Cndt_3_Watchlist_Url");

        // </editor-fold>

        try {

            // If new alert displayed for strategy : ST2_CONDITION_2 then add it to watchlist of strategy : ST2_Cndt3
            if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_Strategies(Constants.ST2_CONDITION_2_Step_2,
                    Constants.TAB_ALERTPAGE_NAME_ST_2_SECOND_CONDITION,false)) {

                Alerts_Stock_Names = Constants.LATEST_ALERT_STOCK_NAMES;
                latest_Alert_TimeStamp = Constants.LATEST_ALERT_TIMESTAMP;
                String[] stocks;

                // Update Stock Alert to textfile
                FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST, Alerts_Stock_Names);

                //Add Stocks to watchlist
                if (Alerts_Stock_Names.contains(",")) {
                    stocks = Alerts_Stock_Names.split(",");
                } else {
                    stocks = new String[]{Alerts_Stock_Names};
                }

                // <editor-fold desc=" Step 2 - Sub 1 - Adding stocks from alert ST2_CONDITION_2 to ST2_Cndt3 watchlist ">
                ReportUtil.report(true, "INFO", "Step 2 - Sub 1 - Adding stocks from alert ST2_CONDITION_2 to ST2_Cndt3 watchlist ", "");

                /// Upload Stocks using Auto_it App and textfile
                watchlistPage.upload_Stock_List_TextFile_Using_Auto_It(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST,
                        Constants.TAB_DEFAULT_WATCHLIST_PAGE,
                        ST2_Cndt_3_Watchlist_Name, ST2_Cndt_3_Watchlist_Url, Alerts_Stock_Names);

                // add Stocks from "ST2_CONDITION_2 to watchlist of ST2_Cndt3
//                watchlistPage.add_Stocks_To_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE,
//                        ST2_Cndt_3_Watchlist_Name, ST2_Cndt_3_Watchlist_Url, stocks);

                // Update Stock Alerts to  output textfile for end of the day validation
                Comments = Constants.ST2_CONDITION_2_Step_2 + "- Sub 1 -" + System.lineSeparator() + Constants.ACTION_STOCKS_ADDED ;
                FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_UPDATES_FROM_CNDT_2,
                        Comments, ST2_Cndt_3_Watchlist_Name,
                        ST2_Cndt_3_Watchlist_Url, Alerts_Stock_Names);
                // </editor-fold>

            }

        } catch (IOException e) {

            System.out.println("Step_2_Checking_ST2_CONDITION_2_Alerts: " + e.getMessage());
            ReportUtil.report(false, "FAIL", " Step_2_Checking_ST2_CONDITION_2_Alerts, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Step 2 -- Ending -- Checking_ST2_CONDITION_2_Alerts", "");
    }

    public void Step_3_Checking_ST2_CONDITION_3_Alerts() throws InterruptedException {

        ReportUtil.report(true, "INFO", "-- Starting -- Step_3_Checking_ST2_CONDITION_3_Alerts", "");

        // <editor-fold desc="Variables">
        String Alerts_Stock_Names = "";
        String latest_Alert_TimeStamp = "";

        String Comments = "";

        String ST2_Cndt_3_Watchlist_Name = prop.getProperty("ST2_Cndt_3_Watchlist_Name");
        String ST2_Cndt_3_Watchlist_Url = prop.getProperty("ST2_Cndt_3_Watchlist_Url");

        // </editor-fold>

        try {

            // If new alert displayed for strategy : ST2_CONDITION_3 then remove stocks from ST2_Cndt_3_Watchlist
            if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_Strategies(Constants.ST2_CONDITION_3_Step_3,
                    Constants.TAB_ALERTPAGE_NAME_ST_2_THIRD_CONDITION,false)) {

                Alerts_Stock_Names = Constants.LATEST_ALERT_STOCK_NAMES;
                latest_Alert_TimeStamp = Constants.LATEST_ALERT_TIMESTAMP;
                String[] stocks;

                // Update Stock Alert to textfile
                FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST, Alerts_Stock_Names);

                //Add Stocks to watchlist
                if (Alerts_Stock_Names.contains(",")) {
                    stocks = Alerts_Stock_Names.split(",");
                } else {
                    stocks = new String[]{Alerts_Stock_Names};
                }

                // <editor-fold desc=" Step 2 - Sub 3 - Remove filtered stock from watchlist 'ST2_Cndt_5_Watchlist'  ">
                ReportUtil.report(true, "INFO", "Step 3 - Sub 1 - Remove filtered stock from watchlist 'ST2_Cndt_3_Watchlist' ", "");

                watchlistPage.delete_Stock_From_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE,
                        ST2_Cndt_3_Watchlist_Name, ST2_Cndt_3_Watchlist_Url, stocks);

                // Update Stock Alerts to  output textfile for end of the day validation
                Comments = Constants.ST2_CONDITION_3_Step_3 + "- Sub 1 -" + System.lineSeparator() + Constants.ACTION_STOCKS_REMOVED ;

                FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT3_WATCHLIST_STOCKS_REMOVAL_UPDATES_FROM_FAILED_OF_IFVG_CNDT_3,
                        Comments, ST2_Cndt_3_Watchlist_Name,
                        ST2_Cndt_3_Watchlist_Url, Alerts_Stock_Names);
                // </editor-fold>

            }

        } catch (IOException e) {

            System.out.println("Step_5_Checking_ST2_CONDITION_5_Alerts: " + e.getMessage());
            ReportUtil.report(false, "FAIL", " Step_5_Checking_ST2_CONDITION_5_Alerts, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "Ending -- Step_5_Checking_ST2_CONDITION_5_Alerts", "");
    }

    public void Step_4_Checking_ST2_CONDITION_4_Alerts() throws InterruptedException {

        ReportUtil.report(true, "INFO", "-- Starting -- Step_4_Checking_ST2_CONDITION_4_Alerts", "");

        // <editor-fold desc="Variables">
        String Alerts_Stock_Names = "";
        String latest_Alert_TimeStamp = "";

        String Comments = "";

        String ST2_Cndt_4_Watchlist_Name = prop.getProperty("ST2_Cndt_4_Watchlist_Name");
        String ST2_Cndt_4_Watchlist_Url = prop.getProperty("ST2_Cndt_4_Watchlist_Url");

        // </editor-fold>

        try {

            // If new alert displayed for strategy : ST2_CONDITION_3 then add it to watchlist of strategy : ST2_Cndt4
            if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_Strategies(Constants.ST2_CONDITION_4_Step_4,
                    Constants.TAB_ALERTPAGE_NAME_ST_2_FOURTH_CONDITION,false)) {

                Alerts_Stock_Names = Constants.LATEST_ALERT_STOCK_NAMES;
                latest_Alert_TimeStamp = Constants.LATEST_ALERT_TIMESTAMP;
                String[] stocks;

                // Update Stock Alert to textfile
                FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST, Alerts_Stock_Names);

                //Add Stocks to watchlist
                if (Alerts_Stock_Names.contains(",")) {
                    stocks = Alerts_Stock_Names.split(",");
                } else {
                    stocks = new String[]{Alerts_Stock_Names};
                }

                // <editor-fold desc=" Step 2 - Sub 1 - Adding stocks from alert ST2_CONDITION_4 to ST2_Cndt5 watchlist ">
                ReportUtil.report(true, "INFO", "Step 4 - Sub 1 - Adding stocks from alert ST2_CONDITION_4 to ST2_Cndt4 watchlist ", "");

                /// Upload Stocks using Auto_it App and textfile
                watchlistPage.upload_Stock_List_TextFile_Using_Auto_It(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST,
                        Constants.TAB_DEFAULT_WATCHLIST_PAGE,
                        ST2_Cndt_4_Watchlist_Name, ST2_Cndt_4_Watchlist_Url, Alerts_Stock_Names);

                // add Stocks from "ST2_CONDITION_3 to watchlist of ST2_Cndt4
//                watchlistPage.add_Stocks_To_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE,
//                        ST2_Cndt_5_Watchlist_Name, ST2_Cndt_5_Watchlist_Url, stocks);

                // Update Stock Alerts to  output textfile for end of the day validation
                Comments = Constants.ST2_CONDITION_4_Step_4 + "- Sub 1 -" + System.lineSeparator() + Constants.ACTION_STOCKS_ADDED ;
                FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT4_WATCHLIST_STOCKS_UPDATES_FROM_RETEST_IFVG_CNDT_4,
                        Comments, ST2_Cndt_4_Watchlist_Name,
                        ST2_Cndt_4_Watchlist_Url, Alerts_Stock_Names);
                // </editor-fold>

            }

        } catch (IOException e) {

            System.out.println("Step_4_Checking_ST2_CONDITION_4_Alerts: " + e.getMessage());
            ReportUtil.report(false, "FAIL", " Step_4_Checking_ST2_CONDITION_4_Alerts, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "Ending -- Step_4_Checking_ST2_CONDITION_4_Alerts", "");
    }

    public void Step_5_Checking_ST2_CONDITION_5_Alerts() throws InterruptedException {

        ReportUtil.report(true, "INFO", "-- Starting -- Step_5_Checking_ST2_CONDITION_5_Alerts", "");

        // <editor-fold desc="Variables">
        String Alerts_Stock_Names = "";
        String latest_Alert_TimeStamp = "";

        String Comments = "";

        String ST2_Cndt_4_Watchlist_Name = prop.getProperty("ST2_Cndt_4_Watchlist_Name");
        String ST2_Cndt_4_Watchlist_Url = prop.getProperty("ST2_Cndt_4_Watchlist_Url");

        // </editor-fold>

        try {

            // If new alert displayed for strategy : ST2_CONDITION_5 then remove stocks from ST2_Cndt_4_Watchlist
            if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_Strategies(Constants.ST2_CONDITION_5_Step_5,
                    Constants.TAB_ALERTPAGE_NAME_ST_2_FIFTH_CONDITION,false)) {

                Alerts_Stock_Names = Constants.LATEST_ALERT_STOCK_NAMES;
                latest_Alert_TimeStamp = Constants.LATEST_ALERT_TIMESTAMP;
                String[] stocks;

                // Update Stock Alert to textfile
                FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST, Alerts_Stock_Names);

                //Add Stocks to watchlist
                if (Alerts_Stock_Names.contains(",")) {
                    stocks = Alerts_Stock_Names.split(",");
                } else {
                    stocks = new String[]{Alerts_Stock_Names};
                }

                // <editor-fold desc=" Step 2 - Sub 3 - Remove filtered stock from watchlist 'ST2_Cndt_5_Watchlist'  ">
                ReportUtil.report(true, "INFO", "Step 5 - Sub 1 - Remove filtered stock from watchlist 'ST2_Cndt_4_Watchlist' ", "");

                watchlistPage.delete_Stock_From_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE,
                        ST2_Cndt_4_Watchlist_Name, ST2_Cndt_4_Watchlist_Url, stocks);

                // Update Stock Alerts to  output textfile for end of the day validation
                Comments = Constants.ST2_CONDITION_5_Step_5 + "- Sub 1 -" + System.lineSeparator() + Constants.ACTION_STOCKS_REMOVED ;

                FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST2_CNDT4_WATCHLIST_STOCKS_REMOVAL_UPDATES_FROM_FAILED_OF_IFVG_CNDT_5,
                        Comments, ST2_Cndt_4_Watchlist_Name,
                        ST2_Cndt_4_Watchlist_Url, Alerts_Stock_Names);
                // </editor-fold>

            }

        } catch (IOException e) {

            System.out.println("Step_5_Checking_ST2_CONDITION_5_Alerts: " + e.getMessage());
            ReportUtil.report(false, "FAIL", " Step_5_Checking_ST2_CONDITION_5_Alerts, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "Ending -- Step_5_Checking_ST2_CONDITION_5_Alerts", "");
    }

}
