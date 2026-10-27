class Solution {
    public int countDaysTogether(String arriveAlice,
                                 String leaveAlice,
                                 String arriveBob,
                                 String leaveBob) {

        int start = Math.max(
            convert(arriveAlice),
            convert(arriveBob)
        );

        int end = Math.min(
            convert(leaveAlice),
            convert(leaveBob)
        );

        return Math.max(0, end - start + 1);
    }

    private int convert(String date) {
        int[] days = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int total = day;

        for (int i = 0; i < month - 1; i++) {
            total += days[i];
        }

        return total;
    }
}