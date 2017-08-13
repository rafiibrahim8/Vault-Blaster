#include<stdio.h>
#include<conio.h>
#include<string.h>

void manuExt(char *enFileName);
int getExt(char fileName[],char ext[]);
int fileWrite(char *inFileName,char *outFileName,unsigned char key);

void autoExt(char *enFileName)
{
    char choice,fext[6],deFileName[100];
    int i,err;
    printf("\nDetecting key and extension...");
    i=getExt(enFileName,fext);
    if(i<0)
    {
        printf("\n\aAutomatic Detection Failed.\nTry Manual Method instead?\n\tY=Yes\n\tAnyKey=No");
        choice=getch();
        if(choice=='y' || choice=='Y')
            manuExt(enFileName);
    }

    else
    {
        printf("\nDetected File Extension: %s\nKEY: %d\n",fext,i);
        printf("Enter New File Name to save: ");
        gets(deFileName);
        strcat(deFileName,fext);
        printf("\nMaking File...");
        err=fileWrite(enFileName,deFileName,(unsigned char)i);
        if(err)
            printf("\a\nOperation Failed.");
        else
            printf("\nOperation Complete.");
    }

}
