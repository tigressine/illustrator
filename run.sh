# Build and run the program.
cd source
scalac $(find * -name '*.scala') && scala Driver
find * -name '*.class' -exec rm {} \;
cd ..
