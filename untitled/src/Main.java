import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        byte[] arr = {0,1,1,1,1,0,1,1};
        System.out.println("oldArr");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

        String s = "";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i] + " ";
        }
        String newS = "";
        for (int i = 0; i < s.length() -1; i++) {
            newS += s.charAt(i);
        }
        System.out.println();
        System.out.println("newS");
        System.out.println(newS);

        List<String> stringArrayList = Arrays.asList(
                newS.split(" "));
        byte[] byteArray = new byte[stringArrayList.size()];
        for (int i = 0; i < stringArrayList.size(); i++) {
            try {
                byteArray[i] = Byte.valueOf(stringArrayList.get(i));
            }catch (Exception e){

                System.out.println("Erorr");
            }
        }
        System.out.println("newS");
        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i]);
        }

    }
}
