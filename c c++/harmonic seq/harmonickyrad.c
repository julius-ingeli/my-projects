#include<stdio.h>
#include<stdlib.h>


int main(int argc, char *argv[]){
    int numargument;
    if(argc == 2){
        char *argument = argv[1];
        numargument = atoi(argument);
        printf("Working with n=%d\n",numargument);
        }
    else if(argc > 2){
        printf("Received too many arguments.");
        return 0;
        }
    else{
        printf("No arguments.");
        //return 0;
        }
    int n = numargument;
    double citatel = 1;
    double menovatel = 1;
    double sucet = 0;
    double vysledok;
    while(sucet<=n){
        vysledok = citatel/menovatel;
        //printf("Delim: 1/%f\n",menovatel);
        //printf("Vysledok:%f\n",vysledok);
        sucet+=vysledok;
        //printf("Sucet:%f\n",sucet);
        menovatel++;
        //printf("------------------------------\n");


    }
    printf("Menovatel: %f\nVysledok: %f\n",menovatel-1,vysledok);
    return 0;
    }
