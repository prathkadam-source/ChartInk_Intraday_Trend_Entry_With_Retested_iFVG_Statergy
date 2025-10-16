package qa.commonfuctions;

import qa.utils.ReportUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ArrayFunctions {

    public String[]  getUniqueArrayFromSetOfTwoArrays (String CurrentTimeStampStocks, String PreviuosTimeStampStocks ) {

        ReportUtil.report( true, "INFO", "-- Function -- Starting -- getUniqueArrayFromSetOfTwoArrays function",  "");

        String[] CurrentTimeStampStocksArray = CurrentTimeStampStocks.split(",");
        String[] PreviuosTimeStampStocksArray = PreviuosTimeStampStocks.split(",");

        Set<String> set1 = new HashSet<>(Arrays.asList(CurrentTimeStampStocksArray));
        Set<String> set2 = new HashSet<>(Arrays.asList(PreviuosTimeStampStocksArray));
        Set<String> unique = new HashSet<>(set1);

        try {

            // Create copies to preserve originals
            unique.addAll(set2); // Union of both sets

            Set<String> common = new HashSet<>(set1);
            common.retainAll(set2); // Intersection (common elements)

            // Remove common from union → gives unique-only items
            unique.removeAll(common);

        } catch (Exception e) {

            System.out.println("getUniqueArrayFromSetOfTwoArrays: " + e.getMessage());
            ReportUtil.report(false, "FAIL", "getUniqueArrayFromSetOfTwoArrays, ", e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- getUniqueArrayFromSetOfTwoArrays function", "");
        return unique.toArray(new String[0]);

    }
}
