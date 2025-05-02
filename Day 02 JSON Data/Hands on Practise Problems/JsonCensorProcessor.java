import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;
import com.opencsv.*;
import java.io.*;
import java.util.*;
class Match {
    public String team1;
    public String team2;
    public String player_of_match;
}
public class JsonCensorProcessor {
    public static void processJson(String inputPath, String outputPath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Match> matches = mapper.readValue(new File(inputPath), new TypeReference<List<Match>>() {});

        for (Match m : matches) {
            m.team1 = censorTeam(m.team1);
            m.team2 = censorTeam(m.team2);
            m.player_of_match = "REDACTED";
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), matches);
    }
    private static String censorTeam(String team) {
        if (team == null || team.isEmpty()) return team;
        String[] parts = team.split(" ", 2);
        return parts[0] + " ***";
    }
}
class CsvCensorProcessor {
    public static void processCsv(String inputPath, String outputPath) throws IOException {
        try (
            CSVReader reader = new CSVReader(new FileReader(inputPath));
            CSVWriter writer = new CSVWriter(new FileWriter(outputPath))
        ) {
            List<String[]> allRows = reader.readAll();
            String[] header = allRows.get(0);
            writer.writeNext(header);

            int team1Idx = Arrays.asList(header).indexOf("team1");
            int team2Idx = Arrays.asList(header).indexOf("team2");
            int playerIdx = Arrays.asList(header).indexOf("player_of_match");

            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                if (team1Idx >= 0) row[team1Idx] = censorTeam(row[team1Idx]);
                if (team2Idx >= 0) row[team2Idx] = censorTeam(row[team2Idx]);
                if (playerIdx >= 0) row[playerIdx] = "REDACTED";
                writer.writeNext(row);
            }
        }
    }
    private static String censorTeam(String team) {
        if (team == null || team.isEmpty()) return team;
        String[] parts = team.split(" ", 2);
        return parts[0] + " ***";
    }
}
class IPLCensorApp {
    public static void main(String[] args) {
        try {
            JsonCensorProcessor.processJson("ipl_matches.json", "censored_ipl.json");
            CsvCensorProcessor.processCsv("ipl_matches.csv", "censored_ipl.csv");
            System.out.println("Censorship applied and data saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.7.1</version>
    </dependency>
</dependencies>