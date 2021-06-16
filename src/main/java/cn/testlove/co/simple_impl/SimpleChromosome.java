package cn.testlove.co.simple_impl;

import cn.testlove.co.base.CalculateFit;
import cn.testlove.co.base.Chromosome;
import cn.testlove.ga.Constants;

import java.util.Random;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/16 10:12
 * @Description: null
 */
public class SimpleChromosome implements Chromosome<String> {
    /**
     * 二进制存储的字符串
     */
    private String data;
    /**
     * 十进制大小
     */
    private int num;

    /**
     * 位数
     */
    private int size;
    /**
     * 适应度大小
     */
    private int fitNumber;

    private double fitRate;

    private CalculateFit calculateFit;


    public  SimpleChromosome(String data,CalculateFit calculateFit){
        this.calculateFit=calculateFit;
        setChromosome(data);
    }
    public  SimpleChromosome(CalculateFit calculateFit) {
        this.calculateFit = calculateFit;

    }
    /**
     * 重写toString方法,便于观察结果
     * @return
     */
    @Override
    public String toString() {
        return "[data:"+data+", num:"+num+",fitRate:"+fitRate+",fitNumber: "+fitNumber + "]";
    }

    public int getNum() {
        return num;
    }

    @Override
    public void mutation() {
        int i = new Random().nextInt(Constants.DIGITS);
        char[] chars = data.toCharArray();
        if(chars[i]=='0'){
            chars[i]='1';
        }else {
            chars[i] = '0';
        }
        setChromosome(String.valueOf(chars));

    }

    @Override
    public void setChromosome(String data) {

        int i = Integer.parseUnsignedInt(data, 2);
        this.data = data;
        this.num=i;
        this.size=Integer.toBinaryString(i).length();
        this.fitNumber= (int) calculateFit.calculateFit(num);

    }

    public int getFitNumber() {
        return fitNumber;
    }

    public double getFitRate() {
        return fitRate;
    }

    public String getData(){
        return this.data;
    }

    public void setFitRate(double fitRate) {
        this.fitRate = fitRate;
    }
}
