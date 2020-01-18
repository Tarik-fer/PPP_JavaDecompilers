import sys
import os
import zipfile
import subprocess
import datetime

#dictionary dekompilera
decompilers = {
	"cfr": "dekompajleri\\cfr-0.148.jar",
	"JD": "dekompajleri\\jd-gui-1.6.5.jar",
	"procyon": "dekompajleri\\procyon-decompiler-o.5.36.jar",
	"fernflower": "dekompajleri\\fernflower.jar"
	}

#dictionary mjesta za stavljanje rezultata testa
#mozda ipak nije losa ideja da se uotput dir napise kao input parametar jer moramo stvarati folder za svaki program unutar programa
#see outputFolder varijablu
decompileResultPath = {
	"cfr": "dekompajlirano\\CFR\\",
	"JD": "dekompajlirano\\JD\\",
	"procyon": "dekompajlirano\\prcyon\\",
	"fernflower": "dekompajlirano\\fernflower\\"
	}

def getJarName(pathToJar):
	return pathToJar[programPath.rfind('\\')+1:]

def extractFile(sourcePath, destPath):
	# os.system("jar xf %s %s" % path)
	# print("SOURCE PATH: %s" % sourcePath)
	print("DEST PATH: %s" % destPath)
	print("extracting...")
	with zipfile.ZipFile(sourcePath, 'r') as zf:
		zf.extractall(destPath)
	print("DONE")
	# currentFile = zipfile.ZipFile(path)
	# jarName = getJarName(path)
	# print("JAR NAME: %s" % jarName)
	# os.system("dir .\\dekompajlirano\\fernflower\\reflex_test_skripta\\ ")
	# currentFile.extractall(path.replace('\\', '/'))
	# currentFile.close()
	return


#uzimanje parametara iz command line-a

#as if you dont know what this is
decompiler = sys.argv[1]
#stvarcica sa jarom
programPath = sys.argv[2]
#naziv folder u koji ide output, nije cijeli path
outputFolder = sys.argv[3]


decompilerPath = decompilers[decompiler]

outputPath = decompileResultPath[decompiler] + outputFolder
# print("outputPath")

if decompiler == "cfr":
				#path do dekompajlera, path do programam, output directory
	command = "java -jar %s '%s' --outputdir '%s'" % (decompilerPath, programPath, outputPath)
	# print(command)
	# subprocess.call(command, shell=true)
	os.system(command)
	#old command = "java -jar "+ decompilers["cfr"] +" '"+ programPath +"' - '"+ decompileResultPath["cfr"] "'"

if decompiler == "JD":
	pass

if decompiler == "procyon":
	command = "java -jar %s '%s' -o '%s'" % (decompilerPath, programPath, outputPath)
	# subprocess.call(command, shell=true)
	os.system(command)



if decompiler == "fernflower":
	command = "java -jar %s %s %s" % (decompilerPath, programPath, outputPath)
	# print("Decompiler: '%s'" % decompilers["fernflower"])
	# print("Program path: '%s'" % programPath)
	# print("Output path: '%s'" % outputPath)
	if not os.path.exists(outputPath):
		os.makedirs(outputPath)
	# subprocess.call(command, shell=true)
	os.system(command)
	if ".jar" in programPath:
		# print("JAR NAME: %s" % getJarName(programPath))
		extractPath = outputPath +"\\"+ getJarName(programPath)
		# print("EXTRACT PATH: %s " % extractPath)
		extractFile(extractPath, outputPath)
