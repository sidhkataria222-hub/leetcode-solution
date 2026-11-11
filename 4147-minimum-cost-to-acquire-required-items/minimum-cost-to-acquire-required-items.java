//[Jitni common requirement hai, utne Type 3 items lo.
//mn=\min(need1,need2)
//Remaining requirements individual items se complete karo.
//c=mn\times costBoth
//+(need1-mn)\times cost1
//+(need2-mn)\times cost2]


class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth,
                            int need1, int need2) {

        long a = (long) need1 * cost1
               + (long) need2 * cost2;

        long b = (long) costBoth * Math.max(need1, need2);

        int mn = Math.min(need1, need2);

        long c = (long) mn * costBoth
               + (long) (need1 - mn) * cost1
               + (long) (need2 - mn) * cost2;

        return Math.min(a, Math.min(b, c));
    }
}
