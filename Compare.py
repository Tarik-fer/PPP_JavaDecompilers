import os
import re

import pygments
from pygments import lexers

import Winnowing
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


def isFlowControl(line):
	if (("if" in line or "else if" in line or "switch" in line or "case" in line) and ";" not in line) \
			or ("return" in line and ("<" in line or ">" in line or "==" in line or "!=" in line)):
		return True
	return False


def countConditionStatements(filePath):
	statementCounter = 0
	with open(filePath, encoding="utf-8") as curr_file:
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
			if isFlowControl(line):
				statementCounter += float(getFlowStatementComplexity(line))
			if "for" in line and ";" not in line:
				statementCounter += 1
	return statementCounter


def printStatsGrade(sizeGrade, controlFlowStatementGrade):
	print(
		"Za sva ocjenivanja, ocjena 1 se daje prvoj datoteci te se uzima kao baza ocjene druge datoteke (veca ocjena predstavlja bolji program)")

	print("Ocjena velicine datoteka:")
	print("\tOcjena prve datoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % sizeGrade)
	print("Ocjena kompleksnosti uvjeta upravljanja tokom:")
	print("\tOcjena prve detoteke: 1")
	print("\tOcjena druge datoteke: %.5f" % controlFlowStatementGrade)


def printWinnowingSimilarity(winnowingOriginal, winnowingSecond, intersectionCounter):
	print("Winnowing slicnost:")
	print("\t" + str(len(winnowingOriginal) + len(winnowingSecond)) + " otisaka ukupno.")
	print("\t" + str(intersectionCounter) + " zajednickih otisaka.")
	result = (float(intersectionCounter) / (float(len(winnowingOriginal)) + float(len(winnowingSecond)))) * 100
	print("\tslicnost: %.3f%%" % result)


def printHalsteadComparison(first_file_h_metric: MetricProfiler, second_file_h_metric: MetricProfiler):
	global compare_method

	# print("Halstead volumen prve datoteke: " + str(first_file_h_metric.h_volume))
	# print("Halstead volumen druge datoteke: " + str(second_file_h_metric.h_volume))
	# print("Halstead tezina prve datoteke: " + str(first_file_h_metric.h_difficulty))
	# print("Halstead tezina druge datoteke: " + str(second_file_h_metric.h_difficulty))
	volume_relative_diff = first_file_h_metric.h_volume / second_file_h_metric.h_volume
	if volume_relative_diff > 1:
		volume_relative_diff = 1 / volume_relative_diff

	difficulty_relative_diff = first_file_h_metric.h_difficulty / second_file_h_metric.h_difficulty
	if difficulty_relative_diff > 1:
		difficulty_relative_diff = 1 / difficulty_relative_diff

	if first_file_h_metric.h_difficulty > second_file_h_metric.h_difficulty:
		print(("Originalna" if compare_method == ORIGINAL_vs_DECOMPILE else "Prva") +
		      " datoteka je teza za razumjeti te je dobila slijedecu ocjenu naspram drugoj: " +
		      str(round(difficulty_relative_diff * 100, 3)) + "%")
	elif first_file_h_metric.h_difficulty < second_file_h_metric.h_difficulty:
		print(("Dekompilirana" if compare_method == ORIGINAL_vs_DECOMPILE else "Druga") +
		      " datoteka je teza za razumjeti te je dobila slijedecu ocjenu naspram prvoj: " +
		      str(round(difficulty_relative_diff * 100, 3)) + "%")
	else:
		print("Datoteke su jednako teske za razumjeti te imaju istu ocjenu")

	if first_file_h_metric.h_volume > second_file_h_metric.h_volume:
		print("Za " + ("originalnu" if compare_method == ORIGINAL_vs_DECOMPILE else "prvu") +
		      " datoteku je potrebno vise vremena kako bi se razumjela te je dobila slijedecu ocjenu naspram drugoj: " +
		      str(round(volume_relative_diff * 100, 3)) + "%")
	elif first_file_h_metric.h_volume < second_file_h_metric.h_volume:
		print("Za " + ("dekompiliranu" if compare_method == ORIGINAL_vs_DECOMPILE else "drugu") +
		      " datoteku je potrebno vise vremena kako bi se razumjela te je dobila slijedecu ocjenu naspram prvoj: " +
		      str(round(volume_relative_diff * 100, 3)) + "%")
	else:
		print("Potrebno je podjednako vremena kako bi se datoteke razumjele te imaju istu ocjenu")


def printHalsteadMetric(file_h_metric):
	print("First file halstead :    (" +
	      str(file_h_metric.line_num) + ", " +
	      str(file_h_metric.word_num) + ", " +
	      str(file_h_metric.char_num) + ", " +
	      str(file_h_metric.h_length) + ", " +
	      str(round(file_h_metric.h_volume, 3)) + ", " +
	      str(file_h_metric.h_vocabulary) + ")")


def printHalsteadSimilarity(first_file_h_metric, second_file_h_metric):
	print("                         (lines, words, chars, h_len, h_vol, h_vocabulary)")
	printHalsteadMetric(first_file_h_metric)
	printHalsteadMetric(second_file_h_metric)
	print("               delta :   (" +
	      str(abs(first_file_h_metric.line_num - second_file_h_metric.line_num)) + ", " +
	      str(abs(first_file_h_metric.word_num - second_file_h_metric.word_num)) + ", " +
	      str(abs(first_file_h_metric.char_num - second_file_h_metric.char_num)) + ", " +
	      str(abs(first_file_h_metric.h_length - second_file_h_metric.h_length)) + ", " +
	      str(abs(round(first_file_h_metric.h_volume - second_file_h_metric.h_volume, 3))) + ", " +
	      str(abs(first_file_h_metric.h_vocabulary - second_file_h_metric.h_vocabulary)) + ")")

	physical_dif = first_file_h_metric.comparePhysicalProfileWith(second_file_h_metric)
	print("Slicnost fizickog profila : " + str(round(physical_dif * 100, 3)) + "%")


def printCompositeSimilarity(winnowing_similarity, physical_similarity, halstead_similarity):
	weighted_winnowing = winnowing_similarity * 0.6
	weighted_halstead = halstead_similarity * 0.3
	weighted_physical = physical_similarity * 0.1
	composite_profile = weighted_winnowing + weighted_halstead + weighted_physical
	print("Ukupna ocjena slicnosti : " + str(round(composite_profile * 100, 3)) + "%")


ORIGINAL_vs_DECOMPILE = 1
DECOMPILED_FILES = 2
compare_method = None


def compare():
	global ORIGINAL_vs_DECOMPILE
	global DECOMPILED_FILES
	global compare_method

	compare_method = input("Nacin usporedbe:\n  (1) Original i dekompilirano\n  (2) Dvije Dekompilirane datoteke\n\tOdaberite 1 ili 2: ")
	while compare_method not in ["1", "2"]:
		compare_method = input(
			"Nacin usporedbe:\n  (1) Original i dekompilirano\n  (2) Dvije Dekompilirane datoteke\n\tOdaberite 1 ili 2: ")
	compare_method = int(compare_method)

	print("\nUsporedba datoteka:")
	if compare_method == ORIGINAL_vs_DECOMPILE:
		firstFilePath = input("Unesite path do originalne .java datoteke: ")
	else:
		firstFilePath = input("Unesite path do prve .java datoteke: ")
	first_file_content = open(firstFilePath, "r", encoding='utf8').read()
	firstFileStats = os.stat(firstFilePath)
	first_before_token = first_file_content

	if compare_method == ORIGINAL_vs_DECOMPILE:
		secondFilePath = input("Unesite path do dekompajlirane .java datoteke: ")
	else:
		secondFilePath = input("Unesite path do druge .java datoteke: ")
	second_file_content = open(secondFilePath, "r", encoding='utf8').read()
	secondFileStats = os.stat(secondFilePath)
	second_before_token = second_file_content

	sizeGrade = float(firstFileStats.st_size) / float(secondFileStats.st_size)

	originalFlowStatements = countConditionStatements(firstFilePath)
	secondFlowStatements = countConditionStatements(secondFilePath)

	# ako druga datoteka ima vise kompliciraniji statement, imat ce veci broj bodava i time manju ocjenu
	if secondFlowStatements != 0:
		controlFlowStatementGrade = float(originalFlowStatements) / float(secondFlowStatements)
	else:
		controlFlowStatementGrade = 0

	first_file_h_metric = MetricProfiler(first_file_content)
	print("-------------------------------------------------------------")
	second_file_h_metric = MetricProfiler(second_file_content)

	first_file_content = tokenize(first_file_content)  # TOKENIZATION
	originalFingerprint = createFingerprints(first_file_content, 70)  # FINGERPRINTS
	winnowingOriginal = winnowingAlgorithm(originalFingerprint, 20)  # WINNOWING ALGORITHM

	second_file_content = tokenize(second_file_content)
	secondFingerprint = createFingerprints(second_file_content, 70)
	winnowingSecond = winnowingAlgorithm(secondFingerprint, 20)

	intersectionCounter = 0
	for fingerprint in winnowingSecond:
		if fingerprint in winnowingOriginal:
			intersectionCounter += 1

	for fingerprint in winnowingOriginal:
		if fingerprint in winnowingSecond:
			intersectionCounter += 1

	if compare_method == ORIGINAL_vs_DECOMPILE:
		printStatsGrade(sizeGrade, controlFlowStatementGrade)
		printWinnowingSimilarity(winnowingOriginal, winnowingSecond, intersectionCounter)
		printHalsteadSimilarity(first_file_h_metric, second_file_h_metric)

		printHalsteadComparison(first_file_h_metric, second_file_h_metric)

		winnowing_similarity = (float(intersectionCounter) / (float(len(winnowingOriginal)) + float(len(winnowingSecond))))
		physical_similarity = first_file_h_metric.comparePhysicalProfileWith(second_file_h_metric)
		halstead_similarity = first_file_h_metric.h_vocabulary / second_file_h_metric.h_vocabulary
		if halstead_similarity > 1:
			halstead_similarity = 1 / halstead_similarity
		printCompositeSimilarity(winnowing_similarity, physical_similarity, halstead_similarity)

	elif compare_method == DECOMPILED_FILES:
		printStatsGrade(sizeGrade, controlFlowStatementGrade)
		printHalsteadComparison(first_file_h_metric, second_file_h_metric)
