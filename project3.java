// Project 3

float namX, namY, namDX, namDY;
float venX, venY, venDX, venDY;
float konX, konY, konDX, konDY;

String title = "Project 3";
String author = "MD NAYEEM U. BHUIkon";

float left = 50, right = 590, top = 150, bottom = 400;
float middle = ( left + right)/2;
boolean wall = true;
boolean mouse = true;
float miceX, miceY, miceDX;

int tableRed = 150, tableGreen = 150, tableBlue = 50;
int score = 0, m = 0, k = 0;
//frame counter
int frame;

void setup() {
  size ( 620, 480);
  reset();
  
}

void draw(){
  background( 250, 250, 200);
  rectMode( CORNERS );
  table( left, top, width-50, bottom );
  buttons();
  bounce();
  collisions();
  show();
  mouseDraw();
  messages();
}

void reset(){
  namX = random( middle, right ); namY = random( top, bottom );
  venX = random( middle, right ); venY = random( top, bottom );
  konX = random( middle, right ); konY = random( top, bottom );
  miceX = width-50;              miceY = 420;  
  
  namDX = random( -5,5 ); namDY = random( -5,5 );
  venDX = random( -5,5 ); venDY = random( -5,5 );
  konDX = random( -5,5 ); konDY = random( -5,5 );
  miceDX = -1;
}

void keyPressed() {
  if (key == 'e') { exit(); }
  if (key == 'r') { reset(); }
  if (key == 'w') { wall = true; }
  if (key == 'p') { tablePink(); }
  if (key == 'm') {mouse = true; }
  
}

void table( float east, float north, float west, float south ) {
  //pool table
  fill( tableRed, tableGreen, tableBlue );
  strokeWeight( 20 );
  //brown ball
  stroke( 127, 0, 0 );
  rect( east-20, north-20, west+20, south+20 );
  if (wall) {
    float middle = ( east + west )/2;
    stroke( 0, 127, 0);
    line( middle, north+10, middle, south-10 );
  }
  stroke(0);
  strokeWeight(1);
}

void bounce() {
  if ( wall ){
   namX += namDX;  if ( namX<middle || namX>right ) namDX *= -1;
   namY += namDY;  if ( namY<top || namY>bottom ) namDY *= -1;
   venX += venDX;  if ( venX<middle || venX>right ) venDX *= -1;
   venY += venDY;  if ( venY<top || venY>bottom ) venDY *= -1;
   konX += konDX;  if ( konX<middle || konX>right ) konDX *= -1;
   konY += konDY;  if ( konY<top || konY>bottom ) konDY *= -1;
 } else {
   namX += namDX;  if ( namX<left || namX>right ) namDX *= -1;
   namY += namDY;  if ( namY<top || namY>bottom ) namDY *= -1;
   venX += venDX;  if ( venX<left || venX>right ) venDX *= -1;
   venY += venDY;  if ( venY<top || venY>bottom ) venDY *= -1;
   konX += konDX;  if ( konX<left || konX>right ) konDX *= -1;
   konY += konDY;  if ( konY<top || konY>bottom ) konDY *= -1;
   }
}

void collisions() {
  float tmp;
  if ( dist( namX, namY, venX, venY ) <30 ) {
    tmp = venDX; venDX = namDX; namDX = tmp;
    tmp = venDY; venDY = namDY; namDY = tmp;
    k += 1;
   }

  if ( dist( namX, namY, konX, konY ) <30 ) {
    tmp = konDX; konDX = namDX; namDX = tmp;
    tmp = konDY; konDY = namDY; namDY = tmp;
    k += 1;
  }
  
  if ( dist( konX, konY, venX, venY ) <30 ) {
    tmp = venDX; venDX = konDX; konDX = tmp;
    tmp = venDY; venDY = konDY; konDY = tmp;
    k += 1;
  }
}

void show() {
  stroke(0);
  strokeWeight(1);
  fill( 255, 0, 0); ellipse( namX, namY, 30, 30 );
  fill(255, 255, 0); ellipse( venX, venY, 30, 30 );
  fill( 0, 0, 255); ellipse( konX, konY, 30, 30 );
  fill(0);
  text( "1", namX, namY );
  text( "2", venX, venY );
  text( "3", konX, konY );
}

void mouseDraw(){
  if (mouse){
    fill( 128, 128, 128 );
    ellipse( miceX,miceY, 20, 20 );
    miceX += miceDX;
    frame = frame + 1;
    if ( miceX<left || miceX>right ) miceDX *= -1;
    if ( miceDX == -1 ){
      if (frame/30 % 2 == 0 ){
        line( miceX + 10, miceY + 4, miceX + 20, miceY + 4);
      }else{
        line( miceX + 10, miceY + 4, miceX + 20, miceY - 1 );
      }
    }else if ( miceDX == 1 ){
      if ( frame/30 % 2 == 0 ){
        line( miceX - 10, miceY + 4, miceX - 20, miceY + 4 );
      }else{
        line( miceX - 10, miceY + 4, miceX -20, miceY -1 );
      }
    }
}
}

void tablePink(){
  tableRed = 255;
  tableGreen = 192;
  tableBlue = 203;
}

void buttons(){
  rectMode( CORNER );
  fill(0);
  strokeWeight(4);
  stroke( 255 );
  rect( 50, 50, 100, 50 );
  rect( 170, 50, 100, 50 );
  rect( 290, 50, 100, 50 );
  rect( 410, 50, 100, 50 );
  fill( 255 );
  text( "Reset Balls 'R' ", 70, 70 );
  text( "Remove Wall", 190, 70 );
  text( " 'W' ", 190, 90 );
  text( "Turn Table", 310, 70 );
  text( "Pink 'P' ", 310, 90 );
  text( "Spawn Mouse", 430, 70 );
  text( " 'M' ", 430, 90 );
}

void messages() {
  fill(0);
  text( title, width/3, 15);
  text( "Collisions", 50, 450);
  text( k, 120, 450 );
  text( author, 10, height-5 );
}

void mousePressed(){
  if ( mouseX>50 && mouseX<150 && mouseY>50 && mouseY<100 ){
    reset();
  }
  if ( mouseX>170 && mouseX<270 && mouseY>50 && mouseY<100 ){
    wall = false;
  }
  if ( mouseX>290 && mouseX<390 && mouseY>50 && mouseX<100 ){
    tablePink();
  }
  if ( mouseX>410 && mouseX<510 && mouseY>50 && mouseY<100 ){
    mouse = true;
  }
}
