package googleAss;
import java.util.*;
public class SolveEquations {

        Map<String,Double> eMap = new HashMap<>();
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String,Double> S = solve(equations,values);
            int l = queries.size();
            double[] D = new double[l];
            Arrays.fill(D,-1.0);
            for (int i = 0; i < l; i++) {
                String n = queries.get(i).get(0);
                String d = queries.get(i).get(1);
                if (eMap.containsKey(n+d)) {
                    D[i] = eMap.get(n+d);
                    continue;
                } else if (eMap.containsKey(d+n)) {
                    D[i] = eMap.get(d+n);
                    continue;
                }
                Double dn = S.get(n);
                Double dd = S.get(d);
                if ( (dn == null) || (dd == null) ) continue;
                D[i] = dn/dd;
            }


            return D;

        }// end of main method.

        private Map<String,Double> solve(List<List<String>> equations,double[] values) {
            Map<String,Double> map = new HashMap<>();
            int l = equations.size();
            Map<String,Set<Integer>> mapa = new HashMap<>();
            int[] sol = new int[equations.size()];

            //Set<String> unsol = new HashSet<>();
            for(int i = 0; i < l ; i++) {
                String n = equations.get(i).get(0);
                String d = equations.get(i).get(1);
                eMap.put(n+d,values[i]); eMap.put(d+n,1/values[i]);
                Set<Integer> s1 = mapa.getOrDefault(n,new HashSet<>());
                s1.add(i); mapa.put(n,s1);
                Set<Integer> s2 = mapa.getOrDefault(d,new HashSet<>());
                s2.add(i);mapa.put(d,s2);
            }
            String n = equations.get(0).get(0);
            String d = equations.get(0).get(1);
            map.put(n,values[0]);
            map.put(d,1.0); sol[0] = 1;
            Stack<Integer> Q = new Stack<>();
            for(int i = l-1;i >= 0;i--) Q.push(i);
            Set<Integer> s1 = mapa.get(n);
            Set<Integer> s2 = mapa.get(d);
            for(Integer ii : s1) Q.push(ii);
            for(Integer ii : s2) Q.push(ii);
            while(!Q.empty()){
                int i = Q.pop();
                if (sol[i] == 1) {
                    continue;
                }
                List<String> ll = equations.get(i);
                n = ll.get(0);
                d = ll.get(1);
                Double nd = map.get(n);
                Double dd = map.get(d);
                if ( (nd == null) && (dd == null) ) {
                    //map.put(n,values[i]);
                    //map.put(d,1.0);

                    continue;
                } else if ( nd == null) {
                    map.put(n,values[i]*dd);
                } else if ( dd == null) {
                    map.put(d,nd/values[i]);
                }
                s1 = mapa.get(n);
                s2 = mapa.get(d);
                sol[i] = 1;
                for(Integer ii : s1) { if (sol[ii] != 1) Q.push(ii);}
                for(Integer ii : s2) { if (sol[ii] != 1) Q.push(ii);}
            }

            return map;
        }



}
