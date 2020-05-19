package cricketleagueanalyser;

import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {

    private static final String IPL_MOSTRUNS_DATA="src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOSTWKTS_DATA="src\\test\\resources\\IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsData_shouldReturnTotalNumberOfPlayers() {
        try{
            CricketLeagueAnalyser cricketLeagueAnalyser = new CricketLeagueAnalyser();
            int numOfRecords = cricketLeagueAnalyser.loadCricketLeagueData(IPL_MOSTRUNS_DATA);
            Assert.assertEquals(100,numOfRecords);
        } catch (CricketLeagueAnalyserException e) {}
    }

}
