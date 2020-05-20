package cricketleagueanalyser;

import java.util.HashMap;
import java.util.Map;

public class MergeBatsmanAndBowlerDataAdapter  extends CricketLeagueDataAdapter {

    Map<String, CricketAnalyserDAO> batsmanData = new HashMap<>();
    Map<String, CricketAnalyserDAO> bowlersData = new HashMap<>();

    public Map<String, CricketAnalyserDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalyserException {
        batsmanData = super.getCricketLeagueData(BatsmanData.class,csvFilePath[0]);
        bowlersData = super.getCricketLeagueData(BowlerData.class,csvFilePath[1]);
        bowlersData.values().stream()
                .filter(iplData -> batsmanData.get(iplData.player) != null)
                .forEach(iplData -> batsmanData.get(iplData.player).bowlerAverage = iplData.bowlerAverage);
        return batsmanData;
    }

}
