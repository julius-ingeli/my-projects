#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>

/*
notes:      1. possible bugs with rotating ships
            2. for quick ship placement, reccomended to paste when first ship input promp shows up: AA1VNYBA2VNYSA3VNYCA4VNYDA5VNY               
               yup, its on purpose
            3. windows (even 11) and linux supported, compile using the supplied script
            4. on row 773 I left a debug so enemy ships are visible, this is as close as it gets to having cheats
cheats: fire upon all - enter into console once you get first coordinates: A1A2A3A4A5A6A7A8A9B1B2B3B4B5B6B7B8B9C1C2C3C4C5C6C7C8C9D1D2D3D4D5D6D7D8D9E1E2E3E4E5E6E7E8E9F1F2F3F4F5F6F7F8F9G1G2G3G4G5G6G7G8G9H1H2H3H4H5H6H7H8H9I1I2I3I4I5I6I7I8I9
        see all ships - see note 4
*/

#ifdef _WIN32
 #include <windows.h>
 #define PAUSE(sleeptime)              Sleep(10000/sleeptime)
 #define CLEAR_SCREEN()       system("cls")
#else
 #include <unistd.h>
 #define PAUSE(sleeptime)              usleep(10000000/sleeptime)
 #define CLEAR_SCREEN()       system("clear");
#endif

#define MAX_NAME 50

typedef struct{
    char symbol;
    int size;
    int selected;
    char fullName[MAX_NAME];
    int p_dead;
    int o_dead;
}Ship;

//colors and graphics

void c_reset() {
    printf("\033[0m");
}

void c_hit(){
    printf("\033[7;31m");
}
void c_miss(){
    printf("\033[7;37m");
}
void c_water(){
    printf("\033[7;94m");
}
void c_coordindex(){
    printf("\033[30;43m");
}

void c_carrier(){
    printf("\033[0m");
}

void c_bship() {
    printf("\033[7;92m");
}

void c_sub() {
    printf("\033[48;2;60;45;117m");
}

void c_cruiser() {
    printf("\033[48;2;71;32;8m");
}

void c_destroyer() {
    printf("\033[7;97m");
}


void draw_right_bord(int i, int *r_border_f){
        c_coordindex();
        printf(" %c",'A'+i);
        *r_border_f = 0;
        c_reset();

}


int rng(int min, int max) {
    return rand() % (max - min + 1) + min; //standard rng function
}

void init_board(char** player_board, char** opponent_board,char** guess_board ,int rows, int cols) {
    //player board
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            player_board[i][j] = ' ';
        }
    }

    // enemy board
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            opponent_board[i][j] = ' ';
        }
    }
    // guess board
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            guess_board[i][j] = ' ';
        }
    }
    
}
void print_board(char** board, int rows, int cols) {
    int r_border_f = 0; //flag of the right border
    c_coordindex();
    printf("  ");
        for (int i = 1; i <= cols; i++) {
        printf("%d ", i);
    }
    printf("  ");
    c_reset();
    printf("\n");

    for (int i = 0; i < rows; i++) {
        c_coordindex();
        printf("%c ", 'A' + i);
        c_reset();
        for (int j = 0; j < cols; j++) {
            if(j==cols-1){
                r_border_f = 1;  
            }

            switch (board[i][j]){ //see which ship is about to be printed, apply relevant graphics
                case ' ':
                    c_water();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){ //its terrible, I know
                        draw_right_bord(i,&r_border_f);
                    }
                    break;

                case 'X':
                    c_hit();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }
                    break;
                case 'O':
                    c_miss();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }
                    break;

                case 'A':
                    c_carrier();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }
                    break;
                case 'B':
                    c_bship();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }                
                    break;
                case 'S':
                    c_sub();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }                
                    break;
                case 'C':
                    c_cruiser();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }                
                    break;    
                case 'D':
                    c_destroyer();
                    printf("%c ", board[i][j]);
                    c_reset();
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
                    }                
                    break;    


                
                default:
                    c_reset();
                    printf("%c ", board[i][j]);
                    if(r_border_f == 1){
                        draw_right_bord(i,&r_border_f);
            }                
                    break;
                }
        }
        printf("\n");
    }
    c_coordindex();
    printf("  ");
        for (int i = 1; i <= cols; i++) {
        printf("%d ", i);
    }
    printf("  ");
    c_reset();
    printf("\n");
}


