import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            if(args.length==0) {
                throw new RuntimeException("expect one input arg");
            }
            String[] words;
            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);
            Map<String, Integer> map = new HashMap<>();
            String s;
            while (true) {
                if (!((s = br.readLine()) != null)) break;
                words = s.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");   //Split the word using space
                for(String word : words) {
                    if(map.containsKey(word))
                        map.put(word, map.get(word)+1);
                    else
                        map.put(word, 1);
                }
            }
            fr.close();
            PriorityQueue<Map.Entry<String, Integer>> topWords = new PriorityQueue<>(
                    (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
            );
            for(Map.Entry<String, Integer> entry: map.entrySet())
            {
                topWords.offer(entry);
                if(topWords.size()>5)
                    topWords.poll();
            }
            int n = topWords.size();
            String[] result = new String[n];
            for(int i=0; i<n; i++) {
                Map.Entry<String, Integer> entry = topWords.poll();
                result[n-i-1] = entry.getKey() + " - " +entry.getValue();
            }
            for(String r:result) {
                System.out.println(r);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
