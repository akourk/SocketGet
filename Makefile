all: SocketGet.java clean app
app:
	javac SocketGet.java
	ls -la
clean:
	rm -rf *.class