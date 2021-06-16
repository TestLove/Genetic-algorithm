package cn.testlove.co.base;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:06
 * @Description: 规定了一个染色体有哪些行为
 */
public interface Chromosome<T> {
    /**
     * 变异
     */
    void mutation();

    /**
     * 设置染色体信息
     * @param data
     */
    void setChromosome(T data);
}
