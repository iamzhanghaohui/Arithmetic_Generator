package com.free.pojo;

import com.free.util.GenerateUtils;
import lombok.Data;

/**
 * @ClassNameFraction
 * @Description 计算数
 * @Author Free
 * @Date2020/10/9 20:22
 * @Version V1.0
 **/
@Data
public class Fraction {
    /**
     * 分子
     */
    private int up;
    /**
     * 分母，不能为0，默认为1
     */
    private int down = 1;
    //设置分子和分母
    public Fraction(int a, int b) {
        setFraction(a, b);
    }
    //看当前分数是否为负数
    boolean isNegative() {
        //结果默认是正数
        boolean isNagitiveAB = false;
        if (up * down < 0) {
            isNagitiveAB = true;
        }
        return isNagitiveAB;
    }
    /**
     * 设置分子和分母
     * @param up
     * @param down
     */
    public void setFraction(int up,int down){
        if (down == 0){
            throw new RuntimeException("分母不能为0");
        }
        //结果默认是正数
        int isNagitive = 1;
        //调整符号，分母只能为正数
        if (up * down < 0) {
            isNagitive = -1;
        }
        up = Math.abs(up);
        down = Math.abs(down);
        //最大公因数
        int greatest_Common_Factor = divisionAlgorithm(up, down);
        //化简
        this.up = up * isNagitive / greatest_Common_Factor;
        this.down = down / greatest_Common_Factor;
    }
    /**
     * 求最大公因数，辗转相除法
     * @param a
     * @param b
     * @return
     */
    private int  divisionAlgorithm(int a, int b) {
        int big = a;
        if (big == 0){
            return 1;
        }
        int small = b;
        //让a成为最大的
        if (a < b) {
            big = b;
            small = a;
        }
        int mod = big % small;
        return mod == 0 ? small : divisionAlgorithm(small, mod);
    }


    /**
     * 功能描述: 通过表达式得到分子和分母,都未经过简化 "1'21/22"
     * @Param: [result]
     * @Return:
     * @Author: Free
     * @Date: 2020/10/9 21:19
     */
    public Fraction(String result) {
        result.trim();
        int up_index = result.indexOf("/");
        int a1_index = result.indexOf("'");

        //不是分式的时候
        if (up_index == -1) {
            up = Integer.valueOf(result);
        }
        //是分式的时候
        else {
            //分母
            down = Integer.valueOf(result.substring(up_index + 1));
            //真分数
            if (a1_index == -1) {
                up = Integer.valueOf(result.substring(0, up_index));
            }
            //带分数
            else {
                int a1 = Integer.valueOf(result.substring(0, a1_index));
                int a0 = Integer.valueOf(result.substring(a1_index + 1, up_index));
                up = a1 * down + a0;
            }
        }
        setFraction(up, down);
    }

   /**
    * 功能描述: 随机生成一个用来运算的分数（多种形式）
    *
    * @Param: []
    * @Return: com.free.pojo.Fraction
    * @Author: Free
    * @Date: 2020/10/9 21:16
    */
    public static Fraction generateFraction() {
        //a.b 都是大于等于0的
        int up = GenerateUtils.getRandomInRange(Expression.range);
        int down = GenerateUtils.getRandomInRange(Expression.range);
        //分母为0
        while (down == 0) {
            down = GenerateUtils.getRandomInRange(Expression.range);
        }
        Fraction result = new Fraction(up,down);
        return result;
    }



    /**
     * 功能描述: <br>加法
     * 〈〉
     * @Param: [right]
     * @Return: com.free.pojo.Fraction
     * @Author: Free
     * @Date: 2020/10/9 21:22
     */
    public Fraction add(Fraction right) {
        // a/b+c/d =（ad+bc）/bd
        return new Fraction(
                this.up * right.down + this.down * right.up,
                this.down * right.down
        );
    }

    /**
     * 功能描述: <br>
     * 〈〉
     * @Param: [right]减法
     * @Return: com.free.pojo.Fraction
     * @Author: Free
     * @Date: 2020/10/9 21:22
     */
    public Fraction subtract(Fraction right) {
        // a/b-c/d =（ad-bc）/bd
        return new Fraction(
                this.up * right.down - this.down * right.up,
                this.down * right.down
        );
    }

    /**
     * 功能描述: <br>乘法
     * 〈〉
     * @Param: [right]
     * @Return: com.free.pojo.Fraction
     * @Author: Free
     * @Date: 2020/10/9 21:22
     */
    public Fraction multiply(Fraction right) {
        // a/b * c/d = ac / bd
        return new Fraction(
                this.up * right.up,
                this.down * right.down
        );
    }

    /**
     * 功能描述: <br>除法
     * 〈〉
     * @Param: [right]
     * @Return: com.free.pojo.Fraction
     * @Author: Free
     * @Date: 2020/10/9 21:22
     */
    public Fraction divide(Fraction right) {
        // a/b  /  c/d = ad / bc
        return new Fraction(
                this.up * right.down,
                this.down * right.up
        );
    }

    //将a,b转化为表达式
    @Override
    public String toString() {
        //不是分式
        if (down == 1)
            return String.valueOf(up);
            //真分式
        else {
            int i = up / down;
            //余数
            int j = up % down;
            if (i != 0) {
                return String.format("%d'%d/%d", i, j, down);
            } else {
                return String.format("%d/%d", up, down);
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (up != fraction.up) return false;
        return down == fraction.down;
    }

    //根据分子和分母
    @Override
    public int hashCode() {
        int result = 31 * up + down;
        return result;
    }
}
