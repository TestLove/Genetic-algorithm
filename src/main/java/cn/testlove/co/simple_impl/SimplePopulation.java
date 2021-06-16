package cn.testlove.co.simple_impl;

import cn.testlove.co.base.AbstractPopulation;
import cn.testlove.co.base.CalculateFit;
import cn.testlove.co.simple_impl.SimpleChromosome;
import cn.testlove.ga.Constants;
import cn.testlove.ga.Gene;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:30
 * @Description: null
 */
public class SimplePopulation extends AbstractPopulation<SimpleChromosome> {
    @Override
    public  void setCalculator(CalculateFit newCalculator){
        super.setCalculator(newCalculator);
    }
    public void evolution(int i){
        System.out.println("==============================开始进行"+i+"次迭代==============================");
        evolution();
        System.out.println("==============================结束第"+i+"次迭代==============================");

    };
    @Override
    public void init(int nums){
        this.maxIndividual =new SimpleChromosome("0",getCalculator());
        this.individuals = new ArrayList<SimpleChromosome>();
        this.newIndividuals = new ArrayList<SimpleChromosome>();
        Random random = new Random();
        for (int i = 0; i < nums; i++) {
            String s = Integer.toBinaryString(random.nextInt(cn.testlove.ga.Constants.MAX));
            int diff = Constants.DIGITS-s.length();
            while (diff>0){
                s = "0"+s;
                diff--;
            }
            individuals.add(new SimpleChromosome(s,getCalculator()));
        }
        calculateFitRate();
    }
    @Override
    public void evolution() {
        System.out.println("此时种群为: ");
        individuals.forEach(System.out::println);
        select();
        cross();
        mutation();
        System.out.println("此时种群为: ");
        individuals.forEach(System.out::println);
        individuals.sort(Comparator.comparingInt(SimpleChromosome::getFitNumber));
        if(maxIndividual.getNum()<individuals.get(individuals.size() - 1).getNum()){
            maxIndividual.setChromosome(individuals.get(individuals.size() - 1).getData());
        }
        System.out.println("当前最大值为: "+individuals.get(individuals.size() - 1));
        System.out.println("全局最大值为: "+maxIndividual);
    }
     private void select() {
        for (int i = 0; i < individuals.size(); i++){
            newIndividuals.add(new SimpleChromosome(nextIndividual(),getCalculator()));
        }
        individuals = new ArrayList<>(newIndividuals);
        newIndividuals.clear();
    }
     private void cross() {
        int size = Constants.DIGITS;
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        SimpleChromosome gene1 = individuals.get(0);
        SimpleChromosome gene2 = individuals.get(1);
        String gene1Data = gene1.getData();
        String gene2Data = gene2.getData();
        String substring1 = gene1Data.substring(min, max);
        String substring2 = gene2Data.substring(min, max);
        gene1.setChromosome(gene1Data.substring(0,min)+substring2+gene1Data.substring(max));
        gene2.setChromosome(gene2Data.substring(0,min)+substring1+gene2Data.substring(max));

    }

    private void mutation() {
        for (SimpleChromosome individual : individuals) {
            int i1 = new Random().nextInt(100);
            if (i1 > 90) {
                individual.mutation();
                System.out.println(individual.toString());
            }


        }
        calculateFitRate();

    }
    private String nextIndividual(){
        double sum = 0.0;
        for (int i = 0; i < individuals.size(); i++) {
            sum += individuals.get(i).getFitRate();
        }
        double r = Math.random()  * sum;
        sum = 0.0;
        for (int i = 0; i < individuals.size(); i++) {
            sum += individuals.get(i).getFitRate();
            if (sum > r){
                return individuals.get(i).getData();
            }

        }


        return individuals.get(individuals.size() - 1).getData();
    }

    /**
     * 计算适应概率
     */
    private void calculateFitRate(){
        double sum = individuals.stream().mapToInt(SimpleChromosome::getNum).sum();
        individuals.forEach((o)-> o.setFitRate(o.getNum()/sum));
    }
}
