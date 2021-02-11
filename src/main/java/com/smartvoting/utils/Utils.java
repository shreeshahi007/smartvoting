package com.smartvoting.utils;

import java.util.*;

public class Utils {
    public double getMedian(List<Integer> numbers){
        double median = 0;
        int size = numbers.size();
        if(size==0){
            return 0;
        }
        Collections.sort(numbers);
        if(size%2==0){
            int mid = size/2;
            median = (numbers.get(mid)+numbers.get(mid-1))/2;
        } else {
            median = numbers.get(size/2);
        }
        return median;
    }

    public List<Integer> getMode(List<Integer> numbers){
        int size = numbers.size();
        int count[] = new int[size];

        List<Integer> modes = new ArrayList<>();
        modes.add(0);
        return modes;
    }
}
