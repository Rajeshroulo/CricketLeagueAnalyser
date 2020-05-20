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
    private static final String IPL_MOSTWKTS_DATA="src\\\\test\\\\resources\\\\IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            int numOfRecords = cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMOstRunsData_WithWrongPath_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA_WRONG_PATH);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WhenEmpty_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA_EMPTY_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.NO_DATA,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WithWrongType_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA_WRONG_TYPE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WithWrongHeader_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA_WRONG_HEADER);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA_WRONG_DELIMITER);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE,e.type);
        }
    }

    @Test
    public void givenIPLMostRunsData_whenSortedAccordingToBattingAverage_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedDataAccordingToAverage(CricketLeagueAnalyser.Cricket.BATTING);
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals("MS Dhoni", iplBatsmanData[0].player);
            Assert.assertEquals("Harpreet Brar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_whenSortedAccordingToStrikeRate_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedDataAccordingToAverage(CricketLeagueAnalyser.Cricket.BATTING);
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals("Ishant Sharma", iplBatsmanData[0].player);
            Assert.assertEquals("Bhuvneshwar Kumar", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostRunsData_whenSortedAccordingToSixsAndFours_shouldReturnSortedResults() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedDataAccordingToAverage(CricketLeagueAnalyser.Cricket.BATTING);
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals("Andre Russell", iplBatsmanData[0].player);
            Assert.assertEquals("Shakib Al Hasan", iplBatsmanData[99].player);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortedAccordingToSixsAndFours_shouldReturnSrikeRate() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedDataAccordingToAverage(CricketLeagueAnalyser.Cricket.BATTING);
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals(204.81, iplBatsmanData[0].battingStrikeRate,0.001);
            Assert.assertEquals(90.0, iplBatsmanData[99].battingStrikeRate,0.001);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortedAccordingToStrikeRate_shouldReturnBattingAverage() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedDataAccordingToAverage(CricketLeagueAnalyser.Cricket.BATTING);
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals(0.0, iplBatsmanData[0].battingAverage,0.001);
            Assert.assertEquals(4.0, iplBatsmanData[99].battingAverage,0.001);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPL2019MostRunsData_whenSortedAccordingToBattingAverage_shouldReturnTotalNumberOfRunsOfPlayer() {
        try {
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BATTING);
            cricketLeagueAnalyser.loadBatsmanData(IPL_MOSTRUNS_DATA);
            String sortedBatsmanData = cricketLeagueAnalyser.getSortedDataAccordingToAverage(CricketLeagueAnalyser.Cricket.BATTING);
            BatsmanDataCsv[] iplBatsmanData = new Gson().fromJson(sortedBatsmanData, BatsmanDataCsv[].class);
            Assert.assertEquals(416, iplBatsmanData[0].runs);
            Assert.assertEquals(20, iplBatsmanData[99].runs);
        } catch (CricketLeagueAnalyserException e) {}
    }

    @Test
    public void givenIPLMostWktsData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser(CricketLeagueAnalyser.Cricket.BOWLING);
            int numOfRecords = cricketLeagueAnalyser.loadBowlerData(IPL_MOSTWKTS_DATA);
            Assert.assertEquals(99,numOfRecords);
        } catch (CricketLeagueAnalyserException e) {}
    }
}
