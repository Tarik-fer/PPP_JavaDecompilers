import sys
import os
import zipfile
import subprocess
import datetime
import hashlib
import re

#dictionary dekompilera
decompilers = {
	"cfr": "dekompajleri\\cfr-0.148.jar",
	"JD": "dekompajleri\\jd-gui-1.6.5.jar",
	"procyon": "dekompajleri\\procyon-decompiler-0.5.36.jar",
	"fernflower": "dekompajleri\\fernflower.jar"
	}

#dictionary mjesta za stavljanje rezultata testa
#mozda ipak nije losa ideja da se uotput dir napise kao input parametar jer moramo stvarati folder za svaki program unutar programa
#see outputFolder varijablu
decompileResultPath = {
	"cfr": "dekompajlirano\\CFR\\",
	"JD": "dekompajlirano\\JD\\",
	"procyon": "dekompajlirano\\procyon\\",
	"fernflower": "dekompajlirano\\fernflower\\"
	}


def getJarName(pathToJar):
	return pathToJar[programPath.rfind('\\')+1:]

def extractFile(sourcePath, destPath):
	print("extracting...")
	with zipfile.ZipFile(sourcePath, 'r') as zf:
		zf.extractall(destPath)
	print("DONE")
	return

def countVariableInCondition(line):
	#micanje svih posebnih znakova i stringova koji bi se mogli naci unutar linije uvjeta
	line = line.replace("}", " ")
	line = line.replace("{", " ")
	line = line.replace("if", "")
	line = line.replace("else if", "")
	line = line.replace("switch", "")
	line = line.replace("case", "")
	line = line.replace("<", " ")
	line = line.replace(">", " ")
	line = line.replace(">=", " ")
	line = line.replace("<=", " ")
	line = line.replace("&&", " ")
	line = line.replace("||", " ")
	line = line.replace("!", " ")
	line = line.replace("(", " ")
	line = line.replace(")", " ")
	#micanje visestrukih razmaka
	line = ' '.join(line.split())
	#vraca se broj varijabli koje se koriste unutar samog uvjeta
	return len(line.split())

def getFlowStatementComplexity(line):
	#svaka usporedba se mjeri tezinom od 0.5 'jedinica'
	complexity = float(line.count("<") + line.count(">") + line.count(">=") + line.count("<=") + line.count("==") + line.count("!"))*0.5
	# print("got a ! over here: %.1f" % complexity)
	complexity += line.count("||") + line.count("&&")
	# print("after or and: %.1f" % complexity)
	complexity += countVariableInCondition(line)
	return complexity


def countConditionStatements(filePath):
	statementCounter = 0
	with open(filePath) as file:
		for line in file:
			if "continue" in line or "break" in line:
				statementCounter += 2
			if ("if" in line or "else if" in line or "switch" in line or "case" in line) and ";" not in line:
				statementCounter += float(getFlowStatementComplexity(line))
			if "for" in line and ";" not in line:
				statementCounter += 1
	return statementCounter

################################################################## winnowing
def h11(word):
    return int(hashlib.md5(word).hexdigest()[:9], 16)
	#word.encode('utf-8')

def createFingerprints(text, k):
    fingerprintArr = [];
    for i in range (0, len(text) - k):
        fingerprintArr.append(h11(text[i: i + k]));
    return fingerprintArr;

def findMinIndex(fingerprints):
    minimum = fingerprints[0]
    minIndex = 0;
    for i in range (0, len(fingerprints)):
        if(int(fingerprints[i]) < int(minimum)):
            minimum = fingerprints[i];
            minIndex = i;

    return minIndex;

