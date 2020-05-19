package cricketleagueanalyser;

public class CricketAnalyserDAO {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double average;
    public double strikeRate;

    public CricketAnalyserDAO(BatsmanDataCsv batsmanDataCsv) {
        this.player = batsmanDataCsv.player;
        this.four = batsmanDataCsv.four;
        this.six = batsmanDataCsv.six;
        this.runs = batsmanDataCsv.runs;
        this.average = batsmanDataCsv.average;
        this.strikeRate = batsmanDataCsv.strikeRate;
    }


}
