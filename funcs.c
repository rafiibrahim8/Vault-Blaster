#include<stdio.h>
#include<string.h>
#include<windows.h>

int getInt(void)
{
    char arr[30];
    gets(arr);
    int i,sum=0,len=strlen(arr);

    for(i=0;i<len;i++)
    {
        if(arr[i]>47 && arr[i]<58)
            sum=sum*10+arr[i]-48;
        else
        {
            sum=-1;
            break;
        }
    }
    return sum;
}


unsigned char getKey(int x)
{
    int k;
    unsigned char key;
    do
    {
        printf("\nEnter %s key (0-255):",x>0?"a":"the");
        k=getInt();
    }
    while(k<0 || k>255);
    key=(unsigned char)k;
    return key;
}

int havFile(char *fname)
{
    FILE *ftest;
    int r;
    ftest=fopen(fname,"rb");
    if(ftest!=NULL)
        r=1;
    else
        r=0;
    fclose(ftest);
    return r;
}

int fileWrite(char *inFileName,char *outFileName,unsigned char key)
{
    FILE *frd,*fwr;
    unsigned char buffer;
    int bytCo=0,bytEn=1,ret=0;
    frd=fopen(inFileName,"rb");
    fwr=fopen(outFileName,"wb");
    if(fwr!=NULL)
    {
        while(1)
        {
            fread(&buffer,1,1,frd);
            if(feof(frd))
                break;
            if(bytEn)
            {
                buffer=buffer^key;
                bytCo++;
                if(bytCo==128)
                    bytEn=0;
            }

            fwrite(&buffer,1,1,fwr);
        }
        fclose(fwr);
    }

    else
    {
        ret=1;
        printf("\nFile Writing ERROR!");
    }

    fclose(frd);

    return ret;
}

