package com.example.try_littlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyGameView extends View
{
  private boolean gameStart = false;
  private boolean gamePass=false;
  private boolean gameOver=false;
  private float tubeWidth=80f;
  private float edge=10f;
  private int w=0;
  private int h=0;
  private float r=40f;
  private float d=0f;
  private float fTouchX = 0;
  private float fTouchY = 0;
  private float fRadius =30f;
  private float startX_R=0;
  private float startX_L=0;
  private float startY_T=0;
  private float startY_B=0;
  private float endX_R=0;
  private float endX_L=0;
  private float endY_T=0;
  private float endY_B=0;
  
  public MyGameView(Context context,int width,int height,float density)
  {

    super(context);
    /* �Ѽƪ�l�� */
    w=width;
    h=height-48;
    d=density;
    r=r*d;
    tubeWidth=tubeWidth*d;
    fRadius=fRadius*d;
    startX_R=edge+r*2;
    startX_L=edge;
    startY_T=edge;
    startY_B=edge+r*2;
    endX_R=w-edge;
    endX_L=w-edge-r*2;
    endY_T=h-edge-r*2;
    endY_B=h-edge;
  }
  
  @Override
  public void draw(Canvas canvas)
  {
    /*�Ŧ�e��(�eĲ�I�I)*/
    Paint paint = new Paint();
    {
      paint.setAntiAlias(true);
      paint.setColor(getResources().getColor(R.drawable.blue));
      paint.setAlpha(50);
    };
    /*�¦�e��(�e����)*/
    Paint paint1 = new Paint();
    {
      paint1.setAntiAlias(true);
      paint1.setColor(getResources().getColor(R.drawable.black));
      paint1.setStyle(Paint.Style.STROKE);
      paint1.setStrokeWidth(3);
      paint1.setTextSize(40);
    };
    //����e��(�e�{��r)
    Paint paint2 = new Paint();
    {
      paint2.setAntiAlias(true);
      paint2.setColor(getResources().getColor(R.drawable.red));
      paint2.setStyle(Paint.Style.FILL);
      paint2.setStrokeWidth(3);
      paint2.setTextSize(48);
    };   
    /*ø�s�C���_�I*/
    canvas.drawCircle(edge+r,edge+r,r, paint1);
    canvas.drawText("S",r+edge-20,r+edge+20,paint1);
    /*ø�s�C�����I*/
    canvas.drawCircle(w-edge-r,h-edge-r,r, paint1);
    canvas.drawText("E",w-r-edge-20,h-r-edge+20,paint1);
    /*ø�s����*/  
    canvas.drawLine(edge+r*2,edge,w/2+tubeWidth/2,edge,paint1);
    canvas.drawLine(w/2+tubeWidth/2,edge,w/2+tubeWidth/2,h-edge-tubeWidth,paint1);
    canvas.drawLine(w/2+tubeWidth/2,h-edge-tubeWidth,w-edge-r*2,h-edge-tubeWidth,paint1);

    canvas.drawLine(edge+r*2,edge+r*2,w/2-tubeWidth/2,edge+r*2,paint1);
    canvas.drawLine(w/2-tubeWidth/2,edge+r*2,w/2-tubeWidth/2,h-edge,paint1);
    canvas.drawLine(w/2-tubeWidth/2,h-edge,w-edge-r*2,h-edge,paint1);
    
    if(gameStart)
    {
      /*�C���}�l���A�ɦbĲ�I�I�W�e�X���*/
      canvas.drawCircle(fTouchX, fTouchY, fRadius, paint);
    }
    if(gamePass)
    {
      /*���GAME PASS!�r��*/
      canvas.drawText("GAME PASS!",edge,h-edge,paint2);
    }
    if(gameOver)
    {
      /*���GAME OVER!�r��*/
      canvas.drawText("GAME OVER!",edge,h-edge,paint2);
    }
    super.draw(canvas);
  }
  
  @Override
  public boolean onTouchEvent(MotionEvent event)
  {
    /*���oĲ�I�I��XY����*/
    fTouchX = event.getX();
    fTouchY = event.getY();
    float fTouchR=fTouchX+fRadius;
    float fTouchL=fTouchX-fRadius;
    float fTouchT=fTouchY-fRadius;
    float fTouchB=fTouchY+fRadius;

    if(event.getAction() == MotionEvent.ACTION_DOWN)
    {
      /*�P�_�O�_�b�_�I�d��*/
      if((fTouchR<startX_R)&&(fTouchL>startX_L)&&(fTouchT>startY_T)&&(fTouchB<startY_B))
      {
        gameStart=true;
        gamePass=false;
        gameOver=false;
      }
    }
    else if(event.getAction() == MotionEvent.ACTION_UP)
    {
      /*������_�ɱNgameStart�]��false*/
      gameStart = false;
    }
    else if(event.getAction() == MotionEvent.ACTION_MOVE)
    {
      /*�P�_�O�_���C���}�l���A*/
      if(gameStart)
      {
        /*�P�_Ĳ�I�I�O�_�b�w���d��*/
        if(((fTouchR<(w/2+tubeWidth/2))&&(fTouchL>edge)&&(fTouchT>edge)&&(fTouchB<edge+r*2))
           ||((fTouchR<(w/2+tubeWidth/2))&&(fTouchL>(w/2-tubeWidth/2))&&(fTouchT>edge)&&(fTouchB<h-edge))
           ||((fTouchR<w-edge)&&(fTouchL>(w/2-tubeWidth/2))&&(fTouchT>h-edge-r*2)&&(fTouchB<h-edge))
          )
        {
          /*�P�_�O�_�b���I�d��*/
          if((fTouchR<endX_R)&&(fTouchL>endX_L)&&(fTouchB<endY_B)&&(fTouchT>endY_T))
          {
            gameStart = false;
            gamePass=true;
            gameOver=false;
          }
        }
        else
        {
          /*Ĳ�I�I���b�w���d�򤺡A�פ�C���ñNgameOver�]��true*/
          gameStart = false;
          gamePass=false;
          gameOver=true;
        }
      }
    }
    /*Ĳ�odraw()*/
    MyGameView.this.invalidate();
    return true;
  }
}
