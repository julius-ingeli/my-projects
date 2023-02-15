#include<iostream>

using namespace std;


int main(int argc, char *argv[0]){
    int numargument;
    if (argc == 2){
        char *argument = argv[1];
        numargument = atoi(argument);
        cout<<"Working with n="<<numargument<<endl;

        }
    else if (argc>2)
    {
        cout<<"Received too many arguments."<<endl;
        exit(0);
        }
    else{
        cout<<"No arguments."<<endl;
        }
    int n = numargument;
    double citatel = 1;
    double menovatel = 1;
    double sucet = 0;
    double vysledok;
    while (sucet<=n)
    {
        vysledok= citatel/menovatel;
        sucet+=vysledok;
        menovatel++;
    }
    cout<<"Menovatel: "<<menovatel-1<<" Vysledok: "<<vysledok<<endl;
    return 0;
}