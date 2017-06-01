JCC = javac
JFLAGS = -g
default: server.class client.class
server.class: server.java
	$(JCC) $(JFLAGS) server.java

client.class: client.java
	$(JCC) $(JFLAGS) client.java

clean:
	$(RM) *.class
