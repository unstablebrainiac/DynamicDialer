package com.tobedecided.dynamicdialer;

import java.util.Comparator;

/**
 * Created by sajalnarang on 8/1/17.
 */

public class Prediction implements Comparable<Prediction> {
    private String name;
    private double probability;

    public Prediction(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public int compareTo(Prediction o) {
        if (this.probability == o.probability)
            return 0;
        return this.probability > o.probability ? -1 : 1;
    }

    public static class Comparators {
        public static Comparator<Prediction> PROBABILITY = new Comparator<Prediction>() {
            @Override
            public int compare(Prediction o1, Prediction o2) {
                return o1.compareTo(o2);
            }
        };
    }
}
