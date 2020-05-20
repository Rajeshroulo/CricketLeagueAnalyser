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

    Comparator<CricketAnalyserDAO> sortByAverage = Comparator.comparing(iplData -> iplData.average);
    Comparator<CricketAnalyserDAO> sortByStrike = Comparator.comparing(iplData -> iplData.strikeRate);
    Comparator<CricketAnalyserDAO> sortBySixAndFour = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
    Comparator<CricketAnalyserDAO> sortByRuns = Comparator.comparing(iplData -> iplData.runs);

    public int loadIPLData(String csvFilePath) throws CricketLeagueAnalyserException {
        iplAnalyserMap = new CricketLeagueDataAdapterFactory().getCricketLeagueData(cricket,csvFilePath);
        return iplAnalyserMap.size();
    }

    public String getSortedDataAccordingToAverage(Cricket cricket) throws CricketLeagueAnalyserException {
        return this.getSortedCricketLeagueData(sortByAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToStrikingRate(Cricket cricket) throws CricketLeagueAnalyserException {
        return this.getSortedCricketLeagueData(sortByStrike.reversed(),cricket);
    }

    public String  getSortedDataAccordingToSixsAndFours(Cricket cricket) throws CricketLeagueAnalyserException {
        return this.getSortedCricketLeagueData(sortBySixAndFour.reversed(),cricket);
    }


    public String getSortedDataAccordingToStrikeRateWithSixsAndFours(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByStrikeWithSixsAndFour = sortBySixAndFour.thenComparing(sortByStrike);
        return this.getSortedCricketLeagueData(sortByStrikeWithSixsAndFour.reversed(),cricket);
    }

    public String getSortedDataAccordingToAverageWithStrikeRate(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByAverageWithStrike = sortByAverage.thenComparing(sortByStrike);
        return this.getSortedCricketLeagueData(sortByAverageWithStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToRunsWithBattingAverage(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByRunsWithAverage = sortByRuns.thenComparing(sortByAverage);
        return this.getSortedCricketLeagueData(sortByRunsWithAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToEconomy(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.getSortedCricketLeagueData(sortByEconomy.reversed(),cricket);
    }

    public String getSortedDataAccordingToStrikeRateWith5WicketsAnd4Wickets(Cricket cricket) throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByFiveAndFourWickets = Comparator.comparing(iplData -> (iplData.fiveWickets*5 + iplData.fourWickets*4));
        Comparator<CricketAnalyserDAO> sortByStrikeWithFiveAndFourWickets = sortByFiveAndFourWickets.thenComparing(sortByStrike);
        return this.getSortedCricketLeagueData(sortByStrikeWithFiveAndFourWickets.reversed(),cricket);
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
