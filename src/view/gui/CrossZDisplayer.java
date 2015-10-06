//package view.gui;
//
//import org.eclipse.swt.events.PaintEvent;
//import org.eclipse.swt.events.PaintListener;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.widgets.Composite;
//
//import algorithms.mazeGenerators.Position;
//
//public class CrossZDisplayer extends CrossDisplayer {
//	public CrossZDisplayer(Composite parent, int style, MazeCharacterGuiDisplayer character) {
//		super(parent, style, character);
//		final Color white = new Color(null, 255, 255, 255);
//		setBackground(white);
//		addPaintListener(new PaintListener() {
//			@Override
//			public void paintControl(PaintEvent e) {
//				e.gc.setForeground(new Color(null, 0, 0, 0));
//				e.gc.setBackground(new Color(null, 0, 0, 0));
//				drawMaze(e);
//			}
//		});
//	}
//		
//			public void drawMaze(PaintEvent e){
//				if (crossSection != null ) {
//					   int width=getSize().x;
//					   int height=getSize().y;
//
//					   double w=(double)width/(crossSection[0].length+0.5);
//					   double h=(double)height/(crossSection.length+0.5);
//					   
//					   int CubeWidth=(int) w;
//					   int CubeHeight=(int) h;
//					   int startX=0;
//					   int startY=0;
//					   
//					   int upSideHeight=CubeHeight/3;
//					   int leftSideWidth=CubeHeight/3;
//					   for (int i = 0; i < crossSection.length; i++) {
//						   for (int j = 0; j < crossSection[0].length; j++) {
//							   int curX=startX+(j*CubeWidth);
//							   int curY=startY+(i*CubeHeight);
//							   if(crossSection[i][j]==1){
//								   e.gc.drawPolygon(new int[]{curX,curY,
//								   curX+CubeWidth,curY,
//								   curX+CubeWidth+leftSideWidth,curY+upSideHeight,
//								   curX+leftSideWidth,curY+upSideHeight});
//
//								   e.gc.drawPolygon(new int[]{curX,curY,
//								   curX+leftSideWidth,curY+upSideHeight,
//								   curX+leftSideWidth,curY+upSideHeight+CubeHeight,
//								   curX,curY+CubeHeight});
//	
//								   e.gc.fillPolygon(new int[]{curX+leftSideWidth,curY+upSideHeight,
//								   curX+leftSideWidth+CubeWidth,curY+upSideHeight,
//								   curX+leftSideWidth+CubeWidth,curY+upSideHeight+CubeHeight,
//								   curX+leftSideWidth,curY+upSideHeight+CubeHeight});
//							   }else{
//								   if(drawHint&&currentHint!=null&&i==getCrossYDisplay(currentHint)&&j==getCrossXDisplay(currentHint)){
////									   Image img= new Image(getDisplay(), "resources\\Exit1.png");
////									   e.gc.drawImage(img, 0, 0, img.getImageData().width, img.getImageData().height, curX+leftSideWidth,curY+upSideHeight, CubeWidth, CubeHeight);
//								   }
//								   if (i == getCrossYDisplay(character.getPosition()) && j == getCrossXDisplay(character.getPosition())){
//									   character.drawCharcter(e, 
//											   curX+leftSideWidth,
//											   curY+upSideHeight,
//											   CubeWidth,
//											   CubeHeight);
//								   }
//								   
//							   }
//							   }
//						   }
//					   
//				}
//					
//				}
//
//			private int getCrossXDisplay(Position position) {
//
//				if (position.getZ() == character.getPosition().getZ())
//					return position.getY();
//
//				return -1;
//			}
// 
//			private int getCrossYDisplay(Position position) {
//				if (position.getZ() == character.getPosition().getZ())
//					return crossSection.length-1- position.getX();
//
//				return -1;
//			}
//
//		
//
//
//}
