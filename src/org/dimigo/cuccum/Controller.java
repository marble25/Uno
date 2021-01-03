package org.dimigo.cuccum;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.DataOutputStream;
import java.net.Socket;

public class Controller{

    @FXML Pane gameDisplay; //게임 화면
    @FXML Button titleBtn; //타이틀 버튼
    @FXML Button startBtn; //시작 버튼
    @FXML Button settingBtn; //설정 버튼(현재 무기능)
    @FXML ImageView loading; //로딩 이미지
    @FXML ImageView clockArrow; //시계 방향 표시
    @FXML ImageView clockArrowRe; //반시계 방향 표시
    @FXML ImageView card1; //겹쳐진 카드 표시1
    @FXML ImageView card2; //겹쳐진 카드 표시2
    @FXML ImageView card3; //겹쳐진 카드 표시3
    @FXML ImageView card4; //겹쳐진 카드 표시4
    @FXML ImageView unoCard1; //실질적으로 가져오는 우노 카드
    @FXML ImageView unoCard2; //unoCard1이 밑으로 내려가기 때문에 unoCard2가 눌리면 unoCard1이 올라오게 함
    @FXML ImageView cemetery; //가장 최근에 내진 카드(묘지)
    @FXML ImageView deck1; //deck1~deck19 패(하드코딩의 원인1)
    @FXML ImageView deck2;
    @FXML ImageView deck3;
    @FXML ImageView deck4;
    @FXML ImageView deck5;
    @FXML ImageView deck6;
    @FXML ImageView deck7;
    @FXML ImageView deck8;
    @FXML ImageView deck9;
    @FXML ImageView deck10;
    @FXML ImageView deck11;
    @FXML ImageView deck12;
    @FXML ImageView deck13;
    @FXML ImageView deck14;
    @FXML ImageView deck15;
    @FXML ImageView deck16;
    @FXML ImageView deck17;
    @FXML ImageView deck18;
    @FXML ImageView deck19;
    @FXML ImageView temp; //각종 애니메이션을 수행하는 아이
    @FXML ImageView k1; //k1~k23 이미지를 생성하는데 상대경로를 자세히 알지 못해서 ImageView로 Image를 받아옴(하드코딩의 원인2)
    @FXML ImageView k2;
    @FXML ImageView b0;
    @FXML ImageView b1;
    @FXML ImageView b2;
    @FXML ImageView b3;
    @FXML ImageView b4;
    @FXML ImageView b5;
    @FXML ImageView b6;
    @FXML ImageView b7;
    @FXML ImageView b8;
    @FXML ImageView b9;
    @FXML ImageView b10;
    @FXML ImageView b11;
    @FXML ImageView b12;
    @FXML ImageView g0;
    @FXML ImageView g1;
    @FXML ImageView g2;
    @FXML ImageView g3;
    @FXML ImageView g4;
    @FXML ImageView g5;
    @FXML ImageView g6;
    @FXML ImageView g7;
    @FXML ImageView g8;
    @FXML ImageView g9;
    @FXML ImageView g10;
    @FXML ImageView g11;
    @FXML ImageView g12;
    @FXML ImageView r0;
    @FXML ImageView r1;
    @FXML ImageView r2;
    @FXML ImageView r3;
    @FXML ImageView r4;
    @FXML ImageView r5;
    @FXML ImageView r6;
    @FXML ImageView r7;
    @FXML ImageView r8;
    @FXML ImageView r9;
    @FXML ImageView r10;
    @FXML ImageView r11;
    @FXML ImageView r12;
    @FXML ImageView y0;
    @FXML ImageView y1;
    @FXML ImageView y2;
    @FXML ImageView y3;
    @FXML ImageView y4;
    @FXML ImageView y5;
    @FXML ImageView y6;
    @FXML ImageView y7;
    @FXML ImageView y8;
    @FXML ImageView y9;
    @FXML ImageView y10;
    @FXML ImageView y11;
    @FXML ImageView y12;
    @FXML ImageView k10;
    @FXML ImageView k11;
    @FXML ImageView k12;
    @FXML ImageView k13;
    @FXML ImageView k20;
    @FXML ImageView k21;
    @FXML ImageView k22;
    @FXML ImageView k23;
    @FXML Rectangle scene1; //scene이동을 하면 NullPointerException이 떠서 한 fxml에 넣기 위한 꼼수1
    @FXML Rectangle scene2; //scene이동을 하면 NullPointerException이 떠서 한 fxml에 넣기 위한 꼼수2
    @FXML Circle turn; //현재 누구턴이지 알게하는 빨간원
    @FXML Rectangle shadow; //팝업시 그림자
    @FXML Rectangle popup; //팝업
    @FXML Rectangle red; //color팝업시 나타남
    @FXML Rectangle blue; //color팝업시 나타남
    @FXML Rectangle yellow; //color팝업시 나타남
    @FXML Rectangle green; //color팝업시 나타남
    @FXML Button p1num; //플레이어1의 카드 갯수
    @FXML Button p2num; //플레이어2의 카드 갯수
    @FXML Button p3num; //플레이어3의 카드 갯수
    @FXML ImageView victory; //승리시 이미지
    @FXML ImageView defeat; //패배시 이미지