int place_ship(char** board, int rows, int cols, int size, char symbol) {
    char y;
    int x, coord_pass, orient_pass, valid;
    char orientation,neg_dir,confirm, ship_retain;
    coord_pass = 0;
    orient_pass = 0;
    valid = 1;

    printf("Enter coordinates where you want your %c ship of size %d placed. (ex. A1): ",symbol,size);

    while (coord_pass == 0)
    {
        
        scanf(" %c%d", &y, &x); // (row, column) convention
        

        if(isalpha(y)==0){
            printf("Incorrect coordinate format, try again.\n");
            continue;
        }
        //decrement for indexing
        x--;
        //conversion to index
        y = toupper(y) - 'A';    //ex. D-A (68-65) = 3
    
        //validation of coordinates
        if (x < 0 || x >= cols || y < 0 || y >= rows) {
            printf("Incorrect coordinates, try again.\n");
            continue;
        }
        //check if there is not a ship on the coordinate
        else{
            if(board[y][x]!= ' '){
                printf("On coordinates %c%d there is already a ship %c, try again.\n", y+65, x, board[y][x+1]);
                continue;
            }
            coord_pass = 1;
            
        }

    }
    
    // user input for ship orientation
    printf("Enter how you want your ship oriented: (H - horizontally , V - vertically): ");

    while (orient_pass == 0)
    {
        scanf(" %c", &orientation);
        orientation = toupper(orientation);
        if(orientation == 'Q'){
            CLEAR_SCREEN();
            printf("Back to ship selection...\n");
            PAUSE(2);
            return 1; //return to ship select
        }

        else if (orientation != 'H' && orientation != 'V') {
            printf("Incorrect orientation, please enter H for horizontal or V for vertical.\n");
            continue;
        }    

        valid = 1;
        neg_dir = 'N';
        
        printf("Orientate ship into the negative direction? Y/N:\n"); //negative direction, so the ship placed at C3 V in negative direction will be placed C3,C2.C1...
        scanf(" %c",&neg_dir);
        neg_dir = toupper(neg_dir);
        if(neg_dir == 'Q'){
            CLEAR_SCREEN();
            printf("Back to ship selection...\n");
            PAUSE(2);
            return 1; //return to ship select
        }
        else if(neg_dir != 'Y' && neg_dir != 'N'){
            printf("Incorrect selection, keeping the ship in the positive direction\n");
            continue;
        }


        
        //validation of orientation
    

        
        if (orientation == 'H'){ 
            if (x + size >= cols&&neg_dir == 'N'){  //check whether the ship is in the board for positive direction
                printf("Ship would be placed out of bounds, try orienting different.\n");
                continue;
            }
            
            else if(x - size < -1&&neg_dir == 'Y'){   //check whether the ship is in the board for negative direction  
                printf("Ship would be placed out of bounds, try orienting different.\n");
                continue;
            }
            
            if(neg_dir == 'N'){
                for(int i = x; i < x + size;i++){ //check whether the ship does not intersect with another ship for positive direction
                    if(valid == 0){
                        break;
                    }
                    if (board[y][i]!= ' '){ //fucked up here
                        printf("Placed ship is blocked by another ship on %c%d, try different orientation.\n", y+65,i+1);
                        valid = 0;
                        continue;
                    }
                }
            }
            else{ //neg_dir == 'Y'
                for(int i = x; i> x-size;i--){ //check whether the ship does not intersect with another ship for negative direction
                    if(valid == 0){
                        break;
                    }
                    if (board[y][i]!= ' '){
                        printf("Placed ship is blocked by another ship on %c%d, try different orientation.\n", y+65,i+1);
                        valid = 0;
                        continue;
                    }
                }
            }       
            
            if(valid == 0){
                continue;
            }

            if(neg_dir == 'N'){
                for(int i = x; i < x + size;i++){   //placement in positive direction 
                    board[y][i] = symbol;
                }
                orient_pass = 1;
            }
            else{ //neg_dir == 'Y'
                for(int i = x; i > x - size;i--){   //placement in negative direction
                    board[y][i] = symbol;
                }
                orient_pass = 1;
            }
        }

        
        else{       //orientation == 'V'
            if (y + size > rows&&neg_dir == 'N') {  //check whether the ship is in the board for positive direction
                printf("Ship would be placed out of bounds, try orienting different.\n");
                continue;
            }

            else if(y-size < -1&&neg_dir=='Y'){        //check whether the ship is in the board for negative direction  
                printf("Ship would be placed out of bounds, try orienting different.\n");
                continue;
            }


            if(neg_dir == 'N'){
                for (int i = y; i < y + size; i++) { //check whether the ship does not intersect with another ship for positive direction
                    if(valid == 0){
                        break;
                    }                        
                    if (board[i][x] != ' ') {
                        printf("Placed ship is blocked by another ship on %c%d, try different orientation.\n", y+65,i+1);
                        valid = 0;
                        continue;
                    }
                }
            }
            else{ //neg_dir == 'Y'
                for (int i = y; i > y - size; i--) { //check whether the ship does not intersect with another ship for negative direction
                    if(valid == 0){
                        break;
                    }                          
                    if (board[i][x] != ' ') {
                        printf("Placed ship is blocked by another ship on %c%d, try different orientation.\n", y+65,i+1);
                        valid = 0;
                        continue;
                    }
                }
            }

            if(valid == 0){
                continue;
            }

            if(neg_dir=='N'){
                for(int i = y; i < y + size;i++){   //placement in positive direction
                    board[i][x] = symbol;
                }
                orient_pass = 1;
            }
            else{   //neg_dir == 'Y'
                for(int i = y;i>y-size;i--){    //placement in negative direction        
                    board[i][x] = symbol;
                }
                orient_pass = 1;

                }
            }
        }

    
    CLEAR_SCREEN();
    print_board(board, rows, cols);

    printf("Confirm ship position? Y/N:");
    scanf(" %c",&confirm);
    switch (toupper(confirm)){
        case 'N':       //replacing the placed ship with water
            if (orientation == 'H') {           //horizontal
                if(neg_dir == 'N'){             //positive direction
                    for (int i = x; i < x + size; i++) {
                        board[y][i] = ' ';
                    }
                }
                else{                           //negative direction
                    for(int i = x; i>x-size;i--){
                        board[y][i] = ' ';
                    }
                }
            } 
            else {                              //veritcal
                if(neg_dir == 'N'){             //positive direction
                    for (int i = y; i < y + size; i++) {
                        board[i][x] = ' ';
                    }
                }
                else{                           //negative direction
                    for (int i = y; i > y - size; i--) {
                        board[i][x] = ' ';
                    }
                }
            }
            orient_pass = 0;
            printf("Keep the ship type? Y/N\n"); //In case the ship is not desirable
            scanf(" %c",&ship_retain);
            ship_retain = toupper(ship_retain);
            if(ship_retain!='Y'&&ship_retain!='N'){
                printf("Incorrect input, returning back to ship selection..\n");
                PAUSE(3);
                return 1;    
            }
            else{
                if(ship_retain=='Y'){
                    break;
                }
                else{
                    return 1; //back to ship selection
                }
            }   
            break;
        
        case 'Y': //ship position confirmed, going out of the function
            break;


        default: //pay attention when placing the ship lol
            printf("Incorrect input, placed ship will retain its position.");
            break;
    }
    return 0; 
}


