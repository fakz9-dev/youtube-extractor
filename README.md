Usual call looks like "java -jar youtube-extractor.jar <youtube link here>". That call must download everything you need in folder where execution was initialed.

You also can provide specific arguments after your link:
	-oa: to download only audio stream
	-ov: to download only video stream
	-q <best/good/worst>: to choose specific quality
	-p: to provide specific path where file should save

If you intend to use arguments, your call should look like "java -jar youtube-extractor.jar <youtube link here> <arg1 here> <arg2 here> <etc>".
For example "java -jar youtube-extractor.jar https://www.youtube.com/watch?v=CsgHd8QOBz4 -oa -q best -p D:\"
