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
	if token in ["Token.Keyword", "Token.Operator", "Token.Punctuation", "Token.Keyword.Constant"] or literal in ["const", "volatile"]:
		return True
	return False


def isHalsteadOperand(token, literal):
	print(token)
	if token in ["Token.Name.Attribute", "Token.Keyword.Type"] or re.match("Token.Literal*", str(token)) is not None\
			or re.match("Token.Name", str(token)) is not None or re.match("Token.Name.*", str(token)) is not None:
		return True
	return False


class MetricProfiler:
	def __init__(self, file_contents):
		self.original_content = file_contents
		# self.processed_content = self.process(file_contents)
		self.original_tokens = self.tokenize()     # tuple (token type, literal)
		self.char_num = self.countChars()
		self.word_num = self.countWords()     # not implemented
		self.line_num = self.countLines()
		self.h_length = 0  # num of token occurancies
		self.h_vocabulary = 0   # num of unique token occurancies
		self.h_volume = 0
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
			line = line.lstrip().rstrip()
			char_num += len(line)
		return char_num

	def countWords(self):      # tokenizacija, vidjeti sto treba
		word_count = 0
		tokenized = self.tokenize()
		for token, literal in tokenized:
			if literal in [" ", "\n", "\t"]:
				continue
			if "Comment" in token or "Namespace" in token:
				continue
			word_count += 1

		return word_count

	def countLines(self):      # ne brojati linije komentara
		line_num: int = 0
		line: str
		for line in self.original_content:
			if line.lstrip() in '':     # prazna linija, cak i ako ima tab
				continue
			if line.lstrip().startswith("/*") or line.lstrip().startswith("*") or line.lstrip().startswith("//"):
				continue
			if line.startswith("import"):
				continue
			line_num += 1
		return line_num

	def calculateHalsteadProfile(self):   # num of operators and operands
		tokenized = self.tokenize()
		operand_count = 0
		operand_dict = {}

		operator_count = 0
		operator_dict = {}

		for token, literal in tokenized:  # operator tokens - source lang keywords, operator symbols, standard library module names
			if isHalsteadOperand(token, literal):
				if literal not in operand_dict.keys():
					operand_dict[literal] = 0
				operand_dict[literal] += 1
				operand_count += 1

			if isHalsteadOperator(token, literal):
				if literal not in operator_dict.keys():
					operator_dict[literal] = 0
				operator_dict[literal] += 1
				operator_count += 1

		self.h_length = operator_count + operand_count
		self.h_vocabulary = len(operator_dict) + len(operand_dict)
		self.h_volume = self.h_length * math.log2(self.h_vocabulary)
		# return                  # operands - programmer defined words
