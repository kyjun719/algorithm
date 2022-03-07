package programmers.가장_큰_수;

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        for(int i : numbers) {
            list.add(String.valueOf(i));
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s);
        }
        return sb.charAt(0)=='0'?"0":sb.toString();
    }
}


class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{6,2,10}));
        System.out.println(new Solution().solution(new int[]{3,30,34}));
        System.out.println(new Solution().solution(new int[]{0, 0, 0}));
        System.out.println(new Solution().solution(new int[]{34, 343}));
    }
}