int getExt(char fileName[],char ext[])
{
    int key=-1,lf=0;
    unsigned int i,j;
    FILE *frd;
    char tExt[6]="NFND";
    unsigned char fileArr[12],dFA[12];
    frd=fopen(fileName,"r+b");
    fread(fileArr,12,1,frd);
    fclose(frd);
    for(i=0;i<256;i++)
    {
        for(j=0;j<12;j++)
        {
            dFA[j]=fileArr[j]^(unsigned char)i;
        }

        if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && (dFA[3]==0x18 || dFA[3]==0x14 || dFA[3]==0x1c) && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x33 && dFA[9]==0x67 && dFA[10]==0x70 && dFA[11]==0x35)
            {
                strcpy(tExt,".mp4");
                lf=12;
                key=i;
                break;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && (dFA[3]==0x18 || dFA[3]==0x14 || dFA[3]==0x1c) && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x4d && dFA[9]==0x53 && dFA[10]==0x4e && dFA[11]==0x56)
            {
                strcpy(tExt,".mp4");
                lf=12;
                key=i;
                break;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && (dFA[3]==0x18 || dFA[3]==0x14 || dFA[3]==0x1c) && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x69 && dFA[9]==0x73 && dFA[10]==0x6f && dFA[11]==0x6d)
            {
                strcpy(tExt,".mp4");
                lf=12;
                key=i;
                break;
            }


        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && dFA[3]==0x1c && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x33 && dFA[9]==0x67 && dFA[10]==0x32 && dFA[11]==0x61)
            {
                strcpy(tExt,".3g2");
                lf=12;
                key=i;
                break;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && dFA[3]==0x14 && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x71 && dFA[9]==0x74 && dFA[10]==0x20 && dFA[11]==0x20)
            {
                strcpy(tExt,".mov");
                lf=12;
                key=i;
                break;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && dFA[3]==0x20 && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x4d && dFA[9]==0x34 && dFA[10]==0x56 && dFA[11]==0x20)
            {
                strcpy(tExt,".m4v");
                lf=12;
                key=i;
                break;
            }
        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && dFA[3]==0x18 && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x6d && dFA[9]==0x70 && dFA[10]==0x34 && dFA[11]==0x32)
            {
                strcpy(tExt,".mp4");
                lf=12;
                key=i;
                break;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && dFA[3]==0x14 && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70 && dFA[8]==0x33 && dFA[9]==0x67 && dFA[10]==0x70)
            {
                strcpy(tExt,".3gp");
                lf=11;
                key=i;
                break;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x00 && dFA[3]==0x18 && dFA[4]==0x66 && dFA[5]==0x74 && dFA[6]==0x79 && dFA[7]==0x70)
            {
                strcpy(tExt,".mp4");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x66 && dFA[1]==0x74 && dFA[2]==0x79 && dFA[3]==0x70 && dFA[4]==0x71 && dFA[5]==0x74 && dFA[6]==0x20 && dFA[7]==0x20)
            {
                strcpy(tExt,".mov");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x66 && dFA[1]==0x74 && dFA[2]==0x79 && dFA[3]==0x70 && dFA[4]==0x6d && dFA[5]==0x70 && dFA[6]==0x34 && dFA[7]==0x32)
            {
                strcpy(tExt,".m4v");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x66 && dFA[1]==0x74 && dFA[2]==0x79 && dFA[3]==0x70 && dFA[4]==0x69 && dFA[5]==0x73 && dFA[6]==0x6f && dFA[7]==0x6d)
            {
                strcpy(tExt,".mp4");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x66 && dFA[1]==0x74 && dFA[2]==0x79 && dFA[3]==0x70 && dFA[4]==0x33 && dFA[5]==0x67 && dFA[6]==0x70 && dFA[7]==0x35)
            {
                strcpy(tExt,".mp4");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x66 && dFA[1]==0x74 && dFA[2]==0x79 && dFA[3]==0x70 && dFA[4]==0x4d && dFA[5]==0x53 && dFA[6]==0x4e && dFA[7]==0x56)
            {
                strcpy(tExt,".mp4");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x89 && dFA[1]==0x50 && dFA[2]==0x4e && dFA[3]==0x47 && dFA[4]==0x0d && dFA[5]==0x0a && dFA[6]==0x1a && dFA[7]==0x0a)
            {
                strcpy(tExt,".png");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x1a && dFA[1]==0x45 && dFA[2]==0xdf && dFA[3]==0xa3 && dFA[4]==0x93 && dFA[5]==0x42 && dFA[6]==0x82 && dFA[7]==0x88)
            {
                strcpy(tExt,".mkv");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x30 && dFA[1]==0x26 && dFA[2]==0xb2 && dFA[3]==0x75 && dFA[4]==0x8e && dFA[5]==0x66 && dFA[6]==0xcf && dFA[7]==0x11)
            {
                strcpy(tExt,".wmv");
                lf=8;
                key=i;
                break;
            }

        else if(dFA[0]==0x52 && dFA[1]==0x49 && dFA[2]==0x46 && dFA[3]==0x46 && dFA[8]==0x41 && dFA[9]==0x56 && dFA[10]==0x49 && dFA[11]==0x20)
            {
                strcpy(tExt,".avi");
                lf=8;
                key=i;
            }

        else if(dFA[0]==0x3c && dFA[1]==0x3f && dFA[2]==0x78 && dFA[3]==0x6d && dFA[4]==0x6c && dFA[5]==0x20)
            {
                strcpy(tExt,".svg");
                lf=6;
                key=i;
            }

        else if((dFA[0]==0x47 && dFA[1]==0x49 && dFA[2]==0x46 && dFA[3]==0x38 && dFA[4]==0x39 && dFA[5]==0x61) || (dFA[0]==0x47 && dFA[1]==0x49 && dFA[2]==0x46 && dFA[3]==0x38 && dFA[4]==0x37 && dFA[5]==0x61))
            {
                strcpy(tExt,".gif");
                lf=6;
                key=i;
            }

        else if(dFA[0]==0x1a && dFA[1]==0x45 && dFA[2]==0xdf && dFA[3]==0xa3 && lf<4)
            {
                strcpy(tExt,".webm");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x52 && dFA[1]==0x49 && dFA[2]==0x46 && dFA[3]==0x46 && lf<4)
            {
                strcpy(tExt,".webp");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x01 && dFA[3]==0xba && lf<4)
            {
                strcpy(tExt,".vob");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x6d && dFA[1]==0x6f && dFA[2]==0x6f && dFA[3]==0x76 && lf<4)
            {
                strcpy(tExt,".mov");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x66 && dFA[1]==0x72 && dFA[2]==0x65 && dFA[3]==0x65 && lf<4)
            {
                strcpy(tExt,".mov");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x6d && dFA[1]==0x64 && dFA[2]==0x61 && dFA[3]==0x74 && lf<4)
            {
                strcpy(tExt,".mov");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x77 && dFA[1]==0x69 && dFA[2]==0x64 && dFA[3]==0x65 && lf<4)
            {
                strcpy(tExt,".mov");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x70 && dFA[1]==0x6e && dFA[2]==0x6f && dFA[3]==0x74 && lf<4)
            {
                strcpy(tExt,".mov");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x73 && dFA[1]==0x6b && dFA[2]==0x69 && dFA[3]==0x70 && lf<4)
            {
                strcpy(tExt,".mov");
                lf=4;
                key=i;
            }
        else if(dFA[0]==0x00 && dFA[1]==0x00 && dFA[2]==0x01 && dFA[3]==0xb3 && lf<4)
            {
                strcpy(tExt,".mpg");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0xff && dFA[1]==0xd8 && dFA[2]==0xff && dFA[3]==0xe0 && lf<4)
            {
                strcpy(tExt,".jpg");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0xff && dFA[1]==0xd8 && dFA[2]==0xff && dFA[3]==0xe1 && lf<4)
            {
                strcpy(tExt,".jpg");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0xff && dFA[1]==0xd8 && dFA[2]==0xff && dFA[3]==0xe8 && lf<4)
            {
                strcpy(tExt,".jpg");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x46 && dFA[1]==0x4c && dFA[2]==0x56 && dFA[3]==0x01 && lf<4)
            {
                strcpy(tExt,".flv");
                lf=4;
                key=i;
            }

        else if(dFA[0]==0x42 && dFA[1]==0x4d && lf<2)
            {
                strcpy(tExt,".bmp");
                lf=2;
                key=i;
            }

    }

    strcpy(ext,tExt);

    return key;
}

void typEff(char str[])
{
    int i,len=strlen(str);
    for(i=0;i<len;i++)
    {
        if(!(str[i]==' ' || str[i]=='\n'))
            Sleep(150);
        printf("%c",str[i]);
    }
}
