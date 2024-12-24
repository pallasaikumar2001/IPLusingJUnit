import static org.junit.Assert.assertSame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

import org.junit.Test;

public class Main {
    public static final int MATCH_ID = 0;
    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int NON_STRIKER = 7;
    public static final int BOWLER = 8;
    public static final int IS_SUPER_OVER = 9;
    public static final int WIDE_RUNS = 10;
    public static final int BYE_RUNS = 11;
    public static final int LEGBYE_RUNS = 12;
    public static final int NOBALL_RUNS = 13;
    public static final int PENALTY_RUNS = 14;
    public static final int BATSMAN_RUNS = 15;
    public static final int EXTRA_RUNS = 16;
    public static final int TOTAL_RUNS = 17;
    public static final int PLAYER_DISMISSED = 18;
    public static final int DISMISSAL_KIND = 19;
    public static final int FIELDER = 20;

    public static final int ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int DL_APPLIED = 9;
    public static final int WINNER = 10;
    public static final int WIN_BY_RUNS = 11;
    public static final int WIN_BY_WICKETS = 12;
    public static final int PLAYER_OF_MATCH = 13;
    public static final int VENUE = 14;
    public static final int UMPIRE1 = 15;
    public static final int UMPIRE2 = 16;
    public static final int UMPIRE3 = 17;

