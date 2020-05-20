package cricketleagueanalyser;

public class CricketLeagueAnalyserException extends Exception {

    enum ExceptionType {
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE,NO_DATA
    }

    ExceptionType type;

    public CricketLeagueAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
