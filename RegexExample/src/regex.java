import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.lang3.StringUtils;
public class regex {
	
	public static void main(String[] args) throws IOException {
		Map<String, Integer> freq = new HashMap<String, Integer>();
		Map<String, Integer> Repofreq = new HashMap<String, Integer>();
		Map<String, Integer> ICJIfreq = new HashMap<String, Integer>();
		Map<String, Integer> Unknownfreq = new HashMap<String, Integer>();
		File folder = new File("/Users/jacobantoine/Desktop/GAW/");
		File[] listOfFiles = folder.listFiles();
		int lineCount = 0;
		ArrayList<String> commandList = new ArrayList();
		commandList.add("cmd");
		commandList.add("dynamicui");
		commandList.add("qnsfromscreen");
		commandList.add("images");
		commandList.add("scripts");
		
		
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) 
				{
				
					line = line.toLowerCase();
					/*
					for (int d = 0; d < commandList.size(); d++) {
						if ((line.contains("equest")) && (line.contains("repository")) && !(line.contains("icji")) && line.contains(commandList.get(d))) {
							System.out.println(line);
						}
					}
					*/
					for (int z = 0; z < commandList.size(); z++) 
					{
						if (line.contains(commandList.get(z))) 
							lineCount += 1;
					}
					if (line.contains("equest")) {
						if (line.contains("dynamicui")) 
						{
							String word = "dynamicui/" + line.substring(line.lastIndexOf("reqtype=") + 8, line.indexOf("&"));
							addCount(freq, word);
						}
						if (line.contains("cmd"))
						{
							if (StringUtils.substringBetween(line, "cmd=", "&") == null) 
							{
								String word = "cmd/" + StringUtils.substringBetween(line, "cmd=", " http/");
								addCount(freq, word);
							} 
							else 
							{
								String word = "cmd/" + StringUtils.substringBetween(line, "cmd=", "&");
								addCount(freq, word);
							}
						}
						if (line.contains("qnsfromscreen")) 
						{
							String word = "jsp/" + StringUtils.substringBetween(line, "qnsfromscreen=", ".jsp");
							addCount(freq, word);
						}
						if (line.contains("images")) 
						{
							if (StringUtils.substringBetween(line, "images/", ".ico") == null)
							{
								String word = "images/" + StringUtils.substringBetween(line, "images/icon/", ".gif");
								addCount(freq, word);
							}
							else
							{
								String word = "images/" + StringUtils.substringBetween(line, "images/", ".ico");
								addCount(freq, word);
							}
						}
						if (line.contains("scripts"))
						{
							String word = "scripts/" + line.substring(line.lastIndexOf("equest/scripts/") + 15, line.indexOf(".js")) + ".js";
							addCount(freq, word);
						}
					}
					
					//EQUEST ^
					
					//Repository
					else if(line.contains("repository")) {
						if (line.contains("dynamicui"))
						{
							String word = "dynamicui/" + line.substring(line.lastIndexOf("reqtype=") + 8, line.indexOf("&"));
							addCount(Repofreq, word);
						}
						if (line.contains("cmd"))
						{
							if (StringUtils.substringBetween(line, "cmd=", "&") == null) 
							{
								String word = "cmd/" + StringUtils.substringBetween(line, "cmd=", " http/");
								addCount(Repofreq, word);
							} 
							else 
							{
								String word = "cmd/" + StringUtils.substringBetween(line, "cmd=", "&");
								addCount(Repofreq, word);
							}
						}
						if (line.contains("qnsfromscreen")) 
						{
							String word = "jsp/" + StringUtils.substringBetween(line, "qnsfromscreen=", ".jsp");
							addCount(Repofreq, word);
						}
						if (line.contains("images")) 
						{
							if (StringUtils.substringBetween(line, "images/", ".ico") == null)
							{
								String word = "images/" + StringUtils.substringBetween(line, "images/icon/", ".gif");
								addCount(Repofreq, word);
							}
							else
							{
								String word = "images/" + StringUtils.substringBetween(line, "images/", ".ico");
								addCount(Repofreq, word);
							}
						}
						if (line.contains("scripts"))
						{
							String word = "scripts/" + line.substring(line.lastIndexOf("repository/scripts/") + 15, line.indexOf(".js")) + ".js";
							addCount(Repofreq, word);
						}
					}
					//ICJI
					else if (line.contains("icji")) {
						if (line.contains("dynamicui")) 
						{
							String word = "dynamicui/" + line.substring(line.lastIndexOf("reqtype=") + 8, line.indexOf("&"));
							addCount(ICJIfreq, word);
						}
						if (line.contains("cmd"))
						{
							if (StringUtils.substringBetween(line, "cmd=", "&") == null) 
							{
								String word = "cmd/" + StringUtils.substringBetween(line, "cmd=", " http/");
								addCount(ICJIfreq, word);
							} 
							else 
							{
								String word = "cmd/" + StringUtils.substringBetween(line, "cmd=", "&");
								addCount(ICJIfreq, word);
							}
						}
						if (line.contains("qnsfromscreen")) 
						{
							String word = "jsp/" + StringUtils.substringBetween(line, "qnsfromscreen=", ".jsp");
							addCount(ICJIfreq, word);
						}
						if (line.contains("images")) 
						{
							if (StringUtils.substringBetween(line, "images/", ".ico") == null)
							{
								String word = "images/" + StringUtils.substringBetween(line, "images/icon/", ".gif");
								addCount(ICJIfreq, word);
							}
							else
							{
								String word = "images/" + StringUtils.substringBetween(line, "images/", ".ico");
								addCount(ICJIfreq, word);
							}
						}
						if (line.contains("scripts"))
						{
							String word = "scripts/" + line.substring(line.lastIndexOf("icji/scripts/") + 15, line.indexOf(".js")) + ".js";
							addCount(ICJIfreq, word);
						}
					}
					
					//Unknown Map
					if (!(line.contains("equest")) && !(line.contains("repository")) && !(line.contains("icji")) && line.contains("images")) {
						String word = "/images";
						addCount(Unknownfreq, word);
					}
					if (!(line.contains("equest")) && !(line.contains("repository")) && !(line.contains("icji")) && line.contains("cmd")) {
						String word = "/cmd";
						addCount(Unknownfreq, word);
					}
					if (!(line.contains("equest")) && !(line.contains("repository")) && !(line.contains("icji")) && line.contains("dynamicui")) {
						String word = "/dynamicui";
						addCount(Unknownfreq, word);
					}
					if (!(line.contains("equest")) && !(line.contains("repository")) && !(line.contains("icji")) && line.contains("scripts")) {
						String word = "/scripts";
						addCount(Unknownfreq, word);
					}
					if (!(line.contains("equest")) && !(line.contains("repository")) && !(line.contains("icji")) && line.contains("qnsfromscreen")) {
						String word = "/qnsfromscreen";
						addCount(Unknownfreq, word);
					}
					
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  
		}
		Map<String, Integer> sortedMap = freq.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
		Map<String, Integer> sortedRepoMap = Repofreq.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
		Map<String, Integer> sortedICJIMap = ICJIfreq.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
		Map<String, Integer> sortedUnknownMap = Unknownfreq.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));
		
		System.out.println("Equest Map");
		for (int i = 0; i < sortedMap.keySet().size(); i++) {
			System.out.print("[");
			int x = 71;
			
			System.out.print(sortedMap.keySet().toArray()[i].toString());
			
			for (int z = 0; z < x - sortedMap.keySet().toArray()[i].toString().length(); z++) {
				System.out.print(" ");
			}
			System.out.print(" | " + sortedMap.values().toArray()[i].toString());
			System.out.print("]");
			System.out.println("\n");
			//71 chars longest
		}
		System.out.println("Repo Map");
		
		for (int i = 0; i < sortedRepoMap.keySet().size(); i++) {
			System.out.print("[");
			int x = 71;
			
			System.out.print(sortedRepoMap.keySet().toArray()[i].toString());
			
			for (int z = 0; z < x - sortedRepoMap.keySet().toArray()[i].toString().length(); z++) {
				System.out.print(" ");
			}
			System.out.print(" | " + sortedRepoMap.values().toArray()[i].toString());
			System.out.print("]");
			System.out.println("\n");
			//71 chars longest
		}
		System.out.println("ICJI Map");
		for (int i = 0; i < sortedICJIMap.keySet().size(); i++) {
			System.out.print("[");
			int x = 71;
			
			System.out.print(sortedICJIMap.keySet().toArray()[i].toString());
			
			for (int z = 0; z < x - sortedICJIMap.keySet().toArray()[i].toString().length(); z++) {
				System.out.print(" ");
			}
			System.out.print(" | " + sortedICJIMap.values().toArray()[i].toString());
			System.out.print("]");
			System.out.println("\n");
			//71 chars longest
		}
		
		System.out.println("Unknown Map");
		for (int i = 0; i < sortedUnknownMap.keySet().size(); i++) {
			System.out.print("[");
			int x = 71;
			
			System.out.print(sortedUnknownMap.keySet().toArray()[i].toString());
			
			for (int z = 0; z < x - sortedUnknownMap.keySet().toArray()[i].toString().length(); z++) {
				System.out.print(" ");
			}
			System.out.print(" | " + sortedUnknownMap.values().toArray()[i].toString());
			System.out.print("]");
			System.out.println("\n");
			//71 chars longest
		}
		
		int mapCount = 0;
		for (String key : ICJIfreq.keySet()) 
			mapCount += ICJIfreq.get(key);
		for (String key : Repofreq.keySet()) 
			mapCount += Repofreq.get(key);
		for (String key : freq.keySet()) 
			mapCount += freq.get(key);
		for (String key : Unknownfreq.keySet()) 
			mapCount += Unknownfreq.get(key);
		
		System.out.println("Map Count: " + mapCount + " Line Count: " + lineCount);
	}
	
	public static void addCount(Map<String, Integer> map, String key)
	{
		Integer count = map.get(key);
		if (count == null)
		  count = 0;
		map.put(key, count + 1);
	}

}
