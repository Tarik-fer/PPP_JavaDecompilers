import re
import pygments
import math
from pygments import lexers


def isComment(line: str):
	if line.lstrip().startswith("/*") or line.lstrip().startswith("*") or line.lstrip().startswith("//"):
		return True
	return False


def tokenizeText(text):
	java_lexer = lexers.get_lexer_by_name("java")
	return pygments.lex(text, java_lexer)


def isHalsteadOperator(token, literal):
	# print(literal + " -> " + str(token))
	# print("\t" + str(str(token) == "Token.Punctuation"))
	if str(token) in ["Token.Keyword.Declaration", "Token.Keyword", "Token.Operator", "Token.Punctuation", "Token.Keyword.Constant"] or literal in [
		"const", "volatile", "static"]:
		# print("1 Is H operator : " + literal + " (" + str(token) + ")")
		# print(literal)
		return True
	return False


def isHalsteadOperand(token, literal):
	# print(token)
	if str(token) in ["Token.Name.Attribute", "Token.Keyword.Type"] or re.match("Token.Literal*", str(token)) is not None \
			or re.match("Token.Name", str(token)) is not None or re.match("Token.Name.*", str(token)) is not None:
		# print("2 Is H operand : " + literal + " (" + str(token) + ")")
		# print(literal)
		return True
	return False


class MetricProfiler:
	def __init__(self, file_contents):
		self.original_content: str = file_contents
		# self.processed_content = self.process(file_contents)
		self.original_tokens = self.tokenize()  # tuple (token type, literal)
		self.char_num = self.countChars()
		self.word_num = self.countWords()  # not implemented
		self.line_num = self.countLines()
		self.h_length = 0  # num of token occurancies
		self.h_vocabulary = 0  # num of unique token occurancies
		self.h_volume = 0
		self.h_difficulty = 0
		self.calculateHalsteadProfile()


	def tokenize(self):
		java_lexer = lexers.get_lexer_by_name("java")
		return pygments.lex(self.original_content, java_lexer)

	def countChars(self):
		char_num: int = 0
		og_content: str = self.original_content
		for line in og_content:
			# line = line.replace()		# zamijeniti space/tab sa praznim characterom
			if isComment(line):
				continue
			if line.lstrip().startswith("import"):
				continue
			line = re.sub("[^\"\'][ \t]*(?://.*)[^\"\']", "", line)     # remove inline comment
			line = line.lstrip().rstrip()
			char_num += len(line)
		return char_num

	def countWords(self):  # tokenizacija, vidjeti sto treba
		word_count = 0
		tokenized = self.tokenize()
		for token, literal in tokenized:
			if literal in [" ", "\n", "\t"]:
				continue
			if re.match("^[ \t]+$", literal) is not None:   # ako je rijec samo space/tabovi onda ne broji
				continue
			if "Comment" in str(token) or "Namespace" in str(token):
				continue
			# print(literal)   #str(word_count) + ". " +
			word_count += 1

		return word_count

	def countLines(self):  # ne brojati linije komentara
		line_num: int = 0
		line: str
		for line in self.original_content.splitlines():
			if line.lstrip() in '':  # prazna linija, cak i ako ima tab
				continue
			if line.lstrip().startswith("/*") or line.lstrip().startswith("*") or line.lstrip().startswith("//"):
				continue
			if line.startswith("import"):
				continue
			line_num += 1
		return line_num

	def calculateHalsteadProfile(self):  # num of operators and operands
		tokenized = self.tokenize()
		operand_count = 0
		operand_dict = {}

		operator_count = 0
		operator_dict = {}

		for token, literal in tokenized:  # operator tokens - source lang keywords, operator symbols, standard library module names

			if str(token) == "Token.Name.Namespace":
				continue
			if isHalsteadOperand(token, literal):
				if literal not in operand_dict.keys():
					# print("new unique operand : " + literal)
					operand_dict[literal] = 0
				# print("operand : " + literal + " | " + str(operand_dict[literal]))
				operand_dict[literal] += 1
				operand_count += 1

			if isHalsteadOperator(token, literal):
				if literal not in operator_dict.keys():
					# print("new unique operator : " + literal)
					operator_dict[literal] = 0
				# print("operand : " + literal + " | " + str(operator_dict[literal]))
				operator_dict[literal] += 1
				operator_count += 1

		print("Operators (unique, all) : " + str(len(operator_dict)) + ", " + str(operator_count))
		print("Operand (unique, all) : " + str(len(operand_dict)) + ", " + str(operand_count))
		self.h_length = operator_count + operand_count
		self.h_vocabulary = len(operator_dict) + len(operand_dict)
		self.h_volume = self.h_length * math.log2(self.h_vocabulary)
		self.h_difficulty = (len(operator_dict) / 2) * (operand_count / len(operand_dict))
	# return                  # operands - programmer defined words

	def comparePhysicalProfileWith(self, other):
		other: MetricProfiler
		line_dif = abs(self.line_num - other.line_num)
		word_dif = abs(self.word_num - other.word_num)
		char_dif = abs(self.char_num - other.char_num)

		# line_precentage_dif = line_dif/self.line_num
		# word_precentage_dif = word_dif/self.word_num
		# char_precentage_dif = char_dif/self.char_num

		line_precentage_dif = other.line_num / self.line_num
		if line_precentage_dif > 1:
			line_precentage_dif = 1 / line_precentage_dif

		word_precentage_dif = other.word_num / self.word_num
		if word_precentage_dif > 1:
			word_precentage_dif = 1 / word_precentage_dif

		char_precentage_dif = other.char_num / self.char_num
		if char_precentage_dif > 1:
			char_precentage_dif = 1 / char_precentage_dif

		return (line_precentage_dif + word_precentage_dif + char_precentage_dif) / 3

	def compareHalsteadProfileWith(self, other):
		other: MetricProfiler

		halstead_vocalbulary_similarity = self.h_vocabulary / other.h_vocabulary
		if halstead_vocalbulary_similarity > 1:
			halstead_vocalbulary_similarity = 1 / halstead_vocalbulary_similarity

		halstead_length_similarity = self.h_length / other.h_length
		if halstead_length_similarity > 1:
			halstead_length_similarity = 1 / halstead_length_similarity

		return (halstead_vocalbulary_similarity + halstead_length_similarity) / 2