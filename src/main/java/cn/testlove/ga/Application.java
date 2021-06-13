package cn.testlove.ga;


/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/9 9:59
 * @Description: null
 */
public class Application {

    public static void main(String[] args) {
        Population population = new Population();
        population.init(4);
        for (int i = 0; i < 1000;i++){
            population.evolution(i);
        }

//        population.individuals.sort(null);
//        System.out.println(population.individuals.get(3));
    }



}
