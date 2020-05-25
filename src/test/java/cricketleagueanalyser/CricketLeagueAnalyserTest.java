package cricketleagueanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String IPL_MOSTRUNS_DATA="src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOSTRUNS_DATA_WRONG_PATH ="src\\main\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOSTRUNS_DATA_WRONG_DELIMITER = "src\\test\\resources\\IPL2019FactsheetMostRunsWithWrongDelimiter.csv";
    private static final String IPL_MOSTRUNS_DATA_WRONG_HEADER ="src\\test\\resources\\IPL2019FactsheetMostRunsWithWrongHeader.csv";
    private static final String IPL_MOSTRUNS_DATA_WRONG_TYPE ="src\\test\\resources\\IPL2019FactsheetMostRuns.txt";;
    private static final String IPL_MOSTRUNS_DATA_EMPTY_FILE ="src\\test\\resources\\IPL2019FactsheetMostRunsEmptyFile.csv";;
    private static final String IPL_MOSTWKTS_DATA="src\\test\\resources\\IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsData_ShouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            int numOfRecords = cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMOstRunsData_WithWrongPath_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA_WRONG_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WhenEmpty_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA_EMPTY_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WithWrongType_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA_WRONG_TYPE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WithWrongHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA_WRONG_HEADER);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA_WRONG_DELIMITER);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WhenSortedAccordingToBattingAverage_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Harpreet Brar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_WhenSortedAccordingToStrikeRate_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Ishant Sharma", iplBatsmanData[0].player);
            Assert.assertEquals("Bhuvneshwar Kumar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_WhenSortedAccordingToSixsAndFours_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortedAccordingToSixsAndFours_ShouldReturnSrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(204.81, iplBatsmanData[0].batsmanStrikeRate,0.001);
            Assert.assertEquals(90.0, iplBatsmanData[99].batsmanStrikeRate,0.001);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_WhenSortedAccordingToStrikeRate_ShouldReturnBattingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(333.33, iplBatsmanData[0].batsmanStrikeRate,0.001);
            Assert.assertEquals(63.15, iplBatsmanData[99].batsmanStrikeRate,0.001);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPL2019MostRunsData_WhenSortedAccordingToBattingAverage_ShouldReturnTotalNumberOfRunsOfPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals(416, iplBatsmanData[0].runs);
            Assert.assertEquals(20, iplBatsmanData[99].runs);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRuns_WhenSortedAccordingToStrikeRateWithSixsAndFours_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_STRIKE_WITH_SIX_AND_FOUR);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_WhenSortedAccordingToBattingAverageWithStrikeRate_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE_WITH_STRIKE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_WhenSortedAccordingToRunsWithBattingAverage_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_RUNS_WITH_AVERAGE);
            BatsmanData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanData[].class);
            Assert.assertEquals("David Warner ", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_ShouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            int numOfRecords = cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            Assert.assertEquals(99,numOfRecords);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_WhenSortedAccordingToBowlingAverage_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BOWLER_AVERAGE);
            BowlerData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBatsmanData[0].player);
            Assert.assertEquals("Tim Southee", iplBatsmanData[1].player);
            Assert.assertEquals("Yusuf Pathan", iplBatsmanData[98].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_WhenSortedAccordingToStrikeRate_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BOWLER_STRIKE);
            BowlerData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBatsmanData[0].player);
            Assert.assertEquals("Prasidh Krishna", iplBatsmanData[1].player);
            Assert.assertEquals("Yusuf Pathan", iplBatsmanData[98].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_WhenSortedAccordingToEconomy_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.ECONOMY);
            BowlerData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BowlerData[].class);
            Assert.assertEquals("Ben Cutting", iplBatsmanData[0].player);
            Assert.assertEquals("Shivam Dube", iplBatsmanData[98].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_WhenSortedAccordingToStringRateWithFiveWicketsAndFourWicket_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BOWLER_STRIKE_WITH_FOUR_AND_FIVE_WICKETS);
            BowlerData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BowlerData[].class);
            Assert.assertEquals("Lasith Malinga", iplBatsmanData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_WhenSortedAccordingToBowlingAverageWithStringRate_ShouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BOWLER_AVERAGE_WITH_STRIKE);
            BowlerData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BowlerData[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplBatsmanData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBatsmanData[98].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktssData_whenSortedAccordingToWicketsWithBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTWKTS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BOWLER_WICKETS_WITH_AVERAGE);
            BowlerData[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BowlerData[].class);
            Assert.assertEquals("Imran Tahir", iplBatsmanData[0].player);
            Assert.assertEquals("Yusuf Pathan", iplBatsmanData[98].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsandWktsData_shouldReturnTotalNumberOfRecords() {
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING_BOWLING);
            int numOfRecords = cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA,IPL_MOSTWKTS_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsandWktsData_whenSortedAccordingToBattingAndBowlingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING_BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA,IPL_MOSTWKTS_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.BATSMAN_AVERAGE_WITH_BOWLER_AVERAGE);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("MS Dhoni", cricketLeagueData[0].player);
            Assert.assertEquals("Harpreet Brar", cricketLeagueData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsandWktsData_whenSortedAccordingToRunsAndWickets_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING_BOWLING);
            cricketLeagueAnalyser.loadIPLData(IPL_MOSTRUNS_DATA,IPL_MOSTWKTS_DATA);
            String sortedCricketLeagueData = cricketLeagueAnalyser.getSortedData(CricketLeagueAnalyser.CompareType.RUNS_WITH_WICKETS);
            BatsmanAndBowlerData[] cricketLeagueData = new Gson().fromJson(sortedCricketLeagueData, BatsmanAndBowlerData[].class);
            Assert.assertEquals("David Warner ", cricketLeagueData[0].player);
            Assert.assertEquals("Tim Southee", cricketLeagueData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

}