    private static List<Match> setMatchesData() {
        List<Match> matches = new ArrayList<>();
        String matchesPath = "/home/saikumar-palla/Desktop/java/ipljunit/data/matches.csv";

        try (BufferedReader matchesReader = new BufferedReader(new FileReader(matchesPath))) {
            String nextLine;
            matchesReader.readLine();

            while ((nextLine = matchesReader.readLine()) != null) {
                String[] nextRecord = nextLine.split(",", -1);
                Match match = new Match();

                match.setId(Integer.parseInt(nextRecord[ID]));
                match.setSeason(Integer.parseInt(nextRecord[SEASON]));
                match.setCity(nextRecord[CITY]);
                match.setDate(LocalDate.parse(nextRecord[DATE]));
                match.setTeam1(nextRecord[TEAM1]);
                match.setTeam2(nextRecord[TEAM2]);
                match.setTossWinner(nextRecord[TOSS_WINNER]);
                match.setTossDecision(nextRecord[TOSS_DECISION]);
                match.setResult(nextRecord[RESULT]);
                match.setDlApplied(Boolean.parseBoolean(nextRecord[DL_APPLIED]));
                match.setWinner(nextRecord[WINNER]);
                match.setWinByRuns(Integer.parseInt(nextRecord[WIN_BY_RUNS]));
                match.setWinByWickets(Integer.parseInt(nextRecord[WIN_BY_WICKETS]));
                match.setPlayerOfMatch(nextRecord[PLAYER_OF_MATCH]);
                match.setVenue(nextRecord[VENUE]);
                match.setUmpire1(nextRecord[UMPIRE1]);
                match.setUmpire2(nextRecord[UMPIRE2]);
                match.setUmpire3(nextRecord[UMPIRE3]);

                matches.add(match);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return matches;
    }
    private static List<Delivery> setDeliveriesData() {
        List<Delivery> deliveries = new ArrayList<>();
        String deliveriessPath = "/home/saikumar-palla/Desktop/java/ipl/data/deliveries.csv";
    
        try (BufferedReader deliveriesReader = new BufferedReader(new FileReader(deliveriessPath))) {
            String nextLine;
    
            deliveriesReader.readLine(); 
    
            while ((nextLine = deliveriesReader.readLine()) != null) {
                if (nextLine.trim().isEmpty()) {  
                    continue;
                }
    
                String[] nextRecord = nextLine.split(",", -1);
    
                if (nextRecord.length <= 1) {
                    continue;
                }
    
                Delivery delivery = new Delivery();
    
                delivery.setMatchId(parseIntSafe(nextRecord[MATCH_ID]));
                delivery.setInning(Integer.parseInt(nextRecord[INNING]));
                delivery.setBattingTeam(nextRecord[BATTING_TEAM]);
                delivery.setBowlingTeam(nextRecord[BOWLING_TEAM]);
                delivery.setOver(Integer.parseInt(nextRecord[OVER]));
                delivery.setBall(Integer.parseInt(nextRecord[BALL]));
                delivery.setBatsman(nextRecord[BATSMAN]);
                delivery.setNonStriker(nextRecord[NON_STRIKER]);
                delivery.setBowler(nextRecord[BOWLER]);
                delivery.setSuperOver(Boolean.parseBoolean(nextRecord[IS_SUPER_OVER]));
                delivery.setWideRuns(Integer.parseInt(nextRecord[WIDE_RUNS]));
                delivery.setByeRuns(Integer.parseInt(nextRecord[BYE_RUNS]));
                delivery.setLegbyeRuns(Integer.parseInt(nextRecord[LEGBYE_RUNS]));
                delivery.setNoballRuns(Integer.parseInt(nextRecord[NOBALL_RUNS]));
                delivery.setPenaltyRuns(Integer.parseInt(nextRecord[PENALTY_RUNS]));
                delivery.setBatsmanRuns(Integer.parseInt(nextRecord[BATSMAN_RUNS]));
                delivery.setExtraRuns(Integer.parseInt(nextRecord[EXTRA_RUNS]));
                delivery.setTotalRuns(Integer.parseInt(nextRecord[TOTAL_RUNS]));
                delivery.setPlayerDismissed(nextRecord[PLAYER_DISMISSED]);
                delivery.setDismissalKind(nextRecord[DISMISSAL_KIND]);
                delivery.setFielder(nextRecord[FIELDER]);
    
                deliveries.add(delivery);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        // System.out.println(deliveries);
    
        return deliveries;
    }
    private static int parseIntSafe(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + str);
            return 0;
        }
    }
    


    //1. Number of matches played per year of all the years in IPL.
    

    @Test
    public void matchesPerSeason() {
        // Map<Integer, Map<Integer, Integer>> matchesPerYearPerSeason = new HashMap<>();
        List<Match> matches = setMatchesData(); 

        Map<Integer, Map<Integer, Integer>> matchesperyear = new HashMap<>();
    
        for (Match match : matches) {
            int year = match.getSeason();
            int season = match.getSeason();
    
            matchesperyear.putIfAbsent(year, new HashMap<>());
            Map<Integer, Integer> seasonMap = matchesperyear.get(year);
            seasonMap.put(season, seasonMap.getOrDefault(season, 0) + 1);
        }
        Map<Integer,Map<Integer,Integer>> expected=matchesperyear;
        // System.out.println(matchesperyear);
        assertSame(expected, matchesperyear);
    }



    //2. Number of matches won of all teams over all the years of IPL.


    @Test
    public void winsperseason(){
        List<Match> matches = setMatchesData(); 

        Map<Integer,Map<String,Integer>> teamwins=new HashMap<>();
        for (Match match:matches){
            int season=match.getSeason();
            String team=match.getWinner();
            teamwins.putIfAbsent(season, new HashMap<>());
            Map<String,Integer>teamwinner=teamwins.get(season);
            teamwinner.put(team,teamwinner.getOrDefault(team, 0)+1);
            
        }
        Map<Integer, Map<String, Integer>> expected = teamwins;

        // Map<Integer, Map<String, Integer>> expected = new HashMap<>();
        // Map<String, Integer> innerMap = new HashMap<>(); 
        // innerMap.put("saikumar", 32); 
        // expected.put(23, innerMap);

        assertSame(expected, teamwins);
                
    }



    //3. For the year 2016 get the extra runs conceded per team.


    @Test
    public void ExtrasConcededInYear() {
        Set<Integer> matchIds = new HashSet<>();
        List<Match> matches = setMatchesData(); 
        List<Delivery> deliveries = setDeliveriesData(); 
        int year=2016;

        for (Match match : matches) {
            if (match.getSeason() == year) {
                matchIds.add(match.getId());
            }
        }
        // System.out.println(matchIds);
    
        Map<String, Integer> teamExtras = new HashMap<>();
    
        for (Delivery delivery : deliveries) {
            if (matchIds.contains(delivery.getMatchId())) {
                teamExtras.putIfAbsent(delivery.getBattingTeam(), 0);
                teamExtras.put(delivery.getBattingTeam(), teamExtras.get(delivery.getBattingTeam()) + delivery.getExtraRuns());
            }
        }
        Map<String,Integer> expected=teamExtras;
        
        assertSame(expected, teamExtras);

        
    }



    //4. Most Player of the match players

    @Test
    public void mostpom() {
        List<Match> matches = setMatchesData();
        Map<Integer, Map<String, Integer>> seasonmom = new HashMap<>();

        for (Match match : matches) {
            int season = match.getSeason();
            String player = match.getPlayerOfMatch();
            seasonmom.putIfAbsent(season, new HashMap<>());

            Map<String, Integer> playersInSeason = seasonmom.get(season);
            playersInSeason.put(player, playersInSeason.getOrDefault(player, 0) + 1);
        }

        Map<Integer, Map<String, Integer>> expected = seasonmom;

        for (Map.Entry<Integer, Map<String, Integer>> entry : seasonmom.entrySet()) {
            Map<String, Integer> playersInSeason = entry.getValue();
            List<Map.Entry<String, Integer>> sortedPlayers = new ArrayList<>(playersInSeason.entrySet());
            sortedPlayers.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        }
        assertSame(expected, seasonmom);
    }



    // Win the toss and win the match per each season


    @Test
    public void wintossandmatch() {
        List<Match> matches = setMatchesData(); 

        Map<Integer, List<String[]>> tossandmatch = new TreeMap<>();
        for (Match match : matches) {
            int season = match.getSeason();
            String twinner = match.getTossWinner();
            String matchWinner = match.getWinner();
            tossandmatch.putIfAbsent(season, new ArrayList<>());
            tossandmatch
                .get(season)
                .add(new String[]{twinner, matchWinner});
        }

        Map<Integer, Map<String, Integer>> win = new TreeMap<>(); 

        for (Map.Entry<Integer, List<String[]>> entry : tossandmatch.entrySet()) {
            int season = entry.getKey();
            Map<String, Integer> teamWins = new HashMap<>();
            for (String[] result : entry.getValue()) {
                String tosswinner = result[0];
                String matchwinner = result[1];
                if (tosswinner.equals(matchwinner)) {
                    teamWins.put(matchwinner, teamWins.getOrDefault(matchwinner, 0) + 1);
                }
            }
            win.put(season, teamWins); 
        }
        Map<Integer,Map<String,Integer>> expected=win;
        // Map<Integer, Map<String, Integer>> expected = new HashMap<>();
        // Map<String, Integer> innerMap = new HashMap<>(); 
        // innerMap.put("saikumar", 32); 
        // expected.put(23, innerMap);
        assertSame(expected, win);    
    }



    
    






}
