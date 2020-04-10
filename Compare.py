import os
import re

import pygments
from pygments import lexers

from Winnowing import createFingerprints, winnowingAlgorithm


def tokenize(source_text):
	# file_path = input("File path: ")
	# file_path = file_path.replace("\\", "/")
	# file_path = file_path.replace(" ", " ")
	# print(file_path)
	# file_open = open(file_path, "r", encoding="utf8")
	# file_content = file_open.read()
	java_lexer = lexers.get_lexer_by_name("java")
	file_tokens = pygments.lex(source_text, java_lexer) # vraca tokene u obliku tuple-a, (ime tokena, string tokena u kodu)
	token_string = ""

	for token, literal in file_tokens:
		token_string += str(token).split('.')[1]
		# print(token)

	return token_string

def countVariableInCondition(line):
	# micanje svih posebnih znakova i stringova koji bi se mogli naci unutar linije uvjeta
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
	# micanje visestrukih razmaka
	line = ' '.join(line.split())
	# vraca se broj varijabli koje se koriste unutar samog uvjeta
	return len(line.split())


def getFlowStatementComplexity(line):
	# svaka usporedba se mjeri tezinom od 0.5 'jedinica'
	complexity = float(
		line.count("<") + line.count(">") + line.count(">=") + line.count("<=") + line.count("==") + line.count(
			"!")) * 0.5
	# print("got a ! over here: %.1f" % complexity)
	complexity += line.count("||") + line.count("&&")
	# print("after or and: %.1f" % complexity)
	complexity += countVariableInCondition(line)
	return complexity


def countConditionStatements(filePath):
	statementCounter = 0
	with open(filePath) as curr_file:
		for line in curr_file:
			############################## Micanje komentara
			# print("BEFORE REGEX: "+line)
			line = re.sub(re.compile(".*/\*.*", re.DOTALL), "", line)
			line = re.sub(re.compile(".*\\*.*", re.DOTALL), "", line)
			line = re.sub(re.compile("[ ]*\*.*", re.DOTALL), "", line)
			line = re.sub(re.compile("//.*?\n"), "", line)
			# print("AFTER REGEX: "+line)
			##############################################
			if "continue" in line or "break" in line:
				statementCounter += 2
			if ("if" in line or "else if" in line or "switch" in line or "case" in line) and ";" not in line:
				statementCounter += float(getFlowStatementComplexity(line))
			if "for" in line and ";" not in line:
				statementCounter += 1
	return statementCounter


def compare():
	print("\nUsporedba dekompajliranih datoteka:")
	originalFilePath = input("Unesite path do originalne .java datoteke: ")
	originalJavaFile = open(originalFilePath, "r", encoding='utf8').read()
	originalFileStats = os.stat(originalFilePath)

	secondFilePath = input("Unesite path do dekompajlirane .java datoteke: ")
	secondJavaFile = open(secondFilePath, "r", encoding='utf8').read()
	secondFileStats = os.stat(secondFilePath)

	print(
		"Za sva ocjenivanja, ocjena 1 se daje prvoj datoteci te se uzima kao baza ocjene druge datoteke (veca ocjena predstavlja bolji program)")

	print("Ocjena velicine datoteka:")
	# ako druga datoteka ima vecu velicinu, imat ce manju ocjenu
	sizeGrade = float(originalFileStats.st_size) / float(secondFileStats.st_size)
	print("\tOcjena prve datoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % sizeGrade)

	print("Ocjena kompleksnosti uvjeta upravljanja tokom:")
	originalFlowStatements = countConditionStatements(originalFilePath)
	secondFlowStatements = countConditionStatements(secondFilePath)

	# ako druga datoteka ima vise kompliciraniji statement, imat ce veci broj bodava i time manju ocjenu
	controlFlowStatementGrade = float(originalFlowStatements) / float(secondFlowStatements)
	print("\tOcjena prve detoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % controlFlowStatementGrade)

	# originalJavaFile = tokenize(originalJavaFile)
	# secondJavaFile = tokenize(secondJavaFile)

	originalFingerprint = createFingerprints(originalJavaFile, 10)
	secondFingerprint = createFingerprints(secondJavaFile, 10)

	winnowingOriginal = winnowingAlgorithm(originalFingerprint, 40)
	winnowingSecond = winnowingAlgorithm(secondFingerprint, 40)

	intersectionCounter = 0
	for fingerprint in winnowingSecond:
		if fingerprint in winnowingOriginal:
			intersectionCounter += 1

	for fingerprint in winnowingOriginal:
		if fingerprint in winnowingSecond:
			intersectionCounter += 1

	print("Wennowing slicnost:")
	print("\t" + str(len(winnowingOriginal) + len(winnowingSecond)) + " otisaka ukupno.")
	print("\t" + str(intersectionCounter) + " zajednickih otisaka.")
	result = (float(intersectionCounter) / (float(len(winnowingOriginal)) + float(len(winnowingSecond)))) * 100
	print("\tslicnost: %.3f%%" % result)