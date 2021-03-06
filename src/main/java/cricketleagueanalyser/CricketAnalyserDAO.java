package cricketleagueanalyser;

public class CricketAnalyserDAO {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double economy;
    public int fourWickets;
    public int fiveWickets;
    public int wickets;
    public double batsmanAverage;
    public double bowlerAverage;
    public double batsmanStrikeRate;
    public double bowlerStrikeRate;


    public CricketAnalyserDAO(BatsmanData batsmanData) {
        this.player = batsmanData.player;
        this.four = batsmanData.four;
        this.six = batsmanData.six;
        this.runs = batsmanData.runs;
        this.batsmanAverage = batsmanData.batsmanAverage;
        this.batsmanStrikeRate = batsmanData.batsmanStrikeRate;
    }


    public CricketAnalyserDAO(BowlerData bowlerData) {
        this.player = bowlerData.player;
        this.fourWickets = bowlerData.fourWickets;
        this.fiveWickets = bowlerData.fiveWickets;
        this.bowlerAverage = bowlerData.bowlerAverage;
        this.bowlerStrikeRate = bowlerData.bowlerStrikeRate;
        this.economy = bowlerData.economy;
        this.wickets = bowlerData.wickets;
    }

    public Object getIPLDTO(CricketLeagueAnalyser.Cricket cricket) {
        if(cricket.equals(CricketLeagueAnalyser.Cricket.BATTING))
            return new BatsmanData(player,runs, batsmanAverage, batsmanStrikeRate,four,six);
        if(cricket.equals(CricketLeagueAnalyser.Cricket.BOWLING))
            return new BowlerData(player, bowlerAverage, bowlerStrikeRate,economy,fourWickets,fiveWickets,wickets);
        return new BatsmanAndBowlerData(player,runs,batsmanAverage,batsmanStrikeRate,four,six,bowlerAverage,wickets);

    }
}