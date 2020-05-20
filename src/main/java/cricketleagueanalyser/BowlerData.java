package cricketleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class BowlerData {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public double bowlingAverage;

    @CsvBindByName(column = "SR", required = true)
    public double bowlingStrikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;
    public double bowlerAverage;
    public double bowlerStrikeRate;

    public BowlerData () {}

    public BowlerData(String player, double bowlingAverage, double bowlingStrikeRate, double economy, int fourWickets, int fiveWickets, int wickets) {
        this.fiveWickets = fiveWickets;
        this.bowlingAverage = bowlingAverage;
        this.fourWickets = fourWickets;
        this.economy = economy;
        this.player = player;
        this.bowlingStrikeRate = bowlingStrikeRate;
        this.wickets = wickets;
    }

}
