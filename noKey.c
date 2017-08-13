#include<stdio.h>
#include<string.h>
#include<conio.h>

int havFile(char *fname);
void autoExt(char *enFileName);
void manuExt(char *enFileName);

void noKey(void)
{
    char choice,fileName[105];

    printf("\nEnter file Name (with extension):");
    gets(fileName);

    while(!havFile(fileName))
    {
        printf("\n\aFile Not Found! Make sure you have entered correct file name with extension.\nPlease enter again: ");
        gets(fileName);
    }

    printf("\n\nPlease choose an option:\n\t1. Automatic Password and extension detection. (Recommended)\n\t2. Manual Mode.");
    choice=getch();

    while(!(choice=='1' || choice=='2'))
    {
        printf("\n\n\aValid input is either 1 or 2.\n\t1. Automatic\n\t2. Manual\nPlease Select.");
        choice=getch();
    }

    switch(choice)
    {
    case '1':
        autoExt(fileName);
        break;
    case '2':
        manuExt(fileName);
        break;
    default:
        printf("\nUnknown Error!");
        break;
    }
}
