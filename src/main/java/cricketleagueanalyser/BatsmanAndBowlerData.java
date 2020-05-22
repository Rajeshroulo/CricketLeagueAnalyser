package cricketleagueanalyser;

public class BatsmanAndBowlerData {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double batsmanAverage;
    public double bowlerAverage;
    public double batsmanStrikeRate;
    public int wickets;

    public BatsmanAndBowlerData(String player, int runs, double batsmanAverage, double batsmanStrikeRate, int four, int six, double bowlerAverage, int wickets) {
        this.player = player;
        this.batsmanAverage = batsmanAverage;
        this.batsmanStrikeRate = batsmanStrikeRate;
        this.runs = runs;
        this.four = four;
        this.six = six;
        this.bowlerAverage = bowlerAverage;
        this.wickets = wickets;

    }
}
