javac *.java
start rmiregistry 1100
java Server 1100
java Client localhost 1100 run
check op
jps