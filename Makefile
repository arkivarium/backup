all:	jar run

run:	arkivarium.jar
	java com.arkivarium.ArkivariumBackup

jar:
	make -C com/
	javapackager -createjar -appclass com.arkivarium.ArkivariumBackup -outfile arkivarium.jar -srcfiles . -v
