import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Canvas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Canvas extends World
{
    private GreenfootImage bg = getBackground();
    private Color[][] image = new Color[ bg.getHeight() ][ bg.getWidth() ];
    /**
     * Constructor for objects of class Canvas.
     * 
     */
    public Canvas()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 768, 1); 
    }

    public void reset()
    {
        setBackground( bg );
    }
    private Color[][] getPixels()
    {
        Color[][] pixels = new Color[ bg.getHeight() ][ bg.getWidth() ];
        for( int r = 0; r < bg.getHeight(); r++ )
        {
            for( int c = 0; c < bg.getWidth(); c++ )
            {
                pixels[r][c] = image[r][c];
            }
        }
        return pixels;
    }

    public void dP()
    {
        Color[][] pixels = getPixels();
        for( int y = 0; y < image.length; y++ )
        {
            for( int x = 0; x < image[y].length; x++ )
            {
                bg.setColorAt( x, y, pixels[y][x] );
            }
        }
    }

    public void clear()
    {
        for( int x = 0; x < getWidth(); x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                Color white = new Color( 255, 255, 255 );
                bg.setColorAt( x, y, white );
            }
        }
    }

    public void randomizeOneColor()
    {
        int r = (int)(Math.random() * 256 );
        int g = (int)(Math.random() * 256 );
        int b = (int)(Math.random() * 256 );
        Color random = new Color( r, g, b );
        for( int i = 0; i < image.length; i++ )
        {
            for( int j = 0; j < image[i].length; j++ )
            {
                image[i][j] = random;
            }
        }
        dP();
    }

    public void staticImage()
    {
        for( int i = 0; i < image.length; i++ )
        {
            for( int j = 0; j < image[i].length; j++ )
            {
                int randomRed = (int)(Math.random() * 256 );
                int randomGreen = (int)(Math.random() * 256 );
                int randomBlue = (int)(Math.random() * 256 );
                Color random = new Color( randomRed, randomGreen, randomBlue );
                image[i][j] = random;
            }
        }
        dP();
    }

    public void greyScale()
    {
        for( int y = 0; y < image.length; y++ )
        {
            for( int x = 0; x < image[y].length; x++ )
            {
                Color old = getColorAt( x, y );
                int r = old.getRed();
                int g = old.getGreen();
                int b = old.getBlue();
                int n = ( r + g + b ) / 3;
                Color gS = new Color( n, n, n );
                image[y][x] = gS;
            }
        }
        dP();
    }

    public void invert()
    {
        for( int i = 0; i < image.length; i++ )
        {
            for( int j = 0; j < image[i].length; j++ )
            {
                Color old = getColorAt( j, i );
                int r = 255 - old.getRed();
                int g = 255 - old.getGreen();
                int b = 255 -old.getBlue();
                Color gS = new Color( r, g, b );
                image[i][j] = gS;
            }
        }
        dP();
    }

    public void mirrorLeftToRight()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        Color[][] pixels = getPixels();
        for( int x = 0; x < getWidth()/2; x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                Color old = bgCopy.getColorAt( x, y );
                bg.setColorAt( getWidth() - x - 1, y, old );
            }
        }
    }

    public void mirrorRightToLeft()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        for( int x = getWidth() / 2; x < getWidth(); x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                Color old = bgCopy.getColorAt( x, y );
                bg.setColorAt( getWidth() - x, y, old );
            }
        }
    }

    public void mirrorTopToBottom()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        Color[][] pixels = getPixels();
        for( int x = 0; x < getWidth(); x++ )
        {
            for( int y = 0; y < getHeight() / 2; y++ )
            {
                Color old = bgCopy.getColorAt( x, y );
                bg.setColorAt( x, getHeight() - y - 1, old );
            }
        }
    }

    public void mirrorBottomToTop()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        for( int x = 0; x < getWidth(); x++ )
        {
            for( int y = getHeight() / 2; y < getHeight(); y++ )
            {
                Color old = bgCopy.getColorAt( x, y );
                bg.setColorAt( x, getHeight() - y - 1, old );
            }
        }
    }

    public void flipVertical()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        Color[][] pixels = getPixels();
        for( int x = 0; x < getWidth(); x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                Color old = bgCopy.getColorAt( x, y );
                bg.setColorAt( x, getHeight() - y - 1, old );
            }
        }
        
    }

    public void flipHorizontal()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        Color[][] pixels = getPixels();
        for( int x = 0; x < getWidth(); x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                Color old = bgCopy.getColorAt( x, y );//pixels[y][x];
                //bg.setColorAt( getWidth() - x - 1, y, old );
                image[y][ bg.getWidth() - x - 1 ] = old;
            }
        }
        dP();
    }

    public void pixelate()
    {
        for( int x = 0; x < getWidth(); x+=10 )
        {
            for( int y = 0; y < getHeight(); y+=10 )
            {
                Color old = getColorAt( x, y );
                for( int sX = x - 10; sX < x; sX++ )
                {
                    if( sX > -1 && sX < getWidth() )
                    {
                        for( int sY = y - 10; sY < y; sY++ )
                        {
                            if( sY > -1 && sY < getHeight() )
                            {
                                bg.setColorAt( sX, sY, old );
                            }
                        }
                    }
                }
            }
        }
    }

    public void pixelate( int amount )
    {
        for( int x = 0; x < getWidth(); x+=amount )
        {
            for( int y = 0; y < getHeight(); y+=amount )
            {
                Color old = getColorAt( x, y );
                for( int sX = x - amount; sX < x; sX++ )
                {
                    if( sX > -1 && sX < getWidth() )
                    {
                        for( int sY = y - amount; sY < y; sY++ )
                        {
                            if( sY > -1 && sY < getHeight() )
                            {
                                bg.setColorAt( sX, sY, old );
                            }
                        }
                    }
                }
            }
        }
    }

    public void detectEdges()
    {
        detectEdges(4);
    }

    public void detectEdges(int amount)
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        Color[][] pixels = getPixels();
        for( int x = 0; x < bg.getWidth(); x++ )
        {
            int xx = x;
            for( int y = 0; y < getHeight(); y++ )
            {
                int yy = y++;

                Color firstXY = bgCopy.getColorAt( x, y );
                Color secondXY = bgCopy.getColorAt( xx, yy );

                int xyR = firstXY.getRed(); int xyG = firstXY.getGreen(); int xyB = firstXY.getBlue();
                int xxyyR = secondXY.getRed(); int xxyyG = secondXY.getGreen(); int xxyyB = secondXY.getBlue();

                int xyAVG = ( xyR + xyG + xyB ) / 3;
                int xxyyAVG = ( xxyyR + xxyyG + xxyyB ) / 3;
                int distance = Math.abs( xyAVG - xxyyAVG );
                if( distance > amount )
                {
                    image[y][x] = Color.BLACK;
                    image[yy][xx] = Color.BLACK;
                }
                else
                {
                    image[y][x] = Color.WHITE;
                    image[yy][xx] = Color.WHITE;
                }
            }

        }
        dP();
    }

    public void oldTelevision()
    {
        for( int x = 0; x < getWidth(); x+=3 )
        {
            for( int y = 0; y < getHeight(); y+=3 )
            {
                int randomRed = (int)(Math.random() * 256 );
                int randomGreen = (int)(Math.random() * 256 );
                int randomBlue = (int)(Math.random() * 256 );
                int average = ( randomRed + randomGreen + randomBlue ) / 3;
                Color gray = new Color( average, average, average );
                bg.setColorAt( x, y, gray );
                for( int sX = x - 3; sX < x; sX++ )
                {
                    if( sX > -1 && sX < getWidth() )
                    {
                        for( int sY = y - 3; sY < y; sY++ )
                        {
                            if( sY > -1 && sY < getHeight() )
                            {
                                bg.setColorAt( sX, sY, gray );
                            }
                        }
                    }
                }
            }
        }
    }

    public void shrink()
    {
        GreenfootImage bgCopy = new GreenfootImage( bg );
        for( int x = 0; x < getWidth(); x+=2 )
        {
            for( int y = 0; y < getHeight(); y +=2 )
            {
                if( x < getWidth() && y < getHeight() )
                {
                    Color old = bgCopy.getColorAt( x, y );
                    for( int xx = 0; xx < getWidth() / 2; xx++ )
                    {
                        for( int yy = 0; yy < getHeight() / 2; yy++ )
                        {
                            bg.setColorAt( xx, yy, old );
                        }
                    }
                }
            }
        }
    }

    public void drawPicture()
    {
        for( int x = 0; x < getWidth(); x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                bg.setColorAt( x, y, Color.BLUE );
            }
        }
        Color skin = new Color( 255, 224, 189 );
        bg.setColor( Color.RED );
        bg.fillOval( getWidth() / 2 - 100, getHeight() / 2 - 100, 200, 200 );
        bg.setColor( skin );
        bg.fillRect( 315, 380, 100, 20 );
        bg.fillRect( 610, 380, 100, 20 );
        bg.fillRect( getWidth() / 2 - 25, 240, 50, 50 );
        bg.fillRect( 476, 477, 10, 50 );
        bg.fillRect( 538, 477, 10, 50 );
        bg.fillRect( 455, 517, 25, 10 );
        bg.fillRect( 544, 517, 25, 10 );
        bg.setColor( Color.GREEN );
        bg.fillRect( 0, 525, getWidth(), getHeight() - 525 );
    }

    public void drawSouthAfrica()
    {
        for( int x = 0; x < getWidth(); x++ ){ for( int y = 0; y < getHeight(); y ++ )
            {
                //red background

                Color red = new Color(222, 56, 49);
                bg.setColorAt( x, y, red );
            }
        }
        for( int x = 0; x < getWidth(); x ++ ) //blue bottom
        {
            for( int y = getHeight() / 2; y < getHeight(); y++ )
            {
                Color blue = new Color(0, 35, 149);
                bg.setColorAt( x, y, blue );
            }
        }
        for( int x = 0; x < getWidth(); x++ )  //white stripe
        {
            for( int y = getHeight() / 2 - 100; y < getHeight() / 2 + 100; y++ )
            {
                bg.setColorAt( x, y, Color.WHITE );
            }
        }

        for( int x = 191; x < 575; x++ )
        {
            for( int y = x - 191; y < 385; y++ )
            {
                bg.setColorAt( x, y, Color.WHITE );
            }
        }
        for( int x = 191; x < 575; x++ )
        {
            for( int y = 383; y < 958 - x; y++ )
            {
                bg.setColorAt( x, y, Color.WHITE );
            }
        }
        for( int x = 0; x < 191; x++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                bg.setColorAt( x, y, Color.WHITE );
            }
        }

        for( int x = 128; x < 513; x++ )
        {
            for( int y = x - 128; y < 385; y++ )
            {
                Color green = new Color(0, 122, 77);
                bg.setColorAt( x, y, green );
            }
        }
        for( int x = 128; x < 513; x++ )
        {
            for( int y = 383; y < 895 - x; y++ )
            {
                Color green = new Color(0, 122, 77);
                bg.setColorAt( x, y, green );
            }
        }
        for( int x = 0; x < 128; x ++ )
        {
            for( int y = 0; y < getHeight(); y++ )
            {
                Color green = new Color(0, 122, 77);
                bg.setColorAt( x, y, green );
            }
        }
        for( int x = 0; x < getWidth(); x++ ) // green stripe
        {
            for( int y = getHeight() / 2 - 70; y < getHeight() / 2 + 70;  y++ )
            {
                Color green = new Color(0, 122, 77);
                bg.setColorAt( x, y, green );
            }
        }

    }
    
    public void getNums()
    {
        
        System.out.println( "getHeight(); -> " + getHeight() + "\timage[0].length; -> " + image[0].length +
                            "\ngetWidth(); -> " + getWidth() + "\timage.length; -> " + image.length );
    }
}
