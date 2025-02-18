cd ..
java -Dedc.keystore=certs/cert.pfx -Dedc.keystore.password=123456 -Dedc.fs.config=configs/recycler.properties -jar build/libs/connector.jar
pause