package cn.testlove.co.base;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:26
 * @Description: null
 */
public interface Population {
    /**
     * 种群进行演化的方法
     */
    void evolution();

    /**
     *
     * @param i 种群规模
     */
    void init(int i);
}
