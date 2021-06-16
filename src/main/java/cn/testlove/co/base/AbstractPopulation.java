package cn.testlove.co.base;

import cn.testlove.co.base.Population;

import java.util.ArrayList;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:31
 * @Description: null
 */
public  abstract class AbstractPopulation<T> implements Population {
    private CalculateFit calculator;
    protected ArrayList<T> individuals = new ArrayList<>();
    protected ArrayList<T> newIndividuals = new ArrayList<>();
    protected T maxIndividual;
    @Override
    public void evolution() {

    }
    @Override
    public void init(int i){}

    public void setCalculator(CalculateFit newCalculator) {
        calculator=newCalculator;

    }
    public CalculateFit getCalculator() {
        return calculator;
    }
}
