package cricketleagueanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IPLComparator {

     static Map<CricketLeagueAnalyser.CompareType, Comparator<CricketAnalyserDAO>> iplComparator = new HashMap<>();

    static {
        Comparator<CricketAnalyserDAO> compareByBattingStrike = Comparator.comparing(iplData -> iplData.batsmanStrikeRate);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BATSMAN_STRIKE,compareByBattingStrike);
        Comparator<CricketAnalyserDAO> compareByBattingAverage = Comparator.comparing(iplData -> iplData.batsmanAverage);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE,compareByBattingAverage);
        Comparator<CricketAnalyserDAO> compareByRuns = Comparator.comparing(iplData -> iplData.runs);
        iplComparator.put(CricketLeagueAnalyser.CompareType.RUNS,compareByRuns);
        Comparator<CricketAnalyserDAO> compareBySixAndFour = Comparator.comparing(iplData -> (iplData.six * 6 + iplData.four * 4));
        iplComparator.put(CricketLeagueAnalyser.CompareType.SIX_AND_FOUR,compareBySixAndFour);
        Comparator<CricketAnalyserDAO> compareByBattingAverageWithStrike = compareByBattingAverage.thenComparing(compareByBattingStrike);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE_WITH_STRIKE,compareByBattingAverageWithStrike);
        Comparator<CricketAnalyserDAO> compareByStrikeWithSixsAndFour = compareBySixAndFour.thenComparing(compareByBattingStrike);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BATSMAN_STRIKE_WITH_SIX_AND_FOUR,compareByStrikeWithSixsAndFour);
        Comparator<CricketAnalyserDAO> compareByRunsWithAverage = compareByRuns.thenComparing(compareByBattingAverage);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BATSMAN_RUNS_WITH_AVERAGE,compareByRunsWithAverage);
        Comparator<CricketAnalyserDAO> compareByBowlerAverage = Comparator.comparing(iplData -> iplData.bowlerAverage);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BOWLER_AVERAGE,compareByBowlerAverage);
        Comparator<CricketAnalyserDAO> compareByBowlerStrikeRate = Comparator.comparing(iplData -> iplData.bowlerStrikeRate);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BOWLER_STRIKE,compareByBowlerStrikeRate);
        Comparator<CricketAnalyserDAO> compareByEconomy = Comparator.comparing(iplData -> iplData.economy);
        iplComparator.put(CricketLeagueAnalyser.CompareType.ECONOMY,compareByEconomy);
        Comparator<CricketAnalyserDAO> compareByFiveAndFourWickets = Comparator.comparing(iplData -> (iplData.fiveWickets * 5 + iplData.fourWickets * 4));
        iplComparator.put(CricketLeagueAnalyser.CompareType.FOUR_AND_FIVE_WICKETS,compareByFiveAndFourWickets);
        Comparator<CricketAnalyserDAO> compareByStrikeWithFiveAndFourWickets = compareByFiveAndFourWickets.thenComparing(compareByBowlerStrikeRate);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS,compareByStrikeWithFiveAndFourWickets);
        Comparator<CricketAnalyserDAO> compareByAverageWithStrike = compareByBowlerAverage.thenComparing(compareByBowlerStrikeRate);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BOWLER_AVERAGE_WITH_STRIKE,compareByAverageWithStrike);
        Comparator<CricketAnalyserDAO> compareByWickets = Comparator.comparing(iplData -> iplData.wickets);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BOWLER_WICKETS,compareByWickets);
        Comparator<CricketAnalyserDAO> compareByWicketsWithAverage = compareByWickets.thenComparing(compareByBowlerAverage);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BOWLER_WICKETS_WITH_AVERAGE,compareByWicketsWithAverage);
        Comparator<CricketAnalyserDAO> compareByBattingAndBowlingAverage = compareByBattingAverage.thenComparing(compareByBowlerAverage);
        iplComparator.put(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE_WITH_BOWLER_AVERAGE,compareByBattingAndBowlingAverage);
        Comparator<CricketAnalyserDAO> compareByRunsAndWickets = compareByRuns.thenComparing(compareByWickets);
        iplComparator.put(CricketLeagueAnalyser.CompareType.RUNS_WITH_WICKETS,compareByRunsAndWickets);
    }

    public Comparator<CricketAnalyserDAO> getIPLDataComparator(CricketLeagueAnalyser.CompareType compareType) {
        return iplComparator.get(compareType);
    }


}
