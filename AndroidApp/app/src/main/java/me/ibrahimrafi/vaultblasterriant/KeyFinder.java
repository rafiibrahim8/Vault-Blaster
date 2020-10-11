package me.ibrahimrafi.vaultblasterriant;

public class KeyFinder {
    public static String[] find(byte[] encBytes, String findExt) {
        if(! findExt.startsWith(".")){
            findExt = "."+findExt;
        }
        int tempKey = -1, lf = 0, last_lf=0, key=-1;
        int i, j;
        String tExt = "NFND";
        String ext = "";
        int[] dFA = new int[12];
        for (i = 0; i < 256; i++) {
            for (j = 0; j < 12; j++) {
                dFA[j] = (encBytes[j] & 0xFF) ^ i;
            }

            if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && (dFA[3] == 0x18 || dFA[3] == 0x14 || dFA[3] == 0x1c) && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x33 && dFA[9] == 0x67 && dFA[10] == 0x70 && dFA[11] == 0x35) {
                tExt = ".mp4";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && (dFA[3] == 0x18 || dFA[3] == 0x14 || dFA[3] == 0x1c) && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x4d && dFA[9] == 0x53 && dFA[10] == 0x4e && dFA[11] == 0x56) {
                tExt = ".mp4";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && (dFA[3] == 0x18 || dFA[3] == 0x14 || dFA[3] == 0x1c) && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x69 && dFA[9] == 0x73 && dFA[10] == 0x6f && dFA[11] == 0x6d) {
                tExt = ".mp4";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && dFA[3] == 0x1c && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x33 && dFA[9] == 0x67 && dFA[10] == 0x32 && dFA[11] == 0x61) {
                tExt = ".3g2";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && dFA[3] == 0x14 && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x71 && dFA[9] == 0x74 && dFA[10] == 0x20 && dFA[11] == 0x20) {
                tExt = ".mov";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && dFA[3] == 0x20 && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x4d && dFA[9] == 0x34 && dFA[10] == 0x56 && dFA[11] == 0x20) {
                tExt = ".m4v";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && dFA[3] == 0x18 && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x6d && dFA[9] == 0x70 && dFA[10] == 0x34 && dFA[11] == 0x32) {
                tExt = ".mp4";
                lf = 12;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && dFA[3] == 0x14 && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70 && dFA[8] == 0x33 && dFA[9] == 0x67 && dFA[10] == 0x70) {
                tExt = ".3gp";
                lf = 11;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x00 && dFA[3] == 0x18 && dFA[4] == 0x66 && dFA[5] == 0x74 && dFA[6] == 0x79 && dFA[7] == 0x70) {
                tExt = ".mp4";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x66 && dFA[1] == 0x74 && dFA[2] == 0x79 && dFA[3] == 0x70 && dFA[4] == 0x71 && dFA[5] == 0x74 && dFA[6] == 0x20 && dFA[7] == 0x20) {
                tExt = ".mov";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x66 && dFA[1] == 0x74 && dFA[2] == 0x79 && dFA[3] == 0x70 && dFA[4] == 0x6d && dFA[5] == 0x70 && dFA[6] == 0x34 && dFA[7] == 0x32) {
                tExt = ".m4v";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x66 && dFA[1] == 0x74 && dFA[2] == 0x79 && dFA[3] == 0x70 && dFA[4] == 0x69 && dFA[5] == 0x73 && dFA[6] == 0x6f && dFA[7] == 0x6d) {
                tExt = ".mp4";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x66 && dFA[1] == 0x74 && dFA[2] == 0x79 && dFA[3] == 0x70 && dFA[4] == 0x33 && dFA[5] == 0x67 && dFA[6] == 0x70 && dFA[7] == 0x35) {
                tExt = ".mp4";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x66 && dFA[1] == 0x74 && dFA[2] == 0x79 && dFA[3] == 0x70 && dFA[4] == 0x4d && dFA[5] == 0x53 && dFA[6] == 0x4e && dFA[7] == 0x56) {
                tExt = ".mp4";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x89 && dFA[1] == 0x50 && dFA[2] == 0x4e && dFA[3] == 0x47 && dFA[4] == 0x0d && dFA[5] == 0x0a && dFA[6] == 0x1a && dFA[7] == 0x0a) {
                tExt = ".png";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x1a && dFA[1] == 0x45 && dFA[2] == 0xdf && dFA[3] == 0xa3 && dFA[4] == 0x93 && dFA[5] == 0x42 && dFA[6] == 0x82 && dFA[7] == 0x88) {
                tExt = ".mkv";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x30 && dFA[1] == 0x26 && dFA[2] == 0xb2 && dFA[3] == 0x75 && dFA[4] == 0x8e && dFA[5] == 0x66 && dFA[6] == 0xcf && dFA[7] == 0x11) {
                tExt = ".wmv";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x52 && dFA[1] == 0x49 && dFA[2] == 0x46 && dFA[3] == 0x46 && dFA[8] == 0x41 && dFA[9] == 0x56 && dFA[10] == 0x49 && dFA[11] == 0x20) {
                tExt = ".avi";
                lf = 8;
                tempKey = i;
            } else if (dFA[0] == 0x3c && dFA[1] == 0x3f && dFA[2] == 0x78 && dFA[3] == 0x6d && dFA[4] == 0x6c && dFA[5] == 0x20) {
                tExt = ".svg";
                lf = 6;
                tempKey = i;
            } else if ((dFA[0] == 0x47 && dFA[1] == 0x49 && dFA[2] == 0x46 && dFA[3] == 0x38 && dFA[4] == 0x39 && dFA[5] == 0x61) || (dFA[0] == 0x47 && dFA[1] == 0x49 && dFA[2] == 0x46 && dFA[3] == 0x38 && dFA[4] == 0x37 && dFA[5] == 0x61)) {
                tExt = ".gif";
                lf = 6;
                tempKey = i;
            } else if (dFA[0] == 0x1a && dFA[1] == 0x45 && dFA[2] == 0xdf && dFA[3] == 0xa3) {
                tExt = ".webm";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x52 && dFA[1] == 0x49 && dFA[2] == 0x46 && dFA[3] == 0x46) {
                tExt = ".webp";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x01 && dFA[3] == 0xba) {
                tExt = ".vob";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x6d && dFA[1] == 0x6f && dFA[2] == 0x6f && dFA[3] == 0x76) {
                tExt = ".mov";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x66 && dFA[1] == 0x72 && dFA[2] == 0x65 && dFA[3] == 0x65) {
                tExt = ".mov";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x6d && dFA[1] == 0x64 && dFA[2] == 0x61 && dFA[3] == 0x74) {
                tExt = ".mov";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x77 && dFA[1] == 0x69 && dFA[2] == 0x64 && dFA[3] == 0x65) {
                tExt = ".mov";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x70 && dFA[1] == 0x6e && dFA[2] == 0x6f && dFA[3] == 0x74) {
                tExt = ".mov";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x73 && dFA[1] == 0x6b && dFA[2] == 0x69 && dFA[3] == 0x70) {
                tExt = ".mov";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x00 && dFA[1] == 0x00 && dFA[2] == 0x01 && dFA[3] == 0xb3) {
                tExt = ".mpg";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0xff && dFA[1] == 0xd8 && dFA[2] == 0xff && dFA[3] == 0xe0) {
                tExt = ".jpg";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0xff && dFA[1] == 0xd8 && dFA[2] == 0xff && dFA[3] == 0xe1) {
                tExt = ".jpg";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0xff && dFA[1] == 0xd8 && dFA[2] == 0xff && dFA[3] == 0xe8) {
                tExt = ".jpg";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x46 && dFA[1] == 0x4c && dFA[2] == 0x56 && dFA[3] == 0x01) {
                tExt = ".flv";
                lf = 4;
                tempKey = i;
            } else if (dFA[0] == 0x42 && dFA[1] == 0x4d) {
                tExt = ".bmp";
                lf = 2;
                tempKey = i;
            }

            if (findExt.equalsIgnoreCase(tExt)) {
                ext = tExt;
                key=tempKey;
                break;
            } else if(lf>last_lf){
                last_lf=lf;
                ext = tExt;
                key=tempKey;
            }

        }

        return new String[]{ext, String.valueOf(key)};
    }

    public static String[] find(byte[] encBytes){
        return find(encBytes,".");
    }
}
