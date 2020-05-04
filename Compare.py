import os
import re

import pygments
from pygments import lexers

from MetricProfiler import MetricProfiler
from Tokenization import tokenize

from Winnowing import createFingerprints, winnowingAlgorithm


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
	firstFilePath = input("Unesite path do originalne .java datoteke: ")
	first_file_content = open(firstFilePath, "r", encoding='utf8').read()
	firstFileStats = os.stat(firstFilePath)
	og_b4_token = first_file_content

	secondFilePath = input("Unesite path do dekompajlirane .java datoteke: ")
	second_file_content = open(secondFilePath, "r", encoding='utf8').read()
	secondFileStats = os.stat(secondFilePath)

	print(
		"Za sva ocjenivanja, ocjena 1 se daje prvoj datoteci te se uzima kao baza ocjene druge datoteke (veca ocjena predstavlja bolji program)")

	print("Ocjena velicine datoteka:")
	# ako druga datoteka ima vecu velicinu, imat ce manju ocjenu
	sizeGrade = float(firstFileStats.st_size) / float(secondFileStats.st_size)
	print("\tOcjena prve datoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % sizeGrade)

	print("Ocjena kompleksnosti uvjeta upravljanja tokom:")
	originalFlowStatements = countConditionStatements(firstFilePath)
	secondFlowStatements = countConditionStatements(secondFilePath)

	# ako druga datoteka ima vise kompliciraniji statement, imat ce veci broj bodava i time manju ocjenu
	controlFlowStatementGrade = float(originalFlowStatements) / float(secondFlowStatements)
	print("\tOcjena prve detoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % controlFlowStatementGrade)

	# print("Before process: " + first_file_content)
	# first_file_content = process_input(first_file_content)            # PROCESSING
	# second_file_content = process_input(second_file_content)
	# # print("-----------------------------------------------------------------------------------------")
	# print("After process: " + first_file_content)

	first_file_h_metric = MetricProfiler(first_file_content)
	second_file_h_metric = MetricProfiler(second_file_content)

	first_file_content = tokenize(first_file_content)  # TOKENIZATION
	second_file_content = tokenize(second_file_content)

	originalFingerprint = createFingerprints(first_file_content, 70)  # FINGERPRINTS
	secondFingerprint = createFingerprints(second_file_content, 70)

	winnowingOriginal = winnowingAlgorithm(originalFingerprint, 20)  # WINNOWING ALGORITHM
	winnowingSecond = winnowingAlgorithm(secondFingerprint, 20)

	intersectionCounter = 0
	for fingerprint in winnowingSecond:
		if fingerprint in winnowingOriginal:
			intersectionCounter += 1

	for fingerprint in winnowingOriginal:
		if fingerprint in winnowingSecond:
			intersectionCounter += 1

	print("Winnowing slicnost:")
	print("\t" + str(len(winnowingOriginal) + len(winnowingSecond)) + " otisaka ukupno.")
	print("\t" + str(intersectionCounter) + " zajednickih otisaka.")
	result = (float(intersectionCounter) / (float(len(winnowingOriginal)) + float(len(winnowingSecond)))) * 100
	print("\tslicnost: %.3f%%" % result)

	print("First file halstead : (" +
	      str(first_file_h_metric.line_num) + ", " +
	      str(first_file_h_metric.word_num) + ", " +
	      str(first_file_h_metric.char_num) + ", " +
	      str(first_file_h_metric.h_length) + ", " +
	      str(first_file_h_metric.h_volume) + ", " +
	      str(first_file_h_metric.h_vocabulary) + ")")

	print("Second file halstead : (" +
	      str(second_file_h_metric.line_num) + ", " +
	      str(second_file_h_metric.word_num) + ", " +
	      str(second_file_h_metric.char_num) + ", " +
	      str(second_file_h_metric.h_length) + ", " +
	      str(second_file_h_metric.h_volume) + ", " +
	      str(second_file_h_metric.h_vocabulary) + ")")
