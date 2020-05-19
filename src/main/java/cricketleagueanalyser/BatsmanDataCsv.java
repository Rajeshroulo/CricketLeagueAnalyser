package cricketleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class BatsmanDataCsv {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    public BatsmanDataCsv() { }

    @Override
    public String toString() {
        return "IPLRunsDataCsv{" +
                "player='" + player + '\'' +
                ", four=" + four +
                ", six=" + six +
                ", runs=" + runs +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                '}';
    }


}