int place_enemy_ship(char** board, int rows, int cols, int size, char symbol){
    int x,y; 
    char randOrient;

    x = rng(0,cols-1);  //s o p h i s t i c a t e d    a i
    y = rng(0,rows-1);
    randOrient = rng(0,1);
    
    //check coords and orientation

    if (randOrient == 0){ //horizontal
        if (x + size >= cols){
            return 1;
        }
        for(int i = x; i < x + size;i++){
            if (board[y][i]!= ' '){
                return 1;
            }
        }
    }
    else {               //vertical
        if (y + size > rows) {
            return 1;
        }
        for (int i = y; i < y + size; i++) {
            if (board[i][x] != ' ') {
                return 1;
            }
        }
    }

    // placing the ship on the board
    if (randOrient == 0) { //horizontal
        for (int i = x; i < x + size; i++) {
            board[y][i] = symbol;
        }
    } 
    else {      //vertical
        for (int i = y; i < y + size; i++) {
            board[i][x] = symbol;
        }
    }


    return 0;


}

int player_guess(int rows, int cols, int *fire_x, int *fire_y, char**board){
    int x;
    char y;
    printf("Enter coordinates for fire mission:");
    scanf(" %c%d",&y,&x);
    y = toupper(y) - 'A';       //same trick for indexing as in the player ship placement coordiante
    x--;                            //indexing
    if(x<0 || y<0 || x>cols || y>rows ){
        printf("Coordinates are not on the battlefield, fire mission denied!\n");
        return 1;
    }
    else if(board[y][x]=='X'){
        printf("Entered coordinate has already been fired upon, fire mission denied!\n");
        return 1;
    }
    *fire_x = x;
    *fire_y = y;
    return 0;

}

