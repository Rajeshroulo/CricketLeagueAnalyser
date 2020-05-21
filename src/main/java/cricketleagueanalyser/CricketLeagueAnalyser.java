package cricketleagueanalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;


public class CricketLeagueAnalyser {

    Map<String,CricketAnalyserDAO> cricketLeagueData = null;
    enum Cricket { BATTING,BOWLING,BATTING_BOWLING}
    private Cricket cricket;

    public CricketLeagueAnalyser(Cricket cricket) {
        this.cricket = cricket;
    }

    public int loadIPLData(String... csvFilePath) throws CricketLeagueAnalyserException {
        cricketLeagueData = new CricketLeagueDataAdapterFactory().getCricketLeagueData(cricket,csvFilePath);
        return cricketLeagueData.size();
    }

    public String getSortedDataAccordingToBattingAverage() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        return this.getSortedCricketLeagueData(sortByBattingAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingStrikingRate() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBattingStrike = Comparator.comparing(iplData -> iplData.battingStrikeRate);
        return this.getSortedCricketLeagueData(sortByBattingStrike.reversed(),cricket);
    }

    public String  getSortedDataAccordingToSixsAndFours() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortBySixAndFour = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        return this.getSortedCricketLeagueData(sortBySixAndFour.reversed(),cricket);
    }


    public String getSortedDataAccordingToBattingStrikeRateWithSixsAndFours() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortBySixAndFour = Comparator.comparing(iplData -> (iplData.six*6 + iplData.four*4));
        Comparator<CricketAnalyserDAO> sortByStrikeWithSixsAndFour = sortBySixAndFour.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.getSortedCricketLeagueData(sortByStrikeWithSixsAndFour.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingAverageWithBattingStrikeRate() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketAnalyserDAO> sortByAverageWithStrike = sortByBattingAverage.thenComparing(iplData -> iplData.battingStrikeRate);
        return this.getSortedCricketLeagueData(sortByAverageWithStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToRunsWithBattingAverage() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketAnalyserDAO> sortByRunsWithAverage = sortByRuns.thenComparing(iplData -> iplData.battingAverage);
        return this.getSortedCricketLeagueData(sortByRunsWithAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlerAverage() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBowlerAverage = Comparator.comparing(iplData -> iplData.bowlerAverage);
        return this.getSortedCricketLeagueData(sortByBowlerAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlerStrikeRate() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBowlerStrikeRate = Comparator.comparing(iplData -> iplData.bowlerStrikeRate);
        return this.getSortedCricketLeagueData(sortByBowlerStrikeRate.reversed(),cricket);
    }

    public String getSortedDataAccordingToEconomy() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByEconomy = Comparator.comparing(iplData -> iplData.economy);
        return this.getSortedCricketLeagueData(sortByEconomy.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlerStrikeRateWith5WicketsAnd4Wickets() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByFiveAndFourWickets = Comparator.comparing(iplData -> (iplData.fiveWickets*5 + iplData.fourWickets*4));
        Comparator<CricketAnalyserDAO> sortByStrikeWithFiveAndFourWickets = sortByFiveAndFourWickets.thenComparing(iplData -> iplData.bowlerStrikeRate);
        return this.getSortedCricketLeagueData(sortByStrikeWithFiveAndFourWickets.reversed(),cricket);
    }

    public String getSortedDataAccordingToBowlingAverageWithBowlingStrikeRate() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBowlerAverage = Comparator.comparing(iplData -> iplData.bowlerAverage);
        Comparator<CricketAnalyserDAO> sortByAverageWithStrike = sortByBowlerAverage.thenComparing(iplData -> iplData.bowlerStrikeRate);
        return this.getSortedCricketLeagueData(sortByAverageWithStrike.reversed(),cricket);
    }

    public String getSortedDataAccordingToWicketsWithBowlingAverage() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByWickets = Comparator.comparing(iplData -> iplData.wickets);
        Comparator<CricketAnalyserDAO> sortByWicketsWithAverage = sortByWickets.thenComparing(iplData -> iplData.bowlerAverage);
        return this.getSortedCricketLeagueData(sortByWicketsWithAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToBattingAndBowlingAverage() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByBattingAverage = Comparator.comparing(iplData -> iplData.battingAverage);
        Comparator<CricketAnalyserDAO> sortByBattingAndBowlingAverage = sortByBattingAverage.thenComparing(iplData -> iplData.bowlerAverage);
        return this.getSortedCricketLeagueData(sortByBattingAndBowlingAverage.reversed(),cricket);
    }

    public String getSortedDataAccordingToRunsAndWickets() throws CricketLeagueAnalyserException {
        Comparator<CricketAnalyserDAO> sortByRuns = Comparator.comparing(iplData -> iplData.runs);
        Comparator<CricketAnalyserDAO> sortByRunsAndWickets = sortByRuns.thenComparing(iplData -> iplData.wickets);
        return this.getSortedCricketLeagueData(sortByRunsAndWickets.reversed(),cricket);
    }

    private String getSortedCricketLeagueData(Comparator<CricketAnalyserDAO> iplDataComparator, Cricket cricket) throws CricketLeagueAnalyserException {
        if(cricketLeagueData == null || cricketLeagueData.size() == 0 ) {
            throw new CricketLeagueAnalyserException("No Data",
                    CricketLeagueAnalyserException.ExceptionType.NO_DATA);
    }
        List sortedCricketLeagueData = cricketLeagueData.values().stream().
                sorted(iplDataComparator).
                map(iplDAO -> iplDAO.getIPLDTO(cricket)).
                collect(Collectors.toList());
        String sortedCricketLeagueDataInJson = new Gson().toJson(sortedCricketLeagueData);
        return sortedCricketLeagueDataInJson;
    }

}
