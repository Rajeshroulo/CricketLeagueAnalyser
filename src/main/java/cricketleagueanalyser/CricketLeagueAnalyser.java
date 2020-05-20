package cricketleagueanalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;


public class CricketLeagueAnalyser {

    Map<String,CricketAnalyserDAO> iplAnalyserMap = null;

    public int loadBatsmanData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplAnalyserMap = new CricketLeagueDataLoader().getCricketLeagueData(csvFilePath);
        return iplAnalyserMap.size();
    }

    public String getSortedBatsmanDataAccordingToBattingAverage() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> censusComparator = Comparator.comparing(iplData -> iplData.average);
        return this.getSortedCricketLeagueData(censusComparator.reversed());
    }

    public String getSortedBatsmanDataAccordingToStrikingRate() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> censusComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedCricketLeagueData(censusComparator.reversed());
    }

    public String getSortedBatsmanDataAccordingToSixsAndFours() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> censusComparator = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        return this.getSortedCricketLeagueData(censusComparator.reversed());
    }

    private String getSortedCricketLeagueData(Comparator<CricketAnalyserDAO> censusComparator) throws CricketLeagueAnalyserException {
        if(iplAnalyserMap == null || iplAnalyserMap.size() == 0 ) {
            throw new CricketLeagueAnalyserException("No Data",
                    CricketLeagueAnalyserException.ExceptionType.NO_DATA);
    }
        List sortedBatsmanData = iplAnalyserMap.values().stream().
                sorted(censusComparator).
                collect(Collectors.toList());
        String sortedBatsmanDataInJson = new Gson().toJson(sortedBatsmanData);
        return sortedBatsmanDataInJson;
    }

}
