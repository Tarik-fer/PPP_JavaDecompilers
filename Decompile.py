import os
import zipfile

# dictionary dekompilera
decompilers = {
	"cfr": "dekompajleri\\cfr-0.148.jar",
	"JD": "dekompajleri\\jd-gui-1.6.5.jar",
	"procyon": "dekompajleri\\procyon-decompiler-0.5.36.jar",
	"fernflower": "dekompajleri\\fernflower.jar"
}

# dictionary mjesta za stavljanje rezultata testa
# mozda ipak nije losa ideja da se uotput dir napise kao input parametar jer moramo stvarati folder za svaki program unutar programa
# see outputFolder varijablu
decompileResultPath = {
	"cfr": "dekompajlirano\\CFR\\",
	"JD": "dekompajlirano\\JD\\",
	"procyon": "dekompajlirano\\procyon\\",
	"fernflower": "dekompajlirano\\fernflower\\"
}


def getJarName(pathToJar):
	return pathToJar[pathToJar.rfind('\\') + 1:]


def extractFile(sourcePath, destPath):
	print("extracting...")
	with zipfile.ZipFile(sourcePath, 'r') as zf:
		zf.extractall(destPath)
	print("DONE")
	return


def decompile():
	# input naziva decompilera
	decompiler = input("Unesite naziv dekompajlera (cfr, procyon, fernflower): ")  # sys.argv[1]
	while decompiler not in ["cfr", "procyon", "fernflower"]:
		decompiler = input("Unesite naziv dekompajlera (cfr, procyon, fernflower): ")  # sys.argv[1]

	# stvarcica sa jarom
	programPath = input("Unesite path do jar ili class datoteke: ")  # sys.argv[2]
	# naziv folder u koji ide output, nije cijeli path
	outputFolder = input("Unesite naziv foldera (ne cijeli path) u kojem ce se nalaziti rjesenje: ")  # sys.argv[3]
	###################################

	# path do decompilera
	decompilerPath = decompilers[decompiler]

	# path u kojem ce se nalaziti rezultat dekompilacije
	outputPath = decompileResultPath[decompiler] + outputFolder

	if decompiler == "cfr":
		# path do dekompajlera, path do programam, output directory
		command = "java -jar %s \"%s\" --outputdir \"%s\"" % (decompilerPath, programPath, outputPath)
		os.system(command)

	if decompiler == "JD":
		pass

	if decompiler == "procyon":
		# print("DECOMPILER PATH: "+decompilerPath)
		# print("PROGRAM PATH: "+programPath)
		# print("OUTPUT PATH: "+outputPath)
		command = "java -jar %s \"%s\" -o \"%s\"" % (decompilerPath, programPath, outputPath)
		# print("COMMAND: "+command)
		# subprocess.call(command, shell=true)
		os.system(command)

	# fernflower ima problem da pri dekompajliranju .jar datoteke on svoj rezultat sprema u isto imenu .jar datoteku,
	# pa ju moramo extractati iz arhive kako bi mogli vidjeti .java datoteke
	if decompiler == "fernflower":
		command = "java -jar %s \"%s\" \"%s\"" % (decompilerPath, programPath, outputPath)
		if not os.path.exists(outputPath):
			os.makedirs(outputPath)
		os.system(command)
		if ".jar" in programPath:
			extractPath = outputPath + "\\" + getJarName(programPath)
			extractFile(extractPath, outputPath)
