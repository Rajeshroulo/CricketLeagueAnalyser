package cricketleagueanalyser;

import java.util.Map;

public class CricketLeagueDataAdapterFactory {

    public Map<String, CricketAnalyserDAO> getCricketLeagueData(CricketLeagueAnalyser.Cricket cricket, String... csvFilePath) throws CricketLeagueAnalyserException {
        if (cricket.equals(CricketLeagueAnalyser.Cricket.BATTING))
            return new BatsmanDataAdapter().loadIPLData(csvFilePath[0]);
        else if (cricket.equals(CricketLeagueAnalyser.Cricket.BOWLING))
            return new BowlerDataAdapter().loadIPLData(csvFilePath[0]);
        return new MergeBatsmanAndBowlerDataAdapter().loadIPLData(csvFilePath);
    }
}
