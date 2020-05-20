package cricketleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class BowlerDataCsv {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    @CsvBindByName(column = "Avg", required = true)
    public double bowlingAverage;

    @CsvBindByName(column = "SR", required = true)
    public double bowlingStrikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    public BowlerDataCsv () {}

    public BowlerDataCsv(String player, double bowlingAverage, double bowlingStrikeRate, double economy, int fourWickets, int fiveWickets) {
        this.fiveWickets = fiveWickets;
        this.bowlingAverage = bowlingAverage;
        this.fourWickets = fourWickets;
        this.economy = economy;
        this.player = player;
        this.bowlingStrikeRate = bowlingStrikeRate;
    }

    @Override
    public String toString() {
        return "BowlerDataCsv{" +
                "player='" + player + '\'' +
                ", fourWickets=" + fourWickets +
                ", fiveWickets=" + fiveWickets +
                ", bowlingAverage=" + bowlingAverage +
                ", bowlingStrikeRate=" + bowlingStrikeRate +
                ", economy=" + economy +
                '}';
    }

}