int opponent_guess(int rows, int cols, int *fire_x, int *fire_y, char**board){
    int x,y;
    x = rng(0,cols-1);
    y = rng(0,rows-1);
    if(board[y][x] == 'X'){
        return 1;
    }

    *fire_x = x;
    *fire_y = y;
    return 0;

}



int p_fire(int fire_x, int fire_y, char **board, char** g_board){
    char hit;

        if(board[fire_y][fire_x]==' '){       //water hit
            printf("Shot did not hit anything on the coordinates %c%d!\n",fire_y+65,fire_x+1);
            board[fire_y][fire_x] = 'X';      //marks the spot on enemy board
            g_board[fire_y][fire_x] = 'O';    //marks miss on the guess board  
            return 1; 
        }
        else{
            hit = board[fire_y][fire_x];      //hit
            printf("Direct hit upon ship %c on coordinates %c%d!\n",hit,fire_y+65,fire_x+1);
            board[fire_y][fire_x] = 'X';      //marks the spot on enemy board
            g_board[fire_y][fire_x] = 'X';    //marks the hit on guess board
            return 0;
        }

}

int o_fire(int fire_x, int fire_y, char **board){ //function overloading, declaring two same functions with different parameters
    char hit;

    if(board[fire_y][fire_x]==' '){        
        printf("Enemy missed their shot at coordinates %c%d!\n",fire_y+65,fire_x+1);
        board[fire_y][fire_x] = 'X'; 
        return 1; 
    }
    else{
        hit = board[fire_y][fire_x];
        printf("Enemy hit our %c ship on coordinates %c%d!\n",hit,fire_y+65,fire_x+1);
        board[fire_y][fire_x] = 'X';
        return 0;
    }
}


void dead_ship_check(char**board, Ship ships[], int rows, int cols, char turn){ //check of destroyed ships by the enemy //unused function
    char chk_ship;
    if(turn == 'P'){
        for(int i = 0; i < 5; i++){
	        int hp_ship = 0;
            chk_ship = ships[i].symbol;
            if(ships[i].p_dead == 1 || ships[i].o_dead == 1){
                continue;
            }
            for(int j = 0; j<rows;j++){
                for(int k = 0; k < cols; k++){
                    if(board[j][k]==chk_ship){
                        hp_ship++;
                    }
                }
            }
            
            if(hp_ship<1){
                printf("You have destroyed the enemy %s!\n",ships[i].fullName);
                ships[i].o_dead = 1;
                PAUSE(2);
            }   
        }
    }
    else{ //turn == 'O'
        for(int i = 0; i < 5; i++){
            int hp_ship = 0;
            chk_ship = ships[i].symbol;
            if(ships[i].p_dead == 1 || ships[i].p_dead == 1){
                continue;
            }
            for(int j = 0; j<rows;j++){
                for(int k = 0; k < cols; k++){
                    if(board[j][k]==chk_ship){
                        hp_ship++;
                    }
                }
            }
            
            if(hp_ship<1){
                printf("Our %s has been destroyed by the enemy!\n",ships[i].fullName);
                ships[i].o_dead = 1;
                PAUSE(2);
            }   
        }
    }

}



