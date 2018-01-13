jar:
	make -C com/
	javapackager -createjar -appclass com.arkivarium.ArkivariumBackup -outfile arkivarium.jar -srcfiles . -v
