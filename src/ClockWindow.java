/*
1344. Angle Between Hands of a Clock

    User Accepted: 1734
    User Tried: 1805
    Total Accepted: 1774
    Total Submissions: 2974
    Difficulty: Medium

Given two numbers, hour and minutes. Return the smaller angle (in sexagesimal units) formed between the hour and the minute hand.



Example 1:

Input: hour = 12, minutes = 30
Output: 165

Example 2:

Input: hour = 3, minutes = 30
Output: 75

Example 3:

Input: hour = 3, minutes = 15
Output: 7.5

Example 4:

Input: hour = 4, minutes = 50
Output: 155

Example 5:

Input: hour = 12, minutes = 0
Output: 0



Constraints:

    1 <= hour <= 12
    0 <= minutes <= 59
    Answers within 10^-5 of the actual value will be accepted as correct.

Java

 */
public class ClockWindow {

    public double angleClock(int hour, int minutes) {

        double hAngle = 30*hour + 30.0*(minutes/60.0);
        if (hAngle >= 360) {
            hAngle = hAngle - 360;
        }
        double mAngle = 6.0*minutes;
        double delta = Math.abs(hAngle-mAngle);
        return Math.min(delta,(360.0-delta));
    }

    public static void main(String[] args) {
        ClockWindow cw = new ClockWindow();
        System.out.println(" Delta is "+cw.angleClock(3,15));
    }
}
