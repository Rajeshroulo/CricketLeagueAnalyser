package cricketleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class BatsmanData {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public double batsmanAverage;


    @CsvBindByName(column = "SR", required = true)
    public double batsmanStrikeRate;

    public BatsmanData() { }

    public BatsmanData(String player, int runs, double batsmanAverage, double batsmanStrikeRate, int four, int six) {
        this.player = player;
        this.batsmanAverage = batsmanAverage;
        this.batsmanStrikeRate = batsmanStrikeRate;
        this.runs = runs;
        this.four = four;
        this.six = six;
    }

}
