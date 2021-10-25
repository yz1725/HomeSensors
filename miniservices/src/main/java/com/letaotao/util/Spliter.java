package com.letaotao.util;

import java.io.*;
import java.util.Arrays;

public class Spliter {

        public static void main(String args[])
        {
            try{
                // Reading file and getting no. of files to be generated
                String inputfile = "/Users/hongmeiyuan/Downloads/btc_balance_sorted.csv"; //  Source File Name.
                double nol = 11000000.0; //  No. of lines to be split and saved in each output file.
//                File file = new File(inputfile);
//                Scanner scanner = new Scanner(file);
//                int count = 0;
//                while (scanner.hasNextLine())
//                {
//                    scanner.nextLine();
//                    count++;
//                    if (count % 1000000 == 0){
//                        System.out.println("we are handling "+count);
//                    }
//                }
//                System.out.println("Lines in the file: " + count);     // Displays no. of lines in the input file.

//                double temp = (count/nol);
                double temp = 1;
                int temp1=(int)temp;
                int nof=3;
//                if(temp1==temp)
//                {
//                    nof=temp1;
//                }
//                else
//                {
//                    nof=temp1+1;
//                }
                System.out.println("No. of files to be generated :"+nof); // Displays no. of files to be generated.

                //---------------------------------------------------------------------------------------------------------

                // Actual splitting of file into smaller files

                FileInputStream fstream = new FileInputStream(inputfile);
                DataInputStream in = new DataInputStream(fstream);

                BufferedReader br = new BufferedReader(new InputStreamReader(in)); String strLine;

                String[] result = new String[31049107];
                int index=0;
                for(index=0; index<31049107; index++)
                {
                    strLine = br.readLine();
                    if (strLine!= null) {
                        String[] ss = strLine.split(",");
                        result[index]=ss[0];
                    }
                    if(strLine == null || strLine.length()<15){
                        System.out.println("the value looks wrong: "+strLine);
                    }
                }
                System.out.println(result[0]);
                System.out.println(result[31049106]);
                in.close();
                fstream.close();
                System.out.println("Collected all data. Sorting now ......");
                Arrays.sort(result, (a, b) -> a.compareTo(b));
                System.out.println("Collected all data. Finished sorting..... ");
                System.out.println(result[31049106]);
                index = 0;
                for (int j=1;j<=nof;j++)
                {
                    String name = "/Users/hongmeiyuan/Downloads/btcAddress/file_"+j+".txt";
                    FileWriter fstream1 = new FileWriter(name);     // Destination File Location
                    BufferedWriter out = new BufferedWriter(fstream1);
                    for (int i=1;i<=nol;i++)
                    {
                        if(i==1){
                            System.out.println(result[index]);
                        }
                        out.write(result[index++]);
                        if(i!=nol)
                            out.newLine();
//                        strLine = br.readLine();
//                        if(i==1){
//                            System.out.println(strLine);
//                        }
//                        if (strLine!= null)
//                        {
//                            String[] ss = strLine.split(",");
//                            out.write(ss[0]);
//                            result[index++]=ss[0];
//                            if(i!=nol)
//                            {
//                                out.newLine();
//                            }
//                        }
                    }
                    out.close();
                    fstream1.close();
                }


            }catch (Exception e)
            {
                System.err.println("Error: " + e.getMessage());
            }

    }
}
