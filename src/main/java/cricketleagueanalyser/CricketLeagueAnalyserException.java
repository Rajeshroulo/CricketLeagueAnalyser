package cricketleagueanalyser;

public class CricketLeagueAnalyserException extends Exception {

    enum ExceptionType {
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE, NO_DATA
    }

    ExceptionType type;

    public CricketLeagueAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CricketLeagueAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
    public CricketLeagueAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }
}
