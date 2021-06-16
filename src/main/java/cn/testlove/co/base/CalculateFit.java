package cn.testlove.co.base;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:19
 * @Description: null
 */
@FunctionalInterface
public interface CalculateFit<T,S> {
    /**
     * 计算适应度的方法
     * @return
     */
    T calculateFit(S source);
}