int game_end_check(char**board, int rows, int cols){    //check if the game is over
    int hpcount = 0;
    for(int i = 0; i<rows;i++){
        for(int j = 0; j < cols; j++)
        {
            if(board[i][j] != 'X' && board[i][j] != ' '){
                hpcount++;
            }
        }
        
    }
    if(hpcount>0){
        return 1; //game on
    }

    return 0; //game over
}

int main(){
    int rows, cols, p_valsel, o_sel, fire_x, fire_y, p_shot, o_shot, ssi;
    CLEAR_SCREEN();
    printf("Welcome player, you will play a game of \033[034mBattleships\033[0m, built in \033[38;2;0;0;255;48;2;255;255;255mC\033[0m, made by \033[35mJulius Ingeli\033[0m\n");
    printf("\033[031m!!!!READ CAREFULLY!!!!\033[0m\n");
    printf("\033[093mRules:\033[0m\n");
    printf("\t1.Board has a size of 9x9\n");
    printf("\t2.You have 5 different ships available to you, which you will place on the board.\n");
    printf("\t3.Ships are placed on coordinates according to the board(A1, B2...), from there, you can orientate your ship horizontally(H) or vertically(V),\n\t  you can also orientate it into \033[003mnegative\033[0m direction (ex. ship placed on A9 placed H into negative direction will go A9, A8, A7..)\n\t  note 1: Ships \033[1mARE UNIQUE\033[0m \n\t  note 2: should you not be satisfied with ship position (orientation is bad or position is bad) you can just enter 'Q' and you will be set back to ship selection.\n");
    printf("\t4.The enemy also has 5 ships placed RANDOMLY on the board.\n");
    printf("\t5.You order fire mission coordinates whenever the program orders you to enter them.\n\t  Entering the fire mission coordinates is the same as when placing the ships: A1, B8...\n");
    printf("\t6.Program will alert you when you fire on coordinates that you fired on previously or outside of the board, if that happens, do not worry, you can enter them again.\n");
    printf("\t7.You win when you destroy the entire enemy fleet\n\t  You lose when you lose your entire fleet\n");
    printf("\t8.Enjoy the game :)");
    printf("\n\nIf you understand the rules, press \033[1mENTER\033[0m\n");
    scanf("?");
    CLEAR_SCREEN();
    
    char t_adjust;
    int p_shootTime, o_shootTime;
    p_shootTime = o_shootTime = 3;

    printf("Do you want to adjust time between turns? Y/N\n");
    scanf(" %c",&t_adjust);
    t_adjust = toupper(t_adjust);
    if(t_adjust == 'Y'){
        printf("Time adjustment for player turn: ");
        scanf(" %d", &p_shootTime);
        printf("Time adjustment for opponent turn: ");
        scanf(" %d", &o_shootTime);
        PAUSE(1);
    }
    else{
        printf("Cont..\n");
    }
    
    CLEAR_SCREEN();
    //ship symbol , size, full name, selected/unselected
    Ship ships[] = {
        {'A', 5, 0, "Aircraft carrier",0,0},    
        {'B', 4, 0, "Battleship",0,0},   
        {'S', 3, 0, "Submarine",0,0},
        {'C', 3, 0, "Cruiser",0,0},  
        {'D', 2, 0, "Destroyer",0,0}    
    };
    char shipselect, turn, winner;

    //seeding RNG
    srand(time(NULL));

   //hardcoded so the board is 9x9
    cols = 9;
    rows = 9;
    const int area = cols * rows;
    char p_guesses[area][2]; //list of guess

    //generation of boards
    //Y coords
    char** player_board = (char**) malloc(rows * sizeof(char*)); //player's
    char** opponent_board = (char**) malloc(rows * sizeof(char*)); //enemy's
    char** guess_board = (char**) malloc(rows * sizeof(char*)); //guess board's
    //X coords
    for (int i = 0; i < rows; i++) {
        player_board[i] = (char*) malloc(cols * sizeof(char));  
        opponent_board[i] = (char*) malloc(cols * sizeof(char));
        guess_board[i] = (char*) malloc(cols * sizeof(char));
        
    }
    init_board(player_board, opponent_board,guess_board ,rows, cols); //fills the board with water
    
    
    //selection and positioning of player's ships

    for (int i = 0;i<5; i++){ //number of ships
        printf("\033[032mYour ships:\n\033[0m");
        print_board(player_board, rows, cols);
        printf("Select from available ships:\n");
        
        for(int l = 0; l < 5; l++){
            if(ships[l].selected != 1){
                printf("%c - %s, Length - %d\n", ships[l].symbol, ships[l].fullName, ships[l].size);
            }
        }

        scanf(" %c",&shipselect);
        shipselect = toupper(shipselect);
        switch (shipselect){
            case 'A':
                ssi = 0;
                break;

            case 'B':
                ssi = 1;
                break;  

            case 'S':
                ssi = 2;
                break;      

            case 'C':
                ssi = 3;
                break;

            case 'D':
                ssi = 4;
                break;

            default:
                printf("Incorrect ship type, try again.");
                i--;
                CLEAR_SCREEN();
                continue;
                break;
        }

        if(ships[ssi].size>cols||ships[ssi].size>rows){
            printf("Ship would not fit the board, select something else.\n");   //not used
            i--;
            continue;
        }

        if(ships[ssi].selected == 0){
            p_valsel = 1;
            ships[ssi].selected = 1;
            int ps = place_ship(player_board,rows,cols,ships[ssi].size,ships[ssi].symbol);
            if(ps==1){ //case that the ship position is not confirmed but the ship selection should retain
                ships[ssi].selected = 0;
                i--;
                CLEAR_SCREEN();
                continue;
            }
            CLEAR_SCREEN();
            continue;
        }
        else{
            printf("Ship already selected, try again.\n");
            i--;
        }
        if(!p_valsel){
            printf("Not correct ship type, try again\n");
            i--;
        }
    }
    //reset select
    printf("Done placing the ships, opponent is placing its ships, stand by...\n");
    for(int i = 0;i<5;i++){
        ships[i].selected = 0;
    }

    //selection and positioning of enemy ships
    for (int i = 0;i<5;i++){
        o_sel = i;
        if(place_enemy_ship(opponent_board,rows,cols,ships[o_sel].size,ships[o_sel].symbol)!=0){
            i--;
        }
        ships[o_sel].selected = 1;
    }
    CLEAR_SCREEN();
    //game loop
    int game = 1;
    int p_turns = 0;
    while (game == 1)
    {
        turn = 'P';
        printf("\033[032mYour ships:\n\033[0m");
        print_board(player_board,rows,cols); // player ship board print
        p_shot = 0;
        printf("\033[033mFired coordinates:\n\033[0m"); //guess board print
        print_board(guess_board,rows,cols);

        printf("\n");
        while (p_shot != 1){
            if(player_guess(rows,cols,&fire_x,&fire_y,opponent_board)!=1){
                p_shot = 1;
            }
        }
        if(p_fire(fire_x,fire_y,opponent_board,guess_board)==0){
            dead_ship_check(opponent_board,ships,rows,cols,turn); //checks opponents dead ships
        }
        //uncomment if you want to see enemy ships
        
        printf("DEBUG:\n");
        print_board(opponent_board,rows,cols);
        PAUSE(1);
        

        winner = turn;
        game = game_end_check(opponent_board, rows, cols);
        if(game<1){
            break;           
        }
        PAUSE(p_shootTime);
        printf("\033[31mEnemy's turn..\033[0m\n");
        turn = 'O';


        o_shot = 0;
        while(o_shot != 1){
            if(opponent_guess(rows,cols,&fire_x,&fire_y,player_board)!=1){
                o_shot = 1;
            }
        }
        if(o_fire(fire_x,fire_y,player_board)==0){;
            dead_ship_check(player_board,ships,rows,cols,turn); //checks players dead ships
        }
        winner = turn; 
        game = game_end_check(player_board, rows, cols);
        PAUSE(o_shootTime);
        CLEAR_SCREEN();
        p_turns++;
    }
    
    if(winner == 'P'){
        printf("\033[32mVICTORY!\033[0m\n");
    }
    else{
        printf("\033[31mDEFEAT!\033[0m\n");
    }

    for (int i = 0; i < rows; i++){ //deallocates the board
        free(player_board[i]);
        free(opponent_board[i]);
    }
    free(player_board);
    free(opponent_board);

    scanf(" ?");
    return 0;
}
