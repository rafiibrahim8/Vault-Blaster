package me.ibrahimrafi.vaultblasterriant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Decoder {
    ArrayList<KVP> kvps;
    private String lastPasswordId;
    private int lassPassword;
    public Decoder(){
        kvps = new ArrayList<>();
        lassPassword = 0;
        lastPasswordId = "";
    }

    public boolean decodeAndSave(String fileName, String currentPath, String passwordId,String fileExt, String whereSave){
        int key  = -1;
        if(!currentPath.endsWith(".bin")){
            currentPath = currentPath.substring(0,currentPath.lastIndexOf("."))+".bin";
        }
        if(passwordId.equalsIgnoreCase(lastPasswordId)){
            key = lassPassword;
        } else {
            for(int i=0;i<kvps.size();i++){
                KVP kvp = (KVP) kvps.get(i);
                if(kvp.key.equalsIgnoreCase(passwordId)){
                    key = kvp.value;
                    lassPassword = key;
                    lastPasswordId = passwordId;
                    break;
                }
            }
        }
        if(key<0){
            byte[] bytes = new byte[12];
            try {
                FileInputStream fileInputStream = new FileInputStream(new File(currentPath));
                fileInputStream.read(bytes);
                fileInputStream.close();
                String[] extKey = KeyFinder.find(bytes,fileExt);
                if(extKey[0].endsWith(fileExt)){
                    key = Integer.parseInt(extKey[1]);
                    lassPassword = key;
                    lastPasswordId = passwordId;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(key<0)
            return false;
        try {
            return decodeAndSaveImpl(currentPath,key,fileName,whereSave);
        } catch (IOException e) {
            return false;
        }
    }

    private boolean decodeAndSaveImpl(String currentPath, int key,String fileName,String parentFolder) throws IOException {
        File saveFile = new File(parentFolder,fileName);
        for(int i=0;saveFile.exists();i++){
            saveFile = new File(parentFolder,"("+i+") "+fileName);
        }
        FileInputStream fileInputStream = new FileInputStream(new File(currentPath));
        byte[] bytes = new byte[128];
        fileInputStream.read(bytes);
        for(int i=0;i<bytes.length;i++){
            bytes[i] = (byte)(bytes[i]^key);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        fileOutputStream.write(bytes);
        while (fileInputStream.available()>262144){
            byte[] b256kB = new byte[262144];
            fileInputStream.read(b256kB);
            fileOutputStream.write(b256kB);
        }
        byte[] bXkB = new byte[fileInputStream.available()];
        fileInputStream.read(bXkB);
        fileOutputStream.write(bXkB);
        fileInputStream.close();
        fileOutputStream.close();

        return true;
    }

    class KVP{
        String key;
        int value;
        public KVP(String key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
