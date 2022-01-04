import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FolderSubFolder {

        public List<String> removeSubfolders(String[] folder) {
             if ((folder == null) ||(folder.length == 1)) {
                 return null;
             }
             List<String> sortedList = Arrays.asList(folder);
             Collections.sort(sortedList);
             List<String> uniqueList = new ArrayList<>();
             int size = folder.length;
             String currentCompare = sortedList.get(0);
            uniqueList.add(currentCompare);
            for(int i = 1; i < size;i++) {
                 String latest = sortedList.get(i);
                 //if (!latest.startsWith(currentCompare)) {
                if (!isSubFolder(currentCompare,latest)) {
                     uniqueList.add(latest);
                     currentCompare = latest;
                 }
             }
             return uniqueList;
        }

        private boolean isSubFolder(String s1,String s2) {
            String[] sa1 = s1.split("/");
            String[] sa2 = s2.split("/");
            if (sa2.length < sa1.length) return false;
            for(int i = 0; i < sa1.length;i++) {
                if (!sa1[i].equals(sa2[i])) return false;
            }
            return true;
        }




        public static void main(String[] args) {
            String[] f1 = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
            String[] f2 = {"/a","/a/b/c","/a/b/d"};
            String[] f3 = {"/a/b/c","/a/b/ca","/a/b/d"};
            FolderSubFolder fs = new FolderSubFolder();
            fs.removeSubfolders(f1).forEach(value->System.out.print(value+" "));
            System.out.println();
            fs.removeSubfolders(f2).forEach(value->System.out.print(value+" "));
            System.out.println();
            fs.removeSubfolders(f3).forEach(value->System.out.print(value+" "));
            System.out.println();
        }
    }

