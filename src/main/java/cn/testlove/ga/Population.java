package cn.testlove.ga;

import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/9 21:53
 * @Description: null
 */
public class Population {
    public static Gene maxGene = new Gene("0");
    ArrayList<Gene> individuals = new ArrayList<>();
    ArrayList<Gene> newIndividuals = new ArrayList<>();
    public void  init(int nums){
        Random random = new Random();
        for (int i = 0; i < nums; i++) {
            String s = Integer.toBinaryString(random.nextInt(Constants.MAX));
            int diff = Constants.DIGITS-s.length();
            while (diff>0){
                s = "0"+s;
                diff--;
            }
            individuals.add(new Gene(s));



        }
        calculateFitRate();

    }
    private   void select() {
        for (int i = 0; i < individuals.size(); i++){
            newIndividuals.add(new Gene(nextIndividual()));
        }
        individuals = new ArrayList<Gene>(newIndividuals);
        newIndividuals.clear();

    }
    private void cross(){
        int size = Constants.DIGITS;
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        Gene gene1 = individuals.get(0);
        Gene gene2 = individuals.get(1);
        String gene1Data = gene1.getData();
        String gene2Data = gene2.getData();
        String substring1 = gene1Data.substring(min, max);
        String substring2 = gene2Data.substring(min, max);
        gene1.setData(gene1Data.substring(0,min)+substring2+gene1Data.substring(max));
        gene2.setData(gene2Data.substring(0,min)+substring1+gene2Data.substring(max));


    }
    public void evolution(int i){
        System.out.println("==============================开始进行"+i+"次迭代==============================");

        System.out.println("此时种群为: ");
        individuals.forEach(System.out::println);
        select();
        cross();
        mutation();
        System.out.println("此时种群为: ");
        individuals.forEach(System.out::println);
        individuals.sort(null);
        if(maxGene.getNum()<individuals.get(individuals.size() - 1).getNum()){
            maxGene.setData(individuals.get(individuals.size() - 1).getData());
        }
        System.out.println("当前最大值为: "+individuals.get(individuals.size() - 1));
        System.out.println("全局最大值为: "+maxGene);
        System.out.println("==============================结束第"+i+"次迭代==============================");

    }

    /**
     * 种群决定哪个变异
     */
    private void mutation(){
        for (int i = 0; i < individuals.size(); i++){
            int i1 = new Random().nextInt(100);
            if (i1>90){
                individuals.get(i).mutation();
                System.out.println(individuals.get(i).toString());
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
        double sum = individuals.stream().mapToInt(Gene::getNum).sum();
        individuals.forEach((o)-> o.setFitRate(o.getNum()/sum));
    }
}
