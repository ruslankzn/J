package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

//    public boolean equals(Solution n) {
//        return n.first.equals(first) && n.last.equals(last);
//    }

//

//Метод equals должен проверять является ли переданный объект объектом класса Solution
@Override
public boolean equals(Object o)
{
    if (o == null)
        return false;
    if (!(o instanceof Solution)){
        return false;
    }
    if (this == o) return true;
    Solution solution = (Solution) o;
    if (first != null ? !first.equals(solution.first) : solution.first != null) return false;
    if (last != null ? !last.equals(solution.last) : solution.last != null) return false;
    return true;
}

    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
//        return 31 * first.hashCode() + last.hashCode();
        return result;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}