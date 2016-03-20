cd G:\SOURCE\TIA_Examples\svh-simple-crud-tutorial
call mvn clean install
ping 127.0.0.1 -n 3 > nul
chdir /d C:\Apache\apache-tomcat-7.0.65\bin
call shutdown.bat
ping 127.0.0.1 -n 3 > nul
del C:\Apache\\apache-tomcat-7.0.65\webapps\web-1.0.war
ping 127.0.0.1 -n 1 > nul
rmdir /Q /S C:\Apache\apache-tomcat-7.0.65\webapps\web-1.0
ping 127.0.0.1 -n 1 > nul
copy G:\SOURCE\TIA_Examples\svh-simple-crud-tutorial\web\target\web-1.0.war C:\Apache\apache-tomcat-7.0.65\webapps\
ping 127.0.0.1 -n 1 > nul
chdir /d C:\Apache\apache-tomcat-7.0.65\bin 
cls
call debug.bat