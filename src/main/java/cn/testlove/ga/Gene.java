package cn.testlove.ga;

import java.util.Random;

/**
 * @author TestLove
 * @version 1.0
 * @date 2021/6/9 10:01
 * @Description: 基因实体类
 */
public class Gene implements Comparable<Gene>{
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


    public Gene(String data){
        setData(data);
    }
    public Gene() {

    }
    /**
     * 这里实现适应度函数比较
     * @param o
     * @return
     */
    @Override
    public int compareTo(Gene o) {
        if(num>o.getNum()){
            return 1;
        }
        if(num<o.getNum()){
            return -1;
        }
        return 0;
    }
    /**
     * 重写toString方法,便于观察结果
     * @return
     */
    @Override
    public String toString() {
        return "[data:"+data+", num:"+num+",fitRate:"+fitRate+",fitNumber: "+fitNumber + "]";
    }

    /**
     * 确定个体哪一个位变异
     */
    public void mutation(){
        int i = new Random().nextInt(Constants.DIGITS);
        char[] chars = data.toCharArray();
        if(chars[i]=='0'){
            chars[i]='1';
        }else {
            chars[i] = '0';
        }
        setData(String.valueOf(chars));


    }

    /**
     * 计算适应度函数值
     * @return
     */
    private int fit(){
        return num;
    }

    public void setData(String data) {
        this.data = data;
        int i = Integer.parseUnsignedInt(data, 2);
        num=i;
        size=Integer.toBinaryString(i).length();
        fitNumber=fit();

    }
    public String getData() {
        return data;
    }

    public int getNum() {
        return num;
    }

    public int getSize() {
        return size;
    }

    public int getFitNumber() {
        return fitNumber;
    }
    public double getFitRate() {
        return fitRate;
    }

    public void setFitRate(double fitRate) {
        this.fitRate = fitRate;
    }

}
