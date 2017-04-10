package cs.dal.csci3130.undined.backend.services;

import org.apache.commons.beanutils.BeanUtils;
import cs.dal.csci3130.undined.backend.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide 
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class ReviewService {

    private static long nextId = 0;
    //Create fake data
    static String[] names = {"Danny", "Franky-Joe", "Bob", "Jim", "Brandon", "Tim", "Harry", "Sally", "James", "SAMTHEMAN"};
    static String[] reviewWords = {"Got stabbed here. Food was good though. 5/7", "I'd rather be fishing", "Good service", "STINKY POO HANDS :(", "Clean bathrooms", "Needs more cow bell", "Rude staff", "Bad service", "Great", "Awful"};


    private static ReviewService instance;

    public static ReviewService createService() {
	if(instance == null) {

	    final ReviewService reviewService = new ReviewService();

	    Random r = new Random(0);

	    for (int i = 0; i < 10; i++) {

		Review review = new Review();
		review.setId(nextId++);
		review.setName(names[r.nextInt(names.length)]);
		review.setReview(reviewWords[r.nextInt(reviewWords.length)]);

		reviewService.save(review);
	    }
	    instance = reviewService;
	}
	return instance;
    }

    private HashMap<Long, Review> reviews = new HashMap<>();


    //I think this is the search box
    public synchronized List<Review> findAll(String stringFilter) {
	ArrayList<Review> arrayList = new ArrayList<Review>();

	//For each restaurant
	for (Review review : reviews.values()) {
	    try {
		//If there is nothing in the search box
		//or
		//The Restaurant.toString() contains the text in the search box (not case sensitive)
		boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
			|| review.toString().toLowerCase()
			.contains(stringFilter.toLowerCase());
		//Add it to the list that will be returned
		if (passesFilter) {
		    arrayList.add(review.clone());
		}


		//If error, log it
	    } catch (CloneNotSupportedException ex) {
		Logger.getLogger(ReviewService.class.getName()).log(
			Level.SEVERE, null, ex);
	    }
	}

	//Sort the created list
	Collections.sort(arrayList, new Comparator<Review>() {
	    @Override
	    public int compare(Review o1, Review o2) {
		return (int) (o1.getId() - o2.getId());
	    }
	});

	//Return it
	return arrayList;
    }

    public synchronized List<Review> findAll() {
	ArrayList<Review> arrayList = new ArrayList<Review>();

	//For each restaurant
	for (Review review : reviews.values()) {
	    try {
		arrayList.add(review.clone());

	    } catch (CloneNotSupportedException ex) {
		Logger.getLogger(ReviewService.class.getName()).log(
			Level.SEVERE, null, ex);
	    }
	}

	//Sort the created list
	Collections.sort(arrayList, new Comparator<Review>() {
	    @Override
	    public int compare(Review o1, Review o2) {
		return (int) (o1.getId() - o2.getId());
	    }
	});

	//Return it
	return arrayList;
    }

    public synchronized long nextID(){
	return nextId++;

    }

    public synchronized long count() {
	return reviews.size();
    }

    public synchronized void delete(Review value) {
	//        restaurants.remove(value.getId());
	reviews.put(value.getId(),value);
    }

    public synchronized void save(Review entry) {
	try {
	    entry = (Review) BeanUtils.cloneBean(entry);
	} catch (Exception ex) {
	    throw new RuntimeException(ex);
	}
	reviews.put(entry.getId(), entry);
    }

}
