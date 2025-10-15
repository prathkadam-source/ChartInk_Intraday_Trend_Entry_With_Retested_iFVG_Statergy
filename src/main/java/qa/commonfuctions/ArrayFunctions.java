package qa.commonfuctions;

import qa.utils.ReportUtil;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ArrayFunctions {

    public String[]  getUniqueArrayFromSetOfTwoArrays (String CurrentTimeStampStocks, String PreviuosTimeStampStocks ) {

        ReportUtil.report( true, "INFO", "-- Function -- Starting -- getUniqueArrayFromSetOfTwoArrays function",  "");

        String[] CurrentTimeStampStocksArray = CurrentTimeStampStocks.split(",");
        String[] PreviuosTimeStampStocksArray = PreviuosTimeStampStocks.split(",");

        Set<String> uniqueSet = new LinkedHashSet<>();

        try {

            // Add all elements from both arrays
            uniqueSet.addAll(Arrays.asList(CurrentTimeStampStocksArray));
            uniqueSet.addAll(Arrays.asList(PreviuosTimeStampStocksArray));

        } catch (Exception e) {

            System.out.println("getUniqueArrayFromSetOfTwoArrays: " + e.getMessage());
            ReportUtil.report(false, "FAIL", "getUniqueArrayFromSetOfTwoArrays, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- getUniqueArrayFromSetOfTwoArrays function", "");
        return uniqueSet.toArray(new String[0]);

    }
}