    private boolean titleBtnCheck = false; //타이틀 버튼이 눌렸는지 체크하는 변수
    private boolean enterCheck = false; //카드에 들어갔는지 체크하는 변수
    private boolean throwCheck = true; //카드를 냈는지 체크하는 변수
    private SocketClient socketClient; //소켓 클라이언트
    private Image[][] images = new Image[5][13]; //이미지들
    private DataOutputStream output; //데이터아웃풋스트림
    private boolean isTurn; //자기턴인지 체크하는 변수
    private int tempInt; //템프인트
    private int tempColor; //템프컬러
    private Image tempImage; //템프이미지
    
    //isTurn setter
    public void setIsTurn(boolean isTurn) {
    	this.isTurn = isTurn;
    }
    
    //out setter
    public void setSocket(DataOutputStream out) {
    	this.output=out;
    }
    //소켓 작성
    public void writeSocket(String string) {
    	try {
    		output.writeUTF(string);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    //타이틀 버튼 움직임
	public void titleMove() {
        if(!titleBtnCheck) {
            Timeline timeline1 = new Timeline(new KeyFrame (Duration.millis(700), new KeyValue(titleBtn.layoutYProperty(), titleBtn.getLayoutY()-250)));
            Timeline timeline2 = new Timeline(new KeyFrame (Duration.millis(1000), new KeyValue(startBtn.opacityProperty(), 1)));
            Timeline timeline3 = new Timeline(new KeyFrame (Duration.millis(1000), new KeyValue(settingBtn.opacityProperty(), 1)));
            timeline1.play();
            timeline2.play();
            timeline3.play();
            titleBtnCheck = true;
            startBtn.setDisable(false);
            settingBtn.setDisable(false);
        }
        else {
            Timeline timeline1 = new Timeline(new KeyFrame (Duration.millis(700), new KeyValue(titleBtn.layoutYProperty(), titleBtn.getLayoutY()+250)));
            Timeline timeline2 = new Timeline(new KeyFrame (Duration.millis(400), new KeyValue(startBtn.opacityProperty(), 0)));
            Timeline timeline3 = new Timeline(new KeyFrame (Duration.millis(400), new KeyValue(settingBtn.opacityProperty(), 0)));
            timeline1.play();
            timeline2.play();
            timeline3.play();
            titleBtnCheck = false;
        }
    }
	//이미지를 세팅하고 씬이 넘어가는 거처럼 보이고 서버에 연결되고 로딩이 시작되는 함수
    public void gameStart(){
        imageSetting();
        scene1.setLayoutX(2000);
        titleBtn.setLayoutX(2000);
        startBtn.setLayoutX(2000);
        settingBtn.setLayoutX(2000);
        socketClient=new SocketClient();
        socketClient.start();
        startLoading();
    }
    //로딩 이미지를 돌리는 함수
    public void startLoading() {
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.hours(1), new KeyValue(loading.rotateProperty(), 360000)));
        timeline1.play();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //씬을 바꾸는 함수
    public void finishLoading(){
        scene2.setLayoutX(2000);
        scene2.setX(2000);
        loading.setLayoutX(2000);
        loading.setX(2000);
    }
    //각 deck에 진입 했을 때
    public void enter1() {
        enter(1);
    }
    public void enter2() {
        enter(2);
    }
    public void enter3() {
        enter(3);
    }
    public void enter4() {
        enter(4);
    }
    public void enter5() {
        enter(5);
    }
    public void enter6() {
        enter(6);
    }
    public void enter7() {
        enter(7);
    }
    public void enter8() {
        enter(8);
    }
    public void enter9() {
        enter(9);
    }
    public void enter10() {
        enter(10);
    }
    public void enter11() {
        enter(11);
    }
    public void enter12() {
        enter(12);
    }
    public void enter13() {
        enter(13);
    }
    public void enter14() {
        enter(14);
    }
    public void enter15() {
        enter(15);
    }
    public void enter16() {
        enter(16);
    }
    public void enter17() {
        enter(17);
    }
    public void enter18() {
        enter(18);
    }
    public void enter19() {
        enter(19);
    }
    //deck에 들어갔을 때 temp로  deck이 올라간 것 처럼 보여준다.
    public void enter(int num) {
        if(temp.getLayoutY()==370) {
            temp.setLayoutY(650);
        }
        if(enterCheck) {
            checkTemp2();
            switch(num) {
                case 1:
                    deck1.setOpacity(0.0);
                    temp.setLayoutX(deck1.getLayoutX());
                    temp.setImage(deck1.getImage());
                    break;
                case 2:
                    deck2.setOpacity(0.0);
                    temp.setLayoutX(deck2.getLayoutX());
                    temp.setImage(deck2.getImage());
                    break;
                case 3:
                    deck3.setOpacity(0.0);
                    temp.setLayoutX(deck3.getLayoutX());
                    temp.setImage(deck3.getImage());
                    break;
                case 4:
                    deck4.setOpacity(0.0);
                    temp.setLayoutX(deck4.getLayoutX());
                    temp.setImage(deck4.getImage());
                    break;
                case 5:
                    deck5.setOpacity(0.0);
                    temp.setLayoutX(deck5.getLayoutX());
                    temp.setImage(deck5.getImage());
                    break;
                case 6:
                    deck6.setOpacity(0.0);
                    temp.setLayoutX(deck6.getLayoutX());
                    temp.setImage(deck6.getImage());
                    break;
                case 7:
                    deck7.setOpacity(0.0);
                    temp.setLayoutX(deck7.getLayoutX());
                    temp.setImage(deck7.getImage());
                    break;
                case 8:
                    deck8.setOpacity(0.0);
                    temp.setLayoutX(deck8.getLayoutX());
                    temp.setImage(deck8.getImage());
                    break;
                case 9:
                    deck9.setOpacity(0.0);
                    temp.setLayoutX(deck9.getLayoutX());
                    temp.setImage(deck9.getImage());
                    break;
                case 10:
                    deck10.setOpacity(0.0);
                    temp.setLayoutX(deck10.getLayoutX());
                    temp.setImage(deck10.getImage());
                    break;
                case 11:
                    deck11.setOpacity(0.0);
                    temp.setLayoutX(deck11.getLayoutX());
                    temp.setImage(deck11.getImage());
                    break;
                case 12:
                    deck12.setOpacity(0.0);
                    temp.setLayoutX(deck12.getLayoutX());
                    temp.setImage(deck12.getImage());
                    break;
                case 13:
                    deck13.setOpacity(0.0);
                    temp.setLayoutX(deck13.getLayoutX());
                    temp.setImage(deck13.getImage());
                    break;
                case 14:
                    deck14.setOpacity(0.0);
                    temp.setLayoutX(deck14.getLayoutX());
                    temp.setImage(deck14.getImage());
                    break;
                case 15:
                    deck15.setOpacity(0.0);
                    temp.setLayoutX(deck15.getLayoutX());
                    temp.setImage(deck15.getImage());
                    break;
                case 16:
                    deck16.setOpacity(0.0);
                    temp.setLayoutX(deck16.getLayoutX());
                    temp.setImage(deck16.getImage());
                    break;
                case 17:
                    deck17.setOpacity(0.0);
                    temp.setLayoutX(deck17.getLayoutX());
                    temp.setImage(deck17.getImage());
                    break;
                case 18:
                    deck18.setOpacity(0.0);
                    temp.setLayoutX(deck18.getLayoutX());
                    temp.setImage(deck18.getImage());
                    break;
                case 19:
                    deck19.setOpacity(0.0);
                    temp.setLayoutX(deck19.getLayoutX());
                    temp.setImage(deck19.getImage());
                    break;
            }
            temp.setOpacity(1.0);
            backFront(num);
        }
        enterCheck = true;
    }
    //카드를 냈을 때 temp와 deck을 원 상태로 돌리는 함수
    public void checkTemp2() {
        deck1.setOpacity(1.0);
        deck2.setOpacity(1.0);
        deck3.setOpacity(1.0);
        deck4.setOpacity(1.0);
        deck5.setOpacity(1.0);
        deck6.setOpacity(1.0);
        deck7.setOpacity(1.0);
        deck8.setOpacity(1.0);
        deck9.setOpacity(1.0);
        deck10.setOpacity(1.0);
        deck11.setOpacity(1.0);
        deck12.setOpacity(1.0);
        deck13.setOpacity(1.0);
        deck14.setOpacity(1.0);
        deck15.setOpacity(1.0);
        deck16.setOpacity(1.0);
        deck17.setOpacity(1.0);
        deck18.setOpacity(1.0);
        deck19.setOpacity(1.0);
        if(throwCheck) {
            temp.toBack();
            temp.setOpacity(0.0);
        }
        else {
            throwCheck = true;
        }
    }
    //앞 뒤 구분해주는 함수
    public void backFront(int num) {
        temp.toBack();
        switch(num) {
            case 19:
                deck17.toBack();
            case 17:
                deck15.toBack();
            case 15:
                deck13.toBack();
            case 13:
                deck11.toBack();
            case 11:
                deck9.toBack();
            case 9:
                deck7.toBack();
            case 7:
                deck5.toBack();
            case 5:
                deck3.toBack();
            case 3:
                deck1.toBack();
            case 1:
                deck2.toBack();
            case 2:
                deck4.toBack();
            case 4:
                deck6.toBack();
            case 6:
                deck8.toBack();
            case 8:
                deck10.toBack();
            case 10:
                deck12.toBack();
            case 12:
                deck14.toBack();
            case 14:
                deck16.toBack();
            case 16:
                deck18.toBack();
        }
        cemetery.toBack();
        unoCard2.toBack();
        unoCard1.toBack();
        card4.toBack();
        card3.toBack();
        card2.toBack();
        card1.toBack();
    }
    //이미지 세팅하는 함수
    public void imageSetting() {
        images[4][0] = k1.getImage();
        images[4][1] = k2.getImage();
        images[4][2] = k10.getImage();
        images[4][3] = k11.getImage();
        images[4][4] = k12.getImage();
        images[4][5] = k13.getImage();
        images[4][6] = k20.getImage();
        images[4][7] = k21.getImage();
        images[4][8] = k22.getImage();
        images[4][9] = k23.getImage();
        images[1][0] = b0.getImage();
        images[1][1] = b1.getImage();
        images[1][2] = b2.getImage();
        images[1][3] = b3.getImage();
        images[1][4] = b4.getImage();
        images[1][5] = b5.getImage();
        images[1][6] = b6.getImage();
        images[1][7] = b7.getImage();
        images[1][8] = b8.getImage();
        images[1][9] = b9.getImage();
        images[1][10] = b10.getImage();
        images[1][11] = b11.getImage();
        images[1][12] = b12.getImage();
        images[3][0] = g0.getImage();
        images[3][1] = g1.getImage();
        images[3][2] = g2.getImage();
        images[3][3] = g3.getImage();
        images[3][4] = g4.getImage();
        images[3][5] = g5.getImage();
        images[3][6] = g6.getImage();
        images[3][7] = g7.getImage();
        images[3][8] = g8.getImage();
        images[3][9] = g9.getImage();
        images[3][10] = g10.getImage();
        images[3][11] = g11.getImage();
        images[3][12] = g12.getImage();
        images[0][0] = r0.getImage();
        images[0][1] = r1.getImage();
        images[0][2] = r2.getImage();
        images[0][3] = r3.getImage();
        images[0][4] = r4.getImage();
        images[0][5] = r5.getImage();
        images[0][6] = r6.getImage();
        images[0][7] = r7.getImage();
        images[0][8] = r8.getImage();
        images[0][9] = r9.getImage();
        images[0][10] = r10.getImage();
        images[0][11] = r11.getImage();
        images[0][12] = r12.getImage();
        images[2][0] = y0.getImage();
        images[2][1] = y1.getImage();
        images[2][2] = y2.getImage();
        images[2][3] = y3.getImage();
        images[2][4] = y4.getImage();
        images[2][5] = y5.getImage();
        images[2][6] = y6.getImage();
        images[2][7] = y7.getImage();
        images[2][8] = y8.getImage();
        images[2][9] = y9.getImage();
        images[2][10] = y10.getImage();
        images[2][11] = y11.getImage();
        images[2][12] = y12.getImage();
    }
    //묘지의 이미지를 설정하는 함수
    public void setTopCard(int number, int color) {
    	if(number>=13) {
    		cemetery.setImage(images[4][(number-13)*4+2+color]);
    		return;
    	}
    	cemetery.setImage(images[color][number]);
    }
    //패를 설정하는 함수
    public void setDeck(int number, int color, int n) {
    	if(number>=13) {
    		color=4;
    		number-=13;
    	}
        switch(n) {
            case 19:
                deck19.setImage(images[color][number]);
                if(deck19.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck19.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 18:
                deck18.setImage(images[color][number]);
                if(deck18.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck18.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 17:
                deck17.setImage(images[color][number]);
                if(deck17.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck17.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 16:
                deck16.setImage(images[color][number]);
                if(deck16.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck16.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 15:
                deck15.setImage(images[color][number]);
                if(deck15.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck15.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 14:
                deck14.setImage(images[color][number]);
                if(deck14.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck14.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 13:
                deck13.setImage(images[color][number]);
                if(deck13.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck13.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 12:
                deck12.setImage(images[color][number]);
                if(deck12.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck12.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 11:
                deck11.setImage(images[color][number]);
                if(deck11.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck11.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 10:
                deck10.setImage(images[color][number]);
                if(deck10.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck10.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 9:
                deck9.setImage(images[color][number]);
                if(deck9.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck9.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 8:
                deck8.setImage(images[color][number]);
                if(deck8.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck8.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 7:
                deck7.setImage(images[color][number]);
                if(deck7.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck7.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 6:
                deck6.setImage(images[color][number]);
                if(deck6.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck6.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 5:
                deck5.setImage(images[color][number]);
                if(deck5.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck5.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 4:
                deck4.setImage(images[color][number]);
                if(deck4.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck4.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 3:
                deck3.setImage(images[color][number]);
                if(deck3.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck3.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 2:
                deck2.setImage(images[color][number]);
                if(deck2.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck2.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
            case 1:
            	cardInitialization();
            	deck1.setImage(images[color][number]);
                if(deck1.getLayoutY()!=850) {
                	Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(deck1.layoutYProperty(), 850)));
                	timeline.play();
                }
                break;
        }
    }
    //카드를 낸다고 말하는 함수
    public void throwCard() {
    	if(isTurn) {
            for(int i=0; i<5; i++) {
            	for(int j=0; j<13; j++) {
            		if(temp.getImage().equals(images[i][j])) {
            			tempImage = temp.getImage();
            			if(i==4) {
            				tempInt=j;
            				colorPopUp();
            				return;
            			}
            			tempInt=-1;
            			writeSocket("Set " + j + " " + i);
            		}
            	}
            }
    	}
    }
    //승인이 떨어지면 카드를 내는 함수
    public void throwCard2() {
    	throwCheck = false;
    	if(tempInt!=-1) {
    		temp.setImage(images[4][tempInt*4+2+tempColor]);
    	}
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(temp.layoutYProperty(), cemetery.getLayoutY())));
        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(temp.layoutXProperty(), cemetery.getLayoutX())));
        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(500), new KeyValue(cemetery.imageProperty(), tempImage)));
        timeline1.play();
        timeline2.play();
        timeline3.play();
    }
    //카드를 가져오는 함수1
    public void drawUno1() {
    	if(isTurn) {
            unoCard2.setLayoutY(375);
            drawUno2();
    	}
    }
    //카드를 가져오는 함수2
    public void drawUno2() {
    	if(isTurn) {
    		writeSocket("Get");
    	}
    }
    //카드를 냈을 때 무슨 카드인지 받는 함수
    public void drawUno3(int number, int color) {
    		Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(700), new KeyValue(unoCard2.layoutYProperty(), unoCard2.getLayoutY()+700)));
            timeline1.play();
    }
    //순서바꾸는 함수
    public void changeOrder() {
    	if(clockArrow.isVisible()) {
    		clockArrow.setVisible(false);
    		clockArrowRe.setVisible(true);
    	}
    	else {
    		clockArrow.setVisible(true);
    		clockArrowRe.setVisible(false);
    	}
    }
    //턴 설정 하는 함수
	public void setTurn(int num) {
		System.out.println(num);
		switch(num) {
		case 1:
			turn.setCenterX(960);
			turn.setCenterY(800);
			break;
		case 2:
			turn.setCenterX(230);
			turn.setCenterY(500);
			break;
		case 3:
			turn.setCenterX(960);
			turn.setCenterY(300);
			break;
		case 4:
			turn.setCenterX(1690);
			turn.setCenterY(500);
			break;
		}
	}
	//k1카드를 냈을 때 카드색을 설정하도록 팝업을 띄우는 함수
	public void colorPopUp() {
		shadow.setLayoutX(0);
		popup.setLayoutX(710);
		red.setLayoutX(760);
		blue.setLayoutX(960);
		yellow.setLayoutX(760);
		green.setLayoutX(960);
	}
	//각각의 색깔을 클릭했을 때의 함수
	public void red() {
		tempColor=0;
		colorPopDown();
	}
	public void blue() {
		tempColor=1;
		colorPopDown();
	}
	public void yellow() {
		tempColor=2;
		colorPopDown();
	}
	public void green() {
		tempColor=3;
		colorPopDown();
	}
	//팝업을 내리는 함수
	public void colorPopDown() {
		shadow.setLayoutX(2000);
		popup.setLayoutX(2000);
		red.setLayoutX(2000);
		blue.setLayoutX(2000);
		yellow.setLayoutX(2000);
		green.setLayoutX(2000);
		writeSocket("Set "+(tempInt+13)+" "+tempColor);
	}
	//상대방의 카드수를 보여주는 공의 숫자를 바꾸는 함수
	public void setCardNum(int player, int num) {
		switch(player) {
		case 1:
			p1num.setText(""+num);
			break;
		case 2:
			p2num.setText(""+num);
			break;
		case 3:
			p3num.setText(""+num);
			break;
		}
	}
	//승리 후 함수
	public void victory() {
		shadow.setX(0);
		victory.setX(460);
		Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(victory.opacityProperty(), 1.0)));
		timeline1.play();
		System.out.println("I win");
	}
	//패배 후 함수
	public void defeat() {
		shadow.setX(0);
		defeat.setX(460);
		Timeline timeline1=new Timeline(new KeyFrame(Duration.millis(1000), new KeyValue(defeat.opacityProperty(), 1.0)));
		timeline1.play();
		System.out.println("I lose");
	}
	//초기화 함수
	public void cardInitialization() {
		deck1.setLayoutY(1200);
		deck2.setLayoutY(1200);
		deck3.setLayoutY(1200);
		deck4.setLayoutY(1200);
		deck5.setLayoutY(1200);
		deck6.setLayoutY(1200);
		deck7.setLayoutY(1200);
		deck8.setLayoutY(1200);
		deck9.setLayoutY(1200);
		deck10.setLayoutY(1200);
		deck11.setLayoutY(1200);
		deck12.setLayoutY(1200);
		deck13.setLayoutY(1200);
		deck14.setLayoutY(1200);
		deck15.setLayoutY(1200);
		deck16.setLayoutY(1200);
		deck17.setLayoutY(1200);
		deck18.setLayoutY(1200);
		deck19.setLayoutY(1200);
		deck1.setImage(null);
		deck2.setImage(null);
		deck3.setImage(null);
		deck4.setImage(null);
		deck5.setImage(null);
		deck6.setImage(null);
		deck7.setImage(null);
		deck8.setImage(null);
		deck9.setImage(null);
		deck10.setImage(null);
		deck11.setImage(null);
		deck12.setImage(null);
		deck13.setImage(null);
		deck14.setImage(null);
		deck15.setImage(null);
		deck16.setImage(null);
		deck17.setImage(null);
		deck18.setImage(null);
		deck19.setImage(null);
	}
}
