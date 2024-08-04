import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class HotelSortForGuestReview {
	
	public static String hotels(String words, String[][] reviews) {
		Map<Integer, Integer> hotelReviewCount = new HashMap<>();
		for (int x = 0; x < reviews.length; x++) {
			int hotelId = Integer.valueOf(reviews[x][0]);		
			int count = wordCountOnReview(words, reviews[x][1]);
			if (hotelReviewCount.containsKey(hotelId)){
				count += hotelReviewCount.get(hotelId);
			}
			hotelReviewCount.put(hotelId, count);
		}
		
		return getSortedHotelId(hotelReviewCount);
	}
	
	
	private static String getSortedHotelId(Map<Integer, Integer> hotelReviewCount) {
		// Create a LinkedHashMap to store stored key-value pairs
		Map<Integer, Integer> orderedHotelReview = new LinkedHashMap<>();
		StringBuffer result = new StringBuffer();
		
		// Sorting a map using lambda expressions
		hotelReviewCount.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed()).forEachOrdered(x -> orderedHotelReview.put(x.getKey(),  x.getValue()));
		
		// Concatenate sorted map keys
		orderedHotelReview.keySet().forEach(a -> result.append(a + " "));		
		return result.toString().trim();
	}
	
	
	private static int wordCountOnReview(String words, String review) {
		// Check words in the review against the words statement.
		Stream<String> str = Arrays.stream(review.split(" ")).filter(a -> {
			a = a.replace(",", "").replace(".", "");
			return words.matches(".*\\b"+a.trim().toLowerCase()+"\\b.*");
			});
		return (int) str.count();
	}
}
