all:	jar run

jar:
	make -C com/
	javapackager -createjar -appclass com.arkivarium.ArkivariumBackup -outfile arkivarium.jar -srcfiles . -v

run:	arkivarium.jar
	java com.arkivarium.ArkivariumBackup
