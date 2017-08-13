#include<stdio.h>
#include<conio.h>
#include<windows.h>
void noKey(void);
void havKey(void);
void about(void);

int main()
{
    int x=1,y;
    char choice;
    SetConsoleTitle("Vault Blaster");
    system("COLOR 0A");
    while(x)
    {
        do
        {
            y=0;
            printf("Choose a option:\n\t1. I don\'t know the key.\n\t2. I know the key.\n\n\t3. About the program.");
            char choice=getch();
            if(choice=='1')
                noKey();
            else if(choice=='2')
                havKey();
            else if(choice=='3')
                about();
            else
            {
                printf("\a\n\nInvalid Selection.\n\n");
                y=1;
            }
        }
        while(y);

        printf("\n\nDo you want to start over?\n\tY=Yes\n\tAnyKey=No");
        choice=getch();
        if(!(choice=='Y' || choice=='y'))
            x=0;
        else
            printf("\n\n");
    }
    printf("\n\n\t\tPress a key to EXIT.");
    getch();
    return 0;
}