def winnowingAlgorithm(fingerprints, w):
    winnowingArr = [];
    minRelativeIndex = -1;
    minRelativeValue = 0;
    for i in range (0, len(fingerprints) - w):
        if(minRelativeIndex == -1):
            minRelativeIndex = findMinIndex(fingerprints[i:i + w]);
            minRelativeValue = fingerprints[i + minRelativeIndex];
            winnowingArr.append(minRelativeValue);
            minRelativeIndex -= 1;
        else:
            minRelativeIndex -= 1;
            if(int(minRelativeValue) > int(fingerprints[i +  w - 1])):
                minRelativeIndex = w - 1;
                minRelativeValue = fingerprints[i + w - 1];
                winnowingArr.append(minRelativeValue);
    return winnowingArr;

############################################################################


#################################### uzimanje parametara iz command line-a

action = raw_input("(1) Dekompiliranje\n(2) Usporedivanje datoteka\nOdaberite 1 ili 2: ")
while action not in ["1", "2"]:
	action = raw_input("(1) Dekompiliranje\n(2) Usporedivanje datoteka")

if action == "1":
	# input naziva decompilera
	decompiler = raw_input("Unesite naziv dekompajlera (cfr, procyon, fernflower): ")#sys.argv[1]
	while decompiler not in ["cfr", "procyon", "fernflower"]:
		decompiler = raw_input("Unesite naziv dekompajlera (cfr, procyon, fernflower): ")#sys.argv[1]
	# stvarcica sa jarom
	programPath = raw_input("Unesite path do jar ili class datoteke: ")#sys.argv[2]
	# naziv folder u koji ide output, nije cijeli path
	outputFolder = raw_input("Unesite naziv foldera (ne cijeli path) u kojem ce se nalaziti rjesenje: ")#sys.argv[3]
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
			extractPath = outputPath +"\\"+ getJarName(programPath)
			extractFile(extractPath, outputPath)
else:
	print("\nUsporedba dekompajliranih datoteka:")
	originalFilePath = raw_input("Unesite path do originalne .java datoteke: ")
	originalJavaFile = open(originalFilePath, "r").read()
	originalFileStats = os.stat(originalFilePath)
	secondFilePath = raw_input("Unesite path do dekompajlirane .java datoteke: ")
	secondJavaFile = open(secondFilePath, "r").read()
	secondFileStats = os.stat(secondFilePath)

	print("Za sva ocjenivanja, ocjena 1 se daje prvoj datoteci te se uzima kao baza ocjene druge datoteke (veca ocjena predstavlja bolji program)")

	print("Ocjena velicine datoteka:")
	#ako druga datoteka ima vecu velicinu, imat ce manju ocjenu
	sizeGrade = float(originalFileStats.st_size) / float(secondFileStats.st_size)
	print("\tOcjena prve datoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % sizeGrade)

	print("Ocjena kompleksnosti uvjeta upravljanja tokom:")
	originalFlowStatements = countConditionStatements(originalFilePath)
	secondFlowStatements = countConditionStatements(secondFilePath)
	#ako druga datoteka ima vise kompliciraniji statement, imat ce veci broj bodava i time manju ocjenu
	controlFlowStatementGrade = float(originalFlowStatements) / float(secondFlowStatements)
	print("\tOcjena prve detoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % controlFlowStatementGrade)

	originalFingerprint = createFingerprints(originalJavaFile, 10)
	secondFingerprint = createFingerprints(secondJavaFile, 10)

	winnowingOriginal = winnowingAlgorithm(originalFingerprint, 40)
	winnowingSecond = winnowingAlgorithm(secondFingerprint, 40)

	intersectionCounter = 0;
	for fingerprint in winnowingSecond:
	    if fingerprint in winnowingOriginal:
	        intersectionCounter += 1;

	for fingerprint in winnowingOriginal:
	    if fingerprint in winnowingSecond:
	        intersectionCounter += 1;

	print("Wennowing slicnost:")
	print("\t"+ str(len(winnowingOriginal) + len(winnowingSecond)) +" otisaka ukupno.")
	print("\t"+ str(intersectionCounter) +  " zajednickih otisaka.")
	result = (float(intersectionCounter) / (float(len(winnowingOriginal)) + float(len(winnowingSecond)))) * 100
	print("\tslicnost: %.3f%%" % result)




#
