import edu.princeton.cs.algs4.*;

import java.util.Arrays;

public class TriplicateNames {
    public static void main(String[] args) {

        int N; //number of names per list

//Sample name lists
        String nameList_1[] = new String[]{"Angie", "Carl", "Beth", "Jack"};
        String nameList_2[] = new String[]{"Carl", "Steven", "Angie", "Alf"};
        String nameList_3[] = new String[]{"Angie", "Carl", "Beth", "Alf"};
        String nameList_4[] = new String[]{"Carl", "Angie", "Beth", "Alf"};

        Merge.sort(nameList_1);
        Merge.sort(nameList_2);
        Merge.sort(nameList_3);
        Merge.sort(nameList_4);

        String firstTriplicate_1 = "";
        int count;
        int[] indexList = new int[]{0,0,0};

        for(int i = 0; i < nameList_1.length ; i++){
            count = 0;

        //Binary search will return -1 if not found and the element's index if the element is found
        //I add these indexes to a list
            indexList[0] = Arrays.binarySearch(nameList_2, nameList_1[i]);
            indexList[1] = Arrays.binarySearch(nameList_3, nameList_1[i]);
            indexList[2] = Arrays.binarySearch(nameList_4, nameList_1[i]);

            for(int j = 0; j < indexList.length; j++){
                if(indexList[j]>=0) count++;
            }
        //If 2 or more of the indexes are not -1, we have 3 matching names
            if(count >= 2){
                firstTriplicate_1 = nameList_1[i];
                break;
        //We were only asked to return the first name that appears in 3 lists. Therefore we break
            }
        }

        //But we need to remember that namelist_2, 3 and 4 may contain the same name, which is not in namelist_1
        //Therefore we need to repeat the above for loop for one of the other list:

        String firstTriplicate_2 = "";

        for(int i = 0; i < nameList_2.length ; i++){
            count = 0;
            indexList[0] = Arrays.binarySearch(nameList_1, nameList_2[i]);
            indexList[1] = Arrays.binarySearch(nameList_3, nameList_2[i]);
            indexList[2]= Arrays.binarySearch(nameList_4, nameList_2[i]);

            for(int j = 0; j < indexList.length; j++){
                if(indexList[j]>=0) {
                    count++;
                }
            }
            if(count >= 2){
                firstTriplicate_2 = nameList_2[i];
                break;
            }
        }
//If only one of the for loops found a nameTriplicate we print out that name, if none of the for loops found a nameTriplicate we print an empty string
        if(firstTriplicate_1 == "" || firstTriplicate_2 ==""){
            System.out.println(firstTriplicate_1 + firstTriplicate_2);
        }
//If both for loops found a nameTriplicate, we print the lexicographically first such
        if(firstTriplicate_1.compareTo(firstTriplicate_2)<0) {
            System.out.println(firstTriplicate_1);
        }
        else
        {
            System.out.println(firstTriplicate_2);
        }
    }
}
