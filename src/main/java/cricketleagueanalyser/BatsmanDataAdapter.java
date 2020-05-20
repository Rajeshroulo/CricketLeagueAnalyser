package cricketleagueanalyser;

import java.util.Map;

public class BatsmanDataAdapter extends CricketLeagueDataAdapter {

    public Map<String, CricketAnalyserDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalyserException {
        return super.getCricketLeagueData(BatsmanData.class,csvFilePath);
    }


}
