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


public abstract class CricketLeagueDataAdapter {
    public abstract Map<String, CricketAnalyserDAO> loadIPLData(String... csvFilePath) throws CricketLeagueAnalyserException;

    Map<String, CricketAnalyserDAO> cricketLeagueData = null;

    public <E> Map<String,CricketAnalyserDAO> getCricketLeagueData(Class<E> iplDataClass, String... csvFilePath) throws CricketLeagueAnalyserException {
        cricketLeagueData = new HashMap<>();
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0])))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader,  iplDataClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if( iplDataClass.getName().equals("cricketleagueanalyser.BatsmanData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                             .map(BatsmanData.class::cast)
                             .forEach(iplDataCsv -> cricketLeagueData.put(iplDataCsv.player, new CricketAnalyserDAO(iplDataCsv)));
            } else if(iplDataClass.getName().equals("cricketleagueanalyser.BowlerData")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                             .map(BowlerData.class::cast)
                             .forEach(iplDataCsv -> cricketLeagueData.put(iplDataCsv.player, new CricketAnalyserDAO(iplDataCsv)));
            }

            if(cricketLeagueData.size() == 0)
                throw new CricketLeagueAnalyserException("NO_DATA",
                        CricketLeagueAnalyserException.ExceptionType.NO_DATA);
            return this.cricketLeagueData;
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
