package cricketleagueanalyser;

import java.util.Map;

public class BowlerDataAdapter extends CricketLeagueDataAdapter {

    public Map<String, CricketAnalyserDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalyserException {
        return super.getCricketLeagueData(BowlerData.class,csvFilePath);
    }

}
