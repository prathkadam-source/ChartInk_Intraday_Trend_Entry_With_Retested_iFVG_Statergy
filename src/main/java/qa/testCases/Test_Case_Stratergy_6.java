package qa.testCases;

import qa.base.BaseTest;
import qa.commonfuctions.Constants;
import qa.commonfuctions.FileAndFolderFunctions;
import qa.commonfuctions.NewTabsSetUp;
import qa.pages.AlertPage;
import qa.pages.LoginPage;
import qa.pages.WatchlistPage;
import qa.utils.ReportUtil;

import java.io.IOException;

public class Test_Case_Stratergy_6 extends BaseTest {

    LoginPage loginPage = new LoginPage();
    NewTabsSetUp newTabsSetUp = new NewTabsSetUp();

    AlertPage alertPage = new AlertPage();

    WatchlistPage watchlistPage = new WatchlistPage();

    // Buy_Trades_From_Logic_Liquidity_Swipe_From_Buy_FVG
    public void Buy_Trades_From_Logic_Liquidity_Swipe_From_Buy_FVG() {

        ReportUtil.report( true, "INFO", "-- Test case 6 -- Starting -- Buy_Trades_From_Logic_Liquidity_Swipe_From_Buy_FVG",  "");

        try {

            Step_1_Checking_ST6_CONDITION_1_Alerts();

        }catch (InterruptedException e) {

            System.out.println("Test case 6 Buy_Trades_From_Logic_Liquidity_Swipe_From_Buy_FVG: " + e.getMessage());
            ReportUtil.report( false, "FAIL", "Test case 6 Buy_Trades_From_Logic_Liquidity_Swipe_From_Buy_FVG, ",  e.getMessage());
        }

        ReportUtil.report( true, "INFO", "-- Test case 6 -- Ending -- Buy_Trades_From_Logic_Liquidity_Swipe_From_Buy_FVG",  "");

    }

    public void Step_1_Checking_ST6_CONDITION_1_Alerts() throws InterruptedException {

        ReportUtil.report(true, "INFO", "-- Step 1 -- Starting -- Step_1_Checking_ST6_CONDITION_1_Alerts", "");

        // <editor-fold desc="Variables">
        String Alerts_Stock_Names = "";
        String latest_Alert_TimeStamp = "";

        String Current_Watchlist_Details = "";
        String Current_Watchlist_Name = "";
        String Current_Watchlist_Url = "";
        String Comments = "";

        // </editor-fold>

        try {

            // If new alert displayed for strategy : ST6_CONDITION_1 then add it to watchlist of strategy : ST6_Cndt2
            if (alertPage.verify_And_Get_Latest_Alert_Displayed_For_Strategies(Constants.ST6_CONDITION_1_Step_1,
                    Constants.TAB_ALERTPAGE_NAME_ST_6_FIRST_CONDITION,false)) {

                Alerts_Stock_Names = Constants.LATEST_ALERT_STOCK_NAMES;
                latest_Alert_TimeStamp = Constants.LATEST_ALERT_TIMESTAMP;
                String[] stocks;

                // Update Stock Alert to textfile
                FileAndFolderFunctions.Overwrite_To_Text_File(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST, Alerts_Stock_Names);

                // <editor-fold desc=" Step 1 - Sub 1">

                ReportUtil.report(true, "INFO", "Step 1 - Sub 1-- Adding stocks from alert ST6_CONDITION_1 to ST6_Cndt2 watchlist ", "");
                //Add Stocks to watchlist
                if (Alerts_Stock_Names.contains(",")) {
                    stocks = Alerts_Stock_Names.split(",");
                } else {
                    stocks = new String[]{Alerts_Stock_Names};
                }
                // **********Using ST2_CNDT_2_TIME series watchlist , hence not to get confused
                Current_Watchlist_Details = watchlistPage.get_Watchlist_Url(Constants.ST2_CNDT_2_TIME, latest_Alert_TimeStamp);
                Current_Watchlist_Name = Current_Watchlist_Details.split(",")[0];
                Current_Watchlist_Url = Current_Watchlist_Details.split(",")[1];

                /// Upload Stocks using Auto_it App and textfile
                watchlistPage.upload_Stock_List_TextFile_Using_Auto_It(Constants.TEXTFILE_PATH_FOR_RUNTIME_STOCKS_FOR_WATCHLIST,
                        Constants.TAB_DEFAULT_WATCHLIST_PAGE,
                        Current_Watchlist_Name, Current_Watchlist_Url, Alerts_Stock_Names);

//                // add Stocks from "ST6_CONDITION_1 to watchlist of ST6_Cndt2
//                watchlistPage.add_Stocks_To_Watchlist(Constants.TAB_DEFAULT_WATCHLIST_PAGE,
//                        Current_Watchlist_Name, Current_Watchlist_Url, stocks);

                // Update Stock Alerts to  output textfile for end of the day validation
                Comments = Constants.ST6_CONDITION_1_Step_1 + System.lineSeparator() + Constants.ACTION_STOCKS_ADDED ;
                FileAndFolderFunctions.update_Output_Text_File_for_Alert_Results(Constants.TEXTFILE_PATH_ST6_CNDT2_WATCHLIST_UPDATES_FROM_CNDT_1,
                        Comments, Current_Watchlist_Name,
                        Current_Watchlist_Url, Alerts_Stock_Names);
                // </editor-fold>

            }

        } catch (IOException e) {

            System.out.println("Step 1 Step_1_Checking_ST6_CONDITION_1_Alerts: " + e.getMessage());
            ReportUtil.report(false, "FAIL", "Step 1 Step_1_Checking_ST6_CONDITION_1_Alerts, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Step 1 -- Ending -- Step_1_Checking_ST6_CONDITION_1_Alerts", "");
    }

}
