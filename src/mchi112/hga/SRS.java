package mchi112.hga;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Stochastic Remainder Selection
 */
public class SRS {

    public List<Tour> reproduce(List<Tour> population) throws Exception {

        List<Tour> newPopulation = new ArrayList<Tour>();

        // Calculate average fitness
        float avgFitness = 0;
        float totFitness = 0;
        for(Tour t : population) {
            totFitness += t.getFitness();
        }
        avgFitness = totFitness/population.size();

        // Assign expected count
        for(Tour t : population) {
            float expectedCount = t.getFitness()/avgFitness;
            t.setExpectedCount(expectedCount - (int)expectedCount);
         }

        // Generate select new population
        Random rng = new Random();
        int stall = 0;
        while(newPopulation.size() < population.size()) {

            // If the population generation is stalling
            if(stall > population.size()) {
                for(Tour t : population) {
                    t.setExpectedCount(t.getExpectedCount() + 0.1f);
                }
                stall = 0;
            }


            int index = rng.nextInt(population.size());

            Tour tour = population.get(index);

            if((float)Math.random() < tour.getExpectedCount()) {
                newPopulation.add(population.get(index));
                tour.setExpectedCount(tour.getExpectedCount() - 0.5f);
                stall = 0;
            } else {
                stall++;
            }
        }

        return newPopulation;
    }
}
