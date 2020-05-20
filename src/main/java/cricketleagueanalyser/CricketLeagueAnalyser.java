package cricketleagueanalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;


public class CricketLeagueAnalyser {

    Map<String,CricketAnalyserDAO> iplAnalyserMap = null;
    enum Cricket { BATTING,BOWLING}
    private Cricket cricket;

    public CricketLeagueAnalyser(Cricket cricket) {
        this.cricket = cricket;
    }

    public int loadBatsmanData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplAnalyserMap = new CricketLeagueDataLoader().getCricketLeagueData(BatsmanDataCsv.class,csvFilePath);
        return iplAnalyserMap.size();
    }

    public int loadBowlerData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplAnalyserMap = new CricketLeagueDataLoader().getCricketLeagueData(BowlerDataCsv.class, csvFilePath);
        return iplAnalyserMap.size();
    }

    public String getSortedDataAccordingToAverage(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> censusComparator = Comparator.comparing(iplData -> iplData.average);
        return this.getSortedCricketLeagueData(censusComparator.reversed(),cricket);
    }

    public String getSortedDataAccordingToStrikingRate(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> censusComparator = Comparator.comparing(iplData -> iplData.strikeRate);
        return this.getSortedCricketLeagueData(censusComparator.reversed(),cricket);
    }

    public String  getSortedDataAccordingToSixsAndFours(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> censusComparator = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        return this.getSortedCricketLeagueData(censusComparator.reversed(),cricket);
    }

    private String getSortedCricketLeagueData(Comparator<CricketAnalyserDAO> censusComparator, Cricket cricket) throws CricketLeagueAnalyserException {
        if(iplAnalyserMap == null || iplAnalyserMap.size() == 0 ) {
            throw new CricketLeagueAnalyserException("No Data",
                    CricketLeagueAnalyserException.ExceptionType.NO_DATA);
    }
        List sortedBatsmanData = iplAnalyserMap.values().stream().
                sorted(censusComparator).
                map(iplDAO -> iplDAO.getIPLDTO(cricket)).
                collect(Collectors.toList());
        String sortedBatsmanDataInJson = new Gson().toJson(sortedBatsmanData);
        return sortedBatsmanDataInJson;
    }

}
