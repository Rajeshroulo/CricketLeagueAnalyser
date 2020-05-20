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
    public abstract Map<String, CricketAnalyserDAO> loadIPLData(String csvFilePath) throws CricketLeagueAnalyserException;

    Map<String, CricketAnalyserDAO> iplAnalyserMap = new HashMap<>();

    public <E> Map<String,CricketAnalyserDAO> getCricketLeagueData(Class<E> iplDataCsvClass,String csvFilePath) throws CricketLeagueAnalyserException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, iplDataCsvClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if(iplDataCsvClass.getName().equals("cricketleagueanalyser.BatsmanDataCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(BatsmanDataCsv.class::cast).
                        forEach(iplDataCsv -> iplAnalyserMap.put(iplDataCsv.player, new CricketAnalyserDAO(iplDataCsv)));
            } else if(iplDataCsvClass.getName().equals("cricketleagueanalyser.BowlerDataCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(BowlerDataCsv.class::cast).
                        forEach(iplDataCsv -> iplAnalyserMap.put(iplDataCsv.player, new CricketAnalyserDAO(iplDataCsv)));
            }

            if(iplAnalyserMap.size() == 0)
                throw new CricketLeagueAnalyserException("NO_DATA",
                        CricketLeagueAnalyserException.ExceptionType.NO_DATA);
            return this.iplAnalyserMap;
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
