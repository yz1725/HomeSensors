package com.letaotao.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AddressVerifier {

    private static HashSet<String> addresses = new HashSet<>();

    static {
        loadAddress();
    }

    public static boolean verifyAddress(String str){
        if (str==null || str.length()<5){
            return false;
        }
        return addresses.contains(str);
    }

    public static void loadAddresses(String path){
        if (addresses.size()>0){
            return;
        }
        try{
            FileInputStream inputStream = new FileInputStream(path);;
            Scanner sc = new Scanner(inputStream, "UTF-8");;
            int readindex=0;
            while (sc.hasNextLine()) {
                String strLine = sc.nextLine();
                addresses.add(strLine);
                if(readindex %1000000 ==0){
                    System.out.println("now we read lines: "+readindex+" address:("+strLine+")");
                }
                readindex++;
            }
            System.out.println("total #address: "+addresses.size());
            sc.close();
            inputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static void loadAddresses(){
        try{
            InputStream fstream = AddressVerifier.class.getResourceAsStream("../../../file_1.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine=br.readLine();
            int readindex=0;
            while(strLine !=null){
                addresses.add(strLine);
                strLine = br.readLine();
                if(readindex %1000000 ==0){
                    System.out.println("now we read lines: "+readindex+" address:("+strLine+")");
                }
                readindex++;
            }
            System.out.println("total #address: "+addresses.size());
            br.close();
            in.close();
            fstream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static Set<String> loadAddress(){
        if (addresses.size()==0){
            loadAddresses();
        }
        return addresses;
    }

    public static int getAddressesInfo(){
        return addresses.size();
    }
}
