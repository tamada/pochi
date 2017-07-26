package com.github.pochi.runner.birthmarks.comparators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.pochi.runner.birthmarks.entities.Birthmark;
import com.github.pochi.runner.birthmarks.entities.Element;
import com.github.pochi.runner.config.Configuration;

public class EditDistanceBirthmarkComparator extends AbstractBirthmarkComparator{
    public EditDistanceBirthmarkComparator(){
        super(new ComparatorType("EditDistance"));
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right, Configuration config) {
        List<Element> leftList = toList(left);
        List<Element> rightList = toList(right);
        return new Similarity(calculate(leftList, rightList));
    }

    private List<Element> toList(Birthmark birthmark){
        List<Element> list = new ArrayList<>();
        birthmark.forEach(list::add);
        return list;
    }

    private double calculate(List<Element> left, List<Element> right){
        int[][] table = createTable(left.size(), right.size());
        for(int i = 1; i <= left.size(); i++){
            for(int j = 1; j <= right.size(); j++){
                int slantCost = calculateSamePairCost(left.get(i - 1), right.get(j - 1));
                table[i][j] = calculateCost(table, i, j, slantCost);
            }
        }
        return similarity(table, left.size(), right.size());
    }

    private double similarity(int[][] table, int imax, int jmax){
        return 1d - (double)table[imax][jmax] / Math.max(imax, imax);
    }

    private int calculateCost(int[][] table, int i, int j, int slantCost){
        int d1 = table[i - 1][j    ] + 1;
        int d2 = table[i    ][j - 1] + 1;
        int d3 = table[i - 1][j - 1] + slantCost;
        return minimum(d1, d2, d3);
    }

    private int minimum(int d1, int d2, int d3){
        int min = Math.min(d1, d2);
        return Math.min(min, d3);
    }

    private int calculateSamePairCost(Element element1, Element element2){
        if(Objects.equals(element1, element2))
            return 0;
        return 1;
    }

    private int[][] createTable(int leftSize, int rightSize){
        int[][] table = new int[leftSize + 1][rightSize + 1];
        for(int i = 0; i <= leftSize; i++)
            table[i][0] = i;
        for(int j = 0; j <= rightSize; j++)
            table[0][j] = j;
        return table;
    }
}
