import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public  class Main {
    public  static void main(String[] args)
    {

        byte[] arr = {1, 2, 3,4};
        String str = "";
        StringBuffer strBuffer = new StringBuffer(str);

        for (int i = 0; i < arr.length; i++) {

            strBuffer.append(arr[i] + " ");

        }
        System.out.println(strBuffer.toString());





        System.out.println(strBuffer.toString().substring(0, strBuffer.toString().length() - 1));
//        System.out.println(result.length());
    }
}
