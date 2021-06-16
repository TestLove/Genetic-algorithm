package cn.testlove.co;

import cn.testlove.co.base.CalculateFit;
import cn.testlove.co.simple_impl.SimplePopulation;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:39
 * @Description: null
 */
public class Application {
    public static void main(String[] args) {
        SimplePopulation simplePopulation = new SimplePopulation();
        simplePopulation.setCalculator((CalculateFit<Integer, Integer>) source -> source*source);
        simplePopulation.init(3);
        for (int i = 0; i < 1000;i++){
            simplePopulation.evolution(i);
        }
    }
}
