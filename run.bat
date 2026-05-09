@echo off
setlocal

if "%TOMCAT_HOME%"=="" (
    echo Please set TOMCAT_HOME to your Apache Tomcat folder.
    echo Example: set TOMCAT_HOME=C:\apache-tomcat-9.0.89
    pause
    exit /b 1
)

if not exist "%TOMCAT_HOME%\lib\servlet-api.jar" (
    echo servlet-api.jar was not found in %TOMCAT_HOME%\lib
    echo Please check your TOMCAT_HOME path.
    pause
    exit /b 1
)

echo Creating WEB-INF\classes folder...
if not exist WEB-INF\classes mkdir WEB-INF\classes

echo Compiling Shopping Cart Web Application...
javac -cp "%TOMCAT_HOME%\lib\servlet-api.jar" -d WEB-INF\classes src\com\shoppingcart\*.java

if errorlevel 1 (
    echo.
    echo Compilation failed. Please check that Java JDK is installed and the code has no errors.
    pause
    exit /b 1
)

echo.
echo Compilation successful.
echo.
echo Deploy this folder to Tomcat as:
echo %TOMCAT_HOME%\webapps\Shopping_Cart
echo.
echo Then start Tomcat and open:
echo http://localhost:8080/Shopping_Cart

echo.
pause
