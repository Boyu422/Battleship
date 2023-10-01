:: ---------------------------------------------------------------------
:: JAP COURSE - SCRIPT
:: ASSIGNMENTS - CST8221 - Spring 2023
:: ---------------------------------------------------------------------
:: Begin of Script (Assignments – S23)
:: ---------------------------------------------------------------------
CLS

:: LOCAL VARIABLES ....................................................

SET SRCDIR=src
SET BINDIR=bin
SET BINOUT=A32-javac.out
SET BINERR=A32-javac.err
SET JARNAME=A32.jar
SET JAROUT=A32-jar.out
SET JARERR=A32-jar.err
SET DOCDIR=doc
SET DOCPACK=BattleshipGame
SET DOCOUT=A32-javadoc.out
SET DOCERR=A32-javadoc.err
SET MAINCLASSSRC=src/BattleshipGame/GameLauncher.java
SET MAINCLASSBIN=BattleshipGame.GameLauncher
SET SECONDCLASSSRC=src/BattleshipGame/GameView.java
SET THIRDCLASSSRC=src/BattleshipGame/GameController.java
SET FORTHCLASSRC=src/BattleshipGame/GameModel.java
SET FIFTHCLASSRC=src/BattleshipGame/GameServer.java
SET SIXTHCLASSRC=src/BattleshipGame/GameClient.java
SET SEVENTHCLASSRC=src/BattleshipGame/GameConfig.java

@echo off

ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
ECHO "@                                                                   @"
ECHO "@                   #       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@                  ##       @  A L G O N Q U I N  C O L L E G E  @  @"
ECHO "@                ##  #      @    JAVA APPLICATION PROGRAMMING    @  @"
ECHO "@             ###    ##     @        S U M M E R - 2 0 2 3       @  @"
ECHO "@          ###    ##        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  @"
ECHO "@        ###    ##                                                  @"
ECHO "@        ##    ###                 ###                              @"
ECHO "@         ##    ###                ###                              @"
ECHO "@           ##    ##               ###   #####  ##     ##  #####    @"
ECHO "@         (     (      ((((()      ###       ## ###   ###      ##   @"
ECHO "@     ((((     ((((((((     ()     ###   ######  ###  ##   ######   @"
ECHO "@        ((                ()      ###  ##   ##   ## ##   ##   ##   @"
ECHO "@         ((((((((((( ((()         ###   ######    ###     ######   @"
ECHO "@         ((         ((           ###                               @"
ECHO "@          (((((((((((                                              @"
ECHO "@   (((                      ((        /-----------------------\    @"
ECHO "@    ((((((((((((((((((((() ))         |  B A T T L E S H I P  |    @"
ECHO "@         ((((((((((((((((()           \-----------------------/    @"
ECHO "@                                                                   @"
ECHO "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"

ECHO "[LABS SCRIPT ---------------------]"

ECHO "1. Compiling ......................"
javac -Xlint -cp ".;%SRCDIR%;%JAVAFXDIR%" %MAINCLASSSRC% %SECONDCLASSSRC% %THIRDCLASSSRC% %FORTHCLASSRC% %FIFTHCLASSRC% %SIXTHCLASSRC% %SEVENTHCLASSRC% -d %BINDIR% > %BINOUT% 2> %BINERR%

ECHO "2. Creating Jar ..................."
cd bin
jar cvfe %JARNAME% %MAINCLASSBIN% . > %JAROUT% 2> %JARERR%

ECHO "3. Creating Javadoc ..............."
cd ..
javadoc -cp ".;%BINDIR%;../%LIBDIR%" --module-path "%LIBDIR%" -d %DOCDIR% -sourcepath %SRCDIR% -subpackages %DOCPACK% > %DOCOUT% 2> %DOCERR%

cd bin
ECHO "4. Running Jar ...................."
start java --module-path "../%LIBDIR%" -jar %JARNAME%
cd ..

ECHO "[END OF SCRIPT -------------------]"
ECHO "                                   "
@echo on

:: ---------------------------------------------------------------------
:: End of Script (Labs - W23)
:: ---------------------------------------------------------------------