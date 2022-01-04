public class RateLimiter {

    /*
          n = number of request allowed.
          time = time in ms.
          long[] --> Array of times
     */
    public void rateLimiter(int n,long time,long[] timeOfReq) {
        long[] timeArray = new long[n];
        int initialCount = 0;
        int index = 0;
        for(long methodTime : timeOfReq) {
            if ((initialCount < n) || (methodTime - timeArray[index%n]) > time) {//Can do
                timeArray[index%n] = methodTime;
                System.out.println(" "+methodTime+" ");
                index++;
                if (index%n == 0) index = 0;
            } else {
                System.out.println("Discard  "+methodTime+" ");
            }
            initialCount++;
        }

    }

    public static void main(String[] args) {
        long[] tArray = {100, 499,701,702,703};
        RateLimiter rt = new RateLimiter();
        rt.rateLimiter(2,500,tArray);
    }
}
