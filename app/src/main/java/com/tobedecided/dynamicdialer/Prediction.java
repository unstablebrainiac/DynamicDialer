package com.tobedecided.dynamicdialer;

import java.util.Comparator;

/**
 * Created by sajalnarang on 8/1/17.
 */

public class Prediction implements Comparable<Prediction> {
    private String contact_id;
    private String name;
    private String number;
    private double probability;

    public Prediction(long contact_id, String name, String number, double probability) {
        this.contact_id = String.valueOf(contact_id);
        this.name = name;
        this.number = number;
        this.probability = probability;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
