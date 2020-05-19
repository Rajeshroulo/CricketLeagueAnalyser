package cricketleagueanalyser;


import csvbuilder.CSVBuilderException;
import csvbuilder.CSVBuilderFactory;
import csvbuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;


public class CricketLeagueAnalyser {

    Map<String, CricketAnalyserDAO> iplAnalyserMap = null;

    public CricketLeagueAnalyser() {
        iplAnalyserMap = new HashMap<>();
    }

    public int loadCricketLeagueData(String csvFilePath) throws CricketLeagueAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BatsmanDataCsv> csvFileIterator = csvBuilder.getCSVFileIterator(reader, BatsmanDataCsv.class);
            Iterable<BatsmanDataCsv> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false).
                    forEach(iplDataCsv -> iplAnalyserMap.put(iplDataCsv.player,new CricketAnalyserDAO(iplDataCsv)));
            if(iplAnalyserMap.size() == 0)
                throw new CricketLeagueAnalyserException("NO_DATA",
                        CricketLeagueAnalyserException.ExceptionType.NO_DATA);
            return this.iplAnalyserMap.size();
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (CSVBuilderException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }


}
