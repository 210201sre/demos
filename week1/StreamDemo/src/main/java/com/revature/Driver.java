package com.revature;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class Driver {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();

		names.add("Peter Parker");
		names.add("Matthew Murdock");
		names.add("Bruce Banner");
		names.add("Steven Strange");
		names.add("SpongeBob Square Pants");
		names.add("Matthew Oberlies");
		names.add("Ernest Kim");
		names.add("Tom Riddle");
		names.add("Rick Sanchez");
		names.add("Micky Mouse");
		names.add("Mario Mario");
		names.add("Luigi Mario");
		names.add("David Bowie");
		names.add("Wario");
		names.add("Prince");
		names.add("Klaus");
		
		int occurencesOfA = names.stream()
					.map( (name) -> name.toLowerCase())
					.filter( (name) -> name.contains("a"))
					.mapToInt( (name) -> name.split("a").length - 1)
					.sum();
		
		System.out.println(occurencesOfA);
		
		List<String> filteredNames = names.stream()
					.map( (name) -> name.toLowerCase())
					.filter( (name) -> name.contains("k") || name.contains("l"))
					.filter( (name) -> (name.length() >= 6))
					.collect(Collectors.toList());
		
		System.out.println(filteredNames);
		
		int averageLength = filteredNames.stream()
					.collect(Collectors.averagingInt( (name) -> name.length()))
					.intValue();
		
		System.out.println(averageLength);
		
		IntSummaryStatistics stats = filteredNames.stream()
					.mapToInt( (name) -> name.length())
					.summaryStatistics();
		
		System.out.println(stats.getAverage());
	}
}
