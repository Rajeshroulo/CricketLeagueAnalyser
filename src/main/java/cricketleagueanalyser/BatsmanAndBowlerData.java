package cricketleagueanalyser;

public class BatsmanAndBowlerData {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double battingAverage;
    public double bowlerAverage;
    public double battingStrikeRate;


    public BatsmanAndBowlerData(String player, int runs, double battingAverage, double battingStrikeRate, int four, int six, double bowlerAverage) {
        this.player = player;
        this.battingAverage = battingAverage;
        this.battingStrikeRate = battingStrikeRate;
        this.runs = runs;
        this.four = four;
        this.six = six;
        this.bowlerAverage = bowlerAverage;
    }
}
