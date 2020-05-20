package cricketleagueanalyser;

public class CricketAnalyserDAO {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double average;
    public double strikeRate;
    public double economy;
    public int fourWickets;
    public int fiveWickets;
    public int wickets;

    public CricketAnalyserDAO() {
    }


    public CricketAnalyserDAO(BatsmanDataCsv batsmanDataCsv) {
        this.player = batsmanDataCsv.player;
        this.four = batsmanDataCsv.four;
        this.six = batsmanDataCsv.six;
        this.runs = batsmanDataCsv.runs;
        this.average = batsmanDataCsv.battingAverage;
        this.strikeRate = batsmanDataCsv.battingStrikeRate;
    }

    public CricketAnalyserDAO(BowlerDataCsv bowlerDataCsv) {
        this.player = bowlerDataCsv.player;
        this.fourWickets = bowlerDataCsv.fourWickets;
        this.fiveWickets = bowlerDataCsv.fiveWickets;
        this.average = bowlerDataCsv.bowlingAverage;
        this.strikeRate = bowlerDataCsv.bowlingStrikeRate;
        this.economy = bowlerDataCsv.economy;
        this.wickets = bowlerDataCsv.wickets;
    }

    public Object getIPLDTO(CricketLeagueAnalyser.Cricket cricket) {
        if(cricket.equals(CricketLeagueAnalyser.Cricket.BATTING))
            return new BatsmanDataCsv(player,runs,average,strikeRate,four,six);
        return new BowlerDataCsv(player,average,strikeRate,economy,fourWickets,fiveWickets,wickets);

    }
